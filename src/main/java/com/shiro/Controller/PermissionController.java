package com.shiro.Controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/5
 * @Time 17:51
 * @Description
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    /**
     * 权限列表
     * @return
     */
    @RequestMapping("/permissionList")
    @RequiresPermissions("permission:list")
    public String permissionList(){
        return "/menu/permissionList";
    }
}
