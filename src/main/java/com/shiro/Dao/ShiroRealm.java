package com.shiro.Dao;

import com.shiro.Entity.Permission;
import com.shiro.Entity.Role;
import com.shiro.Entity.User;
import com.shiro.Service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/1
 * @Time 10:20
 * @Description Realm
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    // 权限信息，包括角色以及权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置.............");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 如果身份认证的时候没有传入User对象，这里只能取到username
        // 也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        User user = (User)principals.getPrimaryPrincipal();

        for(Role role:user.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for(Permission p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    // 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
    protected SimpleAuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("身份认证.............");
        // 获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        // 通过username从数据库中查找 User对象.
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.findByUsername(username);
        if(user == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes(user.getCredentialsSalt()),getName());
        return authenticationInfo;
    }

}