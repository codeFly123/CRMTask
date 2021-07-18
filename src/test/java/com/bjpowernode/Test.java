package com.bjpowernode;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.Impl.UserServiceImpl;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.MD5Util;

public class Test {

    @org.junit.Test
    public void Test1(){
        String Time1="2019-10-10 10:10:10";
        String currentTime= DateTimeUtil.getSysTime();
        System.out.println(currentTime);
        int count= Time1.compareTo(currentTime);
        System.out.println(count);
    }

    @org.junit.Test
    public void Test2(){
        String Ips="168.192.1.1,168.192.1.46,168.192.5.123";
        String ip="168.192.2.46";
        if(Ips.contains(ip)){
            System.out.println("有");
        }
    }
    @org.junit.Test
    public void Test3(){

        String pswd="ls";
        String pswd1= MD5Util.getMD5(pswd);
        System.out.println(pswd1);
        }

    @org.junit.Test
    public void Test4(){

        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService);

    }

    @org.junit.Test
    public void Test5() throws LoginException {

        UserServiceImpl userService = new UserServiceImpl();
        //String name=MD5Util.getMD5("zs");
        String name="ls";
        String pw=MD5Util.getMD5("123");
      User user=userService.login(name,pw,"168.192.1.1");
        System.out.println(user);

    }




}
