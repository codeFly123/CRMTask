package com.bjpowernode.crm.settings.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.Impl.UserServiceImpl;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.MD5Util;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
       // Student student = new Student();
       // System.out.println(student);

        //System.out.println("46565645987");
       // UserServiceImpl userService=new UserServiceImpl();
       // System.out.println(userService);
       // System.out.println("9999999999999999999999999999999999999");


        String path=req.getServletPath();
        if("/setting/user/login.do".equals(path)){
            login(req,resp);//?


        }else if("/setting/user/xxx.do".equals(path)){

        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("进入验证登录操作！");


        //UserServiceImpl userService=new UserServiceImpl();
        //System.out.println(userService);
        String loginACT=req.getParameter("loginACT");
        //System.out.println(loginACT);
        //1.用户及密码进行加密！
        String loginPW=req.getParameter("loginPW");
        //System.out.println(loginPW);
         loginACT= MD5Util.getMD5(loginACT);
         loginPW=MD5Util.getMD5(loginPW);
       // System.out.println(loginACT+","+loginPW);
        //2.获取ip地址
        String ip=req.getRemoteAddr();
        //String ip="192.168.1.1";
        //System.out.println(ip);

        //3.创建service对象；
        //统一使用动态代理；
        //System.out.println("9999999999999999999");

        //UserService userService = new UserServiceImpl();
        //System.out.println(userService);
        //System.out.println("9999999999999999999");
        //UserService proxy = (UserService) ServiceFactory.getService(userService);
        //未来业务层开发，统一用代理形态的接口对象
       // System.out.println("88888");
        //UserService userService = new UserServiceImpl();
        //System.out.println(userService);
        //UserService proxy = (UserService) ServiceFactory.getService(userService);

       // System.out.println("9999999999999999999");
        //System.out.println(proxy);
        //System.out.println(proxy.getClass().getName());



        //.......................................前后端分界点...........................................................





        UserService proxy = (UserService) ServiceFactory.getService(new UserServiceImpl());//Bug1 ?2
        //System.out.println("9999999999999999999");
        try {

            User user = proxy.login(loginACT, loginPW, ip);
            req.getSession().setAttribute("user", user);

            PrintJson.printJsonFlag(resp, true);

        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getMessage();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", false);
            map.put("msg", msg);
            PrintJson.printJsonObj(resp, map);


        }












    }
}
