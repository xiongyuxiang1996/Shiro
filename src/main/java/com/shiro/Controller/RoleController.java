package com.shiro.Controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/5
 * @Time 17:50
 * @Description
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    /**
     * 角色管理
     * @return
     */
    @RequestMapping("/roleList")
    @RequiresPermissions("role:view")
    public String roleList(){
        return "/menu/roleList";
    }
}
