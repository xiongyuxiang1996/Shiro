package com.shiro.Controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    /**
     * 用户查询
     * @return
     */
    @RequestMapping("/userList")
    @RequiresPermissions("user:view")
    public String userInfo(){
        return "/user/userList";
    }
    /**
     * 用户添加
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("user:add")
    public String userInfoAdd(){
        return "/user/userAdd";
    }

    /**
     * 用户删除
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("user:del")
    public String userDel(){
        return "/user/userDel";
    }
}
