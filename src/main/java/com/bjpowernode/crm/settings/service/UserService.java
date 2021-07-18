package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.domain.User;

public interface UserService {


    User login(String loginACT, String loginPW, String ip) throws LoginException;
}
