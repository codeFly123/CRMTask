package com.bjpowernode.crm.workbench.service.ActivityServiceImpl;

import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.workbench.dao.ActivityDao;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao=SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

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
}
