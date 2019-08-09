package com.shiro.Controller;

import com.shiro.Entity.Result;
import com.shiro.Entity.User;
import com.shiro.Service.UserService;
import com.shiro.Util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/1
 * @Time 13:31
 * @Description
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    // 获取用户列表
    @RequiresPermissions("user:list")
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> getUserList() {
        List<User> userList = userService.findAll();
        return ResultUtil.success("获取用户列表成功",userList);
    }

}
