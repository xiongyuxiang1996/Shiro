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
import org.springframework.web.bind.annotation.RequestParam;
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
    public Result<Object> getUserList(@RequestParam(value = "state",required = true)int state,
                                      @RequestParam(value = "searchword",required = false)String searchword,
                                      @RequestParam(value = "privilege",required = true)String privilege,
                                      @RequestParam(value = "timeFrom",required = false)String timeFrom,
                                      @RequestParam(value = "timeTo",required = false)String timeTo) {
        List<User> userList = userService.findUserList(state,searchword,privilege,timeFrom,timeTo);
        return ResultUtil.success("获取用户列表成功",userList);
    }

}
