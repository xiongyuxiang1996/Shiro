package com.shiro.ServiceImpl;

import com.shiro.Dao.RoleRepository;
import com.shiro.Entity.Role;
import com.shiro.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/14
 * @Time 16:47
 * @Description
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> findRoleList(){
        return roleRepository.findRoleList();
    }
}
