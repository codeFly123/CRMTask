package com.bjpowernode.crm.settings.filter;

import javax.servlet.*;
import java.io.IOException;


//解决乱码现象；
public class encoding implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        System.out.println("进入到过滤字符编码的过滤器");

        //过滤post请求中文乱码；
        req.setCharacterEncoding("UTF-8");

        //过滤get请求中文乱码；
        resp.setContentType("text/html;charset=utf-8");

        //放行；
        chain.doFilter(req,resp);


    }
}
