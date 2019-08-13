package com.shiro.Service;

import com.shiro.Entity.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/1
 * @Time 11:31
 * @Description
 */
public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
    List<User> findUserList(@Param(value = "state") int state,
                            @Param(value = "searchword") String searchword,
                            @Param(value = "privilege") String privilege,
                            @Param(value = "timeFrom") String timeFrom,
                            @Param(value = "timeTo") String timeTo);
}

