package com.shiro.Controller;

import com.shiro.Entity.Result;
import com.shiro.Entity.Role;
import com.shiro.Service.RoleService;
import com.shiro.Util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/5
 * @Time 17:50
 * @Description
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    // 获取角色列表
    @RequiresPermissions("role:list")
    @RequestMapping(value = "/getRoleList",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> getUserList() {
        List<Role> roleList = roleService.findRoleList();
        return ResultUtil.success("获取角色列表成功",roleList);
    }
}
