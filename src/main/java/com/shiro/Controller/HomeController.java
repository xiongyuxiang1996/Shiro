package com.shiro.Controller;

import com.shiro.Entity.LoginResult;
import com.shiro.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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
    public String toLogin() {
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
            request.getSession().setAttribute("username",username);
            return "redirect:index";
        }
        else {
            map.put("msg",loginResult.getResult());
            map.put("username",username);
            return "/login";
        }
    }

    // 欢迎
    @RequestMapping("/hello")
    public String hello() {
        return "/hello";
    }
}
