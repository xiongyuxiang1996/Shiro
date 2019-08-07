package com.shiro.ServiceImpl;

import com.shiro.Entity.LoginResult;
import com.shiro.Entity.User;
import com.shiro.Service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
        if (currentUser.isAuthenticated()) {
            loginResult.setLogin(false);
            loginResult.setResult("当前账户已登陆！");
            return loginResult;
        }

        // 3、将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 4、认证
        try {
            currentUser.login(token);   // 传到MyAuthorizingRealm类中的方法进行认证
            // 用户名，密码匹配，判断用户状态
            User user = (User) currentUser.getPrincipals().getPrimaryPrincipal();
            if(user.getState() == 0){
                msg = "账号未认证！";
                loginResult.setLogin(false);
                loginResult.setResult(msg);
            }
            else if(user.getState() == 1){
                loginResult.setLogin(true);
            }
            else if(user.getState() == 2){
                msg = "账号已锁定！";
                loginResult.setLogin(false);
                loginResult.setResult(msg);
            }
            else{
                msg = "账号状态异常！";
                loginResult.setLogin(false);
                loginResult.setResult(msg);
            }
            return loginResult;
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            msg = "账号不存在！";
        } catch (IncorrectCredentialsException e) {
            msg = "密码不正确！";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            msg = "用户验证失败！";
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
