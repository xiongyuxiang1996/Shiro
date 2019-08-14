package com.shiro.Service;

import com.shiro.Entity.Role;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/14
 * @Time 16:47
 * @Description
 */
public interface RoleService {
    List<Role> findRoleList(@Param(value = "state") int state,
                            @Param(value = "searchword") String searchword);
}
