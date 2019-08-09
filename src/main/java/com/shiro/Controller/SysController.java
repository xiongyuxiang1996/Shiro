package com.shiro.Controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/9
 * @Time 13:09
 * @Description
 */
@Controller
public class SysController {
    // -------------------------------------用户管理-------------------------------------
    /**
     * 用户列表
     * @return
     */
    @RequestMapping("/user/userList")
    @RequiresPermissions("user:list")
    public String userList(){
        return "/frame/user/userList";
    }

    /**
     * 用户添加
     * @return
     */
    @RequestMapping("/user/userAdd")
    @RequiresPermissions("user:add")
    public String userAdd(){
        return "/frame/user/userAdd";
    }

    /**
     * 用户删除
     * @return
     */
    @RequestMapping("/user/userDel")
    @RequiresPermissions("user:del")
    public String userDel(){
        return "/frame/user/userDel";
    }

    // -------------------------------------角色管理-------------------------------------
    /**
     * 角色列表
     * @return
     */
    @RequestMapping("/role/roleList")
    @RequiresPermissions("role:list")
    public String roleList(){
        return "/frame/role/roleList";
    }

    // -------------------------------------权限管理-------------------------------------
    /**
     * 权限列表
     * @return
     */
    @RequestMapping("/permission/permissionList")
    @RequiresPermissions("permission:list")
    public String permissionList(){
        return "/frame/permission/permissionList";
    }

}
