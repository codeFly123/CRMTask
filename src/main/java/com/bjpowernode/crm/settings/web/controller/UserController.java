package com.bjpowernode.crm.settings.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path=req.getServletPath();

        if("/setting/user/save.do".equals(path)){

        }else if("/setting/user/xxx.do".equals(path)){

        }





    }
}
