package com.shiro.Dao;

import com.shiro.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
