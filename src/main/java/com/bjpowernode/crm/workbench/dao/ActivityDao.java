package com.bjpowernode.crm.workbench.dao;


import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;


import java.util.List;
import java.util.Map;

public interface ActivityDao {


    int save(Map<String, String> map);


    List<Activity> getPageListMap(Map<String, Object> map);

    int getPageListCount(Map<String, Object> map);


    int delete(String[] ids);

    int update(Map<String, Object> map);

    List<Activity> update_select(Activity activity);

    Activity getDetail(String id);

    int createRemark(ActivityRemark activityRemark);

    List<ActivityRemark> getRemarkPageList(String activityId);


    int deleteRemark(String id);

}
