package com.shiro.Dao;

import com.shiro.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query(value = "select * from role r " +
            "where if(?1 != -1,r.available = ?1,1 = 1) " +
            "and if(?2 != '',r.role like %?2%,1 = 1)",nativeQuery = true)
    List<Role> findRoleList(@Param(value = "state") int state,
                            @Param(value = "searchword") String searchword);
}
