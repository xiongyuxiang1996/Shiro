package com.shiro.Dao;

import com.shiro.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/1
 * @Time 11:30
 * @Description
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    @Query(value = "select * from user u " +
            "where if(?1 != -1,u.state = ?1,1 = 1) " +
            "and if(?2 != '',u.username like %?2%,1 = 1) " +
            "and if(?3 != '全部',u.privilege = ?3,1 = 1) " +
            "and if(?4 != '',u.createTime >= ?4,1 = 1) " +
            "and if(?5 != '',u.createTime <= ?5,1 = 1)",nativeQuery = true)
    List<User> findUserList(@Param(value = "state") int state,
                            @Param(value = "searchword") String searchword,
                            @Param(value = "privilege") String privilege,
                            @Param(value = "timeFrom") String timeFrom,
                            @Param(value = "timeTo") String timeTo);
}
