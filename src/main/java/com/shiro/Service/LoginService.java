package com.shiro.Service;

import com.shiro.Entity.LoginResult;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/1
 * @Time 11:31
 * @Description
 */
public interface LoginService {
    LoginResult login(String username, String password);
    void logout();
}
