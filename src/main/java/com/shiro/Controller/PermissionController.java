package com.shiro.Controller;

import com.shiro.Entity.Permission;
import com.shiro.Entity.Result;
import com.shiro.Service.PermissionService;
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
 * @Time 17:51
 * @Description
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    // 获取权限列表
    @RequiresPermissions("permission:list")
    @RequestMapping(value = "/getPermissionList",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> getUserList() {
        List<Permission> roleList = permissionService.findPermissionList();
        return ResultUtil.success("获取权限列表成功",roleList);
    }
}
