package com.shiro.ServiceImpl;

import com.shiro.Dao.PermissionRepository;
import com.shiro.Entity.Permission;
import com.shiro.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/14
 * @Time 17:43
 * @Description
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Override
    public List<Permission> findPermissionList(){
        return permissionRepository.findPermissionList();
    }
}
