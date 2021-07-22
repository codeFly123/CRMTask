package com.bjpowernode.crm.workbench.service.ActivityServiceImpl;

import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.utils.vo.PaginationVO;
import com.bjpowernode.crm.workbench.dao.ActivityDao;
import com.bjpowernode.crm.workbench.dao.ActivityRemarkDao;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao=SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

   // private ActivityRemarkDao activityRemarkDao=SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);

    @Override
    public Boolean save(Map<String, String> map) {
        System.out.println(map);

        boolean flag=true;
        int count=activityDao.save(map);
        if(count!=1){
            flag=false;
        }

        return flag;


    }

    @Override
    public PaginationVO<Activity> getPageList(Map<String, Object> map) {

        List<Activity> list=activityDao.getPageListMap(map);

        int count=activityDao.getPageListCount(map);

        PaginationVO<Activity> vo = new PaginationVO<Activity>();

        vo.setTotal(count);

        vo.setDataList(list);

        return vo;


    }

    @Override
    public Boolean delete(String[] ids) {
        boolean flag=false;


           int counts= activityDao.delete(ids);


        if(counts==ids.length){
            flag=true;
        }


        return flag;
    }

    @Override
    public Boolean update(Map<String,Object> map) {
        boolean flag=false;
        int count=activityDao.update(map);
        if(count!=0){
            flag=true;
        }
        System.out.println("8888888888888888888888888888888,count="+count);

        return flag;
    }

    @Override
    public List<Activity> update_select(Activity activity) {
        List<Activity> list=activityDao.update_select(activity);
        System.out.println("99999999999999999999999999999999,list="+list);

        return list;
    }

    @Override
    public Activity detail(String id) {
        Activity activity=activityDao.getDetail(id);

        return activity;
    }

    @Override
    public List<ActivityRemark> getRemarkPageList(String activityId) {
        System.out.println("ok");
        List<ActivityRemark> list=activityDao.getRemarkPageList(activityId);
        System.out.println(list+"12345697879845465569999999999998");

        return list;
    }

    @Override
    public boolean deleteRemark(String id) {
        boolean flag=false;
        int count=activityDao.deleteRemark(id);
        System.out.println("deleteRemark"+count);
        if(count!=0){
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean createRemark(ActivityRemark activityRemark) {
        boolean flag=false;
        int count=activityDao.createRemark(activityRemark);
        if(count!=0){
            flag=true;
        }



        return flag;
    }


}
