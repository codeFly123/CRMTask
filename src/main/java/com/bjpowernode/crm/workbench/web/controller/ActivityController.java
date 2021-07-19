package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.Impl.UserServiceImpl;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.*;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.bjpowernode.crm.workbench.service.ActivityServiceImpl.ActivityServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("进入市场活动控制器");


        String path=req.getServletPath();
        if("/workbench/activity/getUserList.do".equals(path)){
            getUserList(req,resp);



        }else if("/workbench/activity/save.do".equals(path)){
            save(req,resp);

        }else if("/setting/user/xxx.do".equals(path)){

        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) {

        Map<String,String> map=new HashMap<String,String>();
        String id= UUIDUtil.getUUID();
        String OWNER=req.getParameter("OWNER");
        System.out.println( OWNER);
        String NAME=req.getParameter("NAME");
        System.out.println(NAME);
        String startDate=req.getParameter("startDate");
        System.out.println(startDate);
        String endDate=req.getParameter("endDate");
        System.out.println( endDate);
        String cost = req.getParameter("cost");
        System.out.println(cost);
        String description = req.getParameter("description");
        System.out.println(description);
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User) req.getSession().getAttribute("user")).getNAME();


        map.put("id",id);
        map.put("OWNER",OWNER);
        map.put("NAME",NAME);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("cost",cost);
        map.put("description",description);
        map.put("createTime",createTime);
        map.put("createBy",createBy);

        ActivityService proxy=(ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

       Boolean flag= proxy.save(map);
       PrintJson.printJsonFlag(resp,flag);










    }





    private void getUserList(HttpServletRequest req, HttpServletResponse resp) {

        UserService proxy = ( UserService)ServiceFactory.getService(new  UserServiceImpl());

        List<User> list=proxy.getUserList();

        PrintJson.printJsonObj(resp,list);//转换成JSon数组；










    }


}

