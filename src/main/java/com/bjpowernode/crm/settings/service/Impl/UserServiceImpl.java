package com.bjpowernode.crm.settings.service.Impl;
//import javax.security.auth.login.LoginException;
//import com.bjpowernode.crm.settings.dao.UserDao;
//import com.bjpowernode.crm.settings.domain.User;
//import com.bjpowernode.crm.settings.service.UserService;
//import com.bjpowernode.crm.utils.DateTimeUtil;
//import com.bjpowernode.crm.utils.SqlSessionUtil;


import com.bjpowernode.crm.exception.LoginException;

import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.workbench.dao.ActivityDao;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.fasterxml.jackson.databind.util.ISO8601Utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserServiceImpl implements UserService {

    public UserServiceImpl() {}

    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);//?1


    @Override
    public User login(String loginACT, String loginPW, String ip) throws LoginException {
        //System.out.println("13212313");
        Map<String, String> map = new HashMap<String, String>();
        map.put("loginACT", loginACT);
        map.put("loginPW", loginPW);


        User user=userDao.login(map);
        //System.out.println(user);

        if(user==null){
            throw new LoginException("账号密码错误！");
        }

        //验证失效时间；
        String experiTime=user.getExpireTime();
        String currenTime= DateTimeUtil.getSysTime();

        if(experiTime.compareTo(currenTime)<0){
            throw new LoginException("验证已失效！");

        }

        //验证锁定状态；
        String lockState=user.getLockState();
        if("0".equals(lockState)){
            throw new LoginException("账户已锁定！");
        }

        //验证可接受的IP地址；
        String allowIps=user.getAllowIps();
        //System.out.println("7777"+allowIps);
        if(!allowIps.contains(ip)){

            //if(allowIps==ip){
            throw new LoginException("ip地址受限！");
        }



        return user;
    }




    @Override
    public List<User> getUserList() {
        List<User> list=userDao.getUserList();
        return  list;

    }
}
