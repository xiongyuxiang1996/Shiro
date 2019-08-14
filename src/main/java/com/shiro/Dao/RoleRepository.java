package com.shiro.Dao;

import com.shiro.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/14
 * @Time 16:47
 * @Description
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query(value = "select * from role r",nativeQuery = true)
    List<Role> findRoleList();
}
