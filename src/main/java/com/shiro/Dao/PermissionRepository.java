package com.shiro.Dao;

import com.shiro.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/14
 * @Time 17:43
 * @Description
 */
public interface PermissionRepository extends JpaRepository<Permission,Integer> {
    @Query(value = "select * from permission p " +
            "where if(?1 != -1,p.available = ?1,1 = 1)",nativeQuery = true)
    List<Permission> findPermissionList(@Param(value = "state") int state);

    @Modifying
    @Query(value = "update permission set available = 1 where permissionId = ?1 or (?1) IN parentId",nativeQuery = true)
    void deleteByPermissionId(@Param(value = "permissionId") int permissionId);
}
