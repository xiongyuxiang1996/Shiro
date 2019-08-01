package com.shiro.Controller;

import com.shiro.Entity.LoginResult;
import com.shiro.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private LoginService loginService;

    // 主页
    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    // 登陆页面
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String toLogin(Map<String, Object> map, HttpServletRequest request) {
        loginService.logout();
        return "/login";
    }

    // 登陆判断
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Map<String, Object> map,HttpServletRequest request) throws Exception{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginResult loginResult = loginService.login(username,password);
        if(loginResult.isLogin()) {
            return "/index";
        }
        else {
            map.put("msg",loginResult.getResult());
            map.put("username",username);
            return "/login";
        }
    }

    // 注销
    @RequestMapping("/logout")
    public String logOut(HttpSession session) {
        loginService.logout();
        return "/user/login";
    }

    // 没有权限
    @RequestMapping("/403")
    public String unauthorizedRole(){
        return "/403";
    }
}
