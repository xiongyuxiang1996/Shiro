package com.shiro.Service;

import com.shiro.Entity.Permission;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/14
 * @Time 17:43
 * @Description
 */
@Transactional
public interface PermissionService {
    List<Permission> findPermissionList(@Param(value = "state") int state);
    void deleteByPermissionId(@Param(value = "permissionId") int permissionId);
}
