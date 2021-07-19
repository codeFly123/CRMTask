package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface UserDao {


        User login(Map<String, String> map);

        List<User> getUserList();
}
