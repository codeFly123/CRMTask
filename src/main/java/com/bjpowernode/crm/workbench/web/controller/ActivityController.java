package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.Impl.UserServiceImpl;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.*;
import com.bjpowernode.crm.utils.vo.PaginationVO;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.bjpowernode.crm.workbench.service.ActivityServiceImpl.ActivityServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入市场活动控制器");


        String path=req.getServletPath();
        if("/workbench/activity/getUserList.do".equals(path)){
            getUserList(req,resp);



        }else if("/workbench/activity/save.do".equals(path)){
            save(req,resp);

        }else if("/workbench/activity/pageList.do".equals(path)){
            System.out.println("1");
            getPageList(req,resp);

        }else if("/workbench/activity/delete.do".equals(path)){
            delete(req,resp);

        }else if("/workbench/activity/update.do".equals(path)){
            update(req,resp);

        }else if("/workbench/activity/update-select.do".equals(path)){

            update_select(req,resp);

        }else if("/workbench/activity/detail.do".equals(path)){
            detail(req,resp);

        }else if("/workbench/activity/remarkPageList.do".equals(path)){
            getRemarkPageList(req,resp);

        }else if("/workbench/activity/createRemark.do".equals(path)){
            createRemark(req,resp);


        }else if("/workbench/activity/deleteRemark.do".equals(path)){
            deleteRemark(req,resp);

        }else if("/workbench/activity/xxx.do".equals(path)){


        }

    }

    private void deleteRemark(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("进入评论删除操作层");
        String id=req.getParameter("id");
        ActivityService proxy=(ActivityService)ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag=proxy.deleteRemark(id);
        PrintJson.printJsonFlag(resp,flag);


    }

    private void createRemark(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("进入备注创建操作层！");


        String id=UUIDUtil.getUUID();
        String createDate=DateTimeUtil.getSysTime();
        String createBy=req.getParameter("createBy");
        String noteContent=req.getParameter("noteContent");

        ActivityRemark activityRemark = new ActivityRemark();
        activityRemark.setId(id);
        activityRemark.setCreateBy(createBy);
        activityRemark.setCreateTime(createDate);
        activityRemark.setNoteContent(noteContent);

        ActivityService proxy=(ActivityService)ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag=proxy.createRemark(activityRemark);
        PrintJson.printJsonFlag(resp,flag);


    }

    private void getRemarkPageList(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("已进入备注信息操作层!");
        String activityId=req.getParameter("activityId");

        ActivityService proxy =(ActivityService)ServiceFactory.getService(new ActivityServiceImpl());
        List<ActivityRemark> list=proxy.getRemarkPageList(activityId);
        System.out.println(list);
        PrintJson.printJsonObj(resp,list);




    }

    private void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入详细信息跳转页面！");
        String id=req.getParameter("id");
        ActivityService proxy=(ActivityService)ServiceFactory.getService(new ActivityServiceImpl());
        Activity a=proxy.detail(id);
        System.out.println("id="+a.getId());
        req.setAttribute("a",a);
        req.getRequestDispatcher("/workbench/activity/detail.jsp").forward(req,resp);



    }

    private void update_select(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("进入表单覆盖操作！");
        String id=req.getParameter("$zxId");
        Activity activity = new Activity();
        activity.setId(id);
        ActivityService proxy=(ActivityService)ServiceFactory.getService(new ActivityServiceImpl());
        List<Activity> list=proxy.update_select(activity);
        System.out.println("77777777777777777,list="+list);
        PrintJson.printJsonObj(resp,list);//未传bug

    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("已进入更新操作！");
        Map<String,Object> map=new HashMap<String,Object>();
        String id= req.getParameter("id");
        String OWNER=req.getParameter("OWNER");
        String NAME=req.getParameter("NAME");
        String startDate=req.getParameter("startDate");
        String endDate=req.getParameter("endDate");
        String cost = req.getParameter("cost");
        String description = req.getParameter("description");


        map.put("id",id);
        map.put("OWNER",OWNER);
        map.put("NAME",NAME);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("cost",cost);
        map.put("description",description);


        ActivityService proxy=(ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        Boolean flag= proxy.update(map);
        PrintJson.printJsonFlag(resp,flag);//转出去作为请求成功信息；

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("已进入删除操作！");
        String[] ids=req.getParameterValues("id");
        ActivityService proxy=(ActivityService)ServiceFactory.getService(new ActivityServiceImpl());
        Boolean flag=proxy.delete(ids);
        PrintJson.printJsonFlag(resp,flag);











    }

    private void getPageList(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("2");

        String pageNoStr=req.getParameter("pageNo");
        int pageNo=Integer.parseInt(pageNoStr);
        System.out.println(pageNo);
        String pageSizeStr=req.getParameter("pageSize");
        int pageSize=Integer.parseInt(pageSizeStr);
        System.out.println(pageSize);
        String name=req.getParameter("name");
        String owner=req.getParameter("owner");
        String startDate=req.getParameter("startDate");
        String endDate=req.getParameter("endDate");
        int skipCount=(pageNo-1)*pageSize;



         Map<String,Object> map=new HashMap<String,Object>();

        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        ActivityService proxy=(ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        System.out.println("3");
        PaginationVO<Activity> vo=proxy.getPageList(map);
        System.out.println("4");

        PrintJson.printJsonObj(resp,vo);





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
       PrintJson.printJsonFlag(resp,flag);//转出去作为请求成功信息；










    }





    private void getUserList(HttpServletRequest req, HttpServletResponse resp) {

        UserService proxy = ( UserService)ServiceFactory.getService(new  UserServiceImpl());

        List<User> list=proxy.getUserList();

        PrintJson.printJsonObj(resp,list);//转换成JSon数组；传出去










    }


}

