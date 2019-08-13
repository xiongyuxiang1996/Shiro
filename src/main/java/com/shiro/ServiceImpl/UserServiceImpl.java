package com.shiro.ServiceImpl;

import com.shiro.Dao.UserRepository;
import com.shiro.Entity.User;
import com.shiro.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/1
 * @Time 11:32
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public List<User> findUserList(@Param(value = "state") int state,
                                   @Param(value = "searchword") String searchword,
                                   @Param(value = "privilege") String privilege,
                                   @Param(value = "timeFrom") String timeFrom,
                                   @Param(value = "timeTo") String timeTo){
        return userRepository.findUserList(state,searchword,privilege,timeFrom,timeTo);
    }
}
