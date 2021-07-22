package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.utils.vo.PaginationVO;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityService {


    Boolean save(Map<String, String> map);


    PaginationVO<Activity> getPageList(Map<String, Object> map);

    Boolean delete(String[] ids);

    Boolean update(Map<String, Object> map);

    List<Activity> update_select(Activity activity);

    Activity detail(String id);

    boolean createRemark(ActivityRemark activityRemark);

    List<ActivityRemark> getRemarkPageList(String activityId);

    boolean deleteRemark(String id);

}
