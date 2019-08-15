package com.shiro.Controller;

import com.shiro.Entity.Permission;
import com.shiro.Entity.Result;
import com.shiro.Service.PermissionService;
import com.shiro.Util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<Object> getPermissionList(@RequestParam(value = "state",required = true)int state) {
        List<Permission> roleList = permissionService.findPermissionList(state);
        return ResultUtil.success("获取权限列表成功",roleList);
    }

    // 权限删除
    @RequiresPermissions("permission:del")
    @RequestMapping(value = "/deletePermission",method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> deletePermission(@RequestParam(value = "permissionId",required = true)int permissionId) {
        permissionService.deleteByPermissionId(permissionId);
        return ResultUtil.success("权限删除成功");
    }
}
