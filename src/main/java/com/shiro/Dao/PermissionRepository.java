package com.shiro.Dao;

import com.shiro.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    @Query(value = "select * from permission p",nativeQuery = true)
    List<Permission> findPermissionList();
}
