package com.shiro.ServiceImpl;

import com.shiro.Entity.LoginResult;
import com.shiro.Service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/1
 * @Time 11:33
 * @Description
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public LoginResult login(String username, String password) {
        LoginResult loginResult = new LoginResult();
        if(username==null || username.isEmpty()) {
            loginResult.setLogin(false);
            loginResult.setResult("用户名为空！");
            return loginResult;
        }
        if(password==null || password.isEmpty()) {
            loginResult.setLogin(false);
            loginResult.setResult("密码为空！");
            return loginResult;
        }
        String msg="";
        // 1、获取Subject实例对象
        Subject currentUser = SecurityUtils.getSubject();

        // 2、判断当前用户是否登录
        if (currentUser.isAuthenticated() == false) {
            loginResult.setLogin(false);
            loginResult.setResult("当前账户已登陆！");
            return loginResult;
        }

        // 3、将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 4、认证
        try {
            currentUser.login(token);// 传到MyAuthorizingRealm类中的方法进行认证
            Session session = currentUser.getSession();
            session.setAttribute("username", username);
            loginResult.setLogin(true);
            return loginResult;
            //return "/index";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            msg = "账号不存在！";
        } catch (IncorrectCredentialsException e) {
            msg = "密码不正确！";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            msg="用户验证失败";
        }

        loginResult.setLogin(false);
        loginResult.setResult(msg);

        return loginResult;
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
