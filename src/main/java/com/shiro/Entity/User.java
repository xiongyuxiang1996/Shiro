package com.shiro.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/1
 * @Time 10:17
 * @Description
 */
@Entity
public class User {
    @Id
    @GenericGenerator(name="generator",strategy = "native")
    @GeneratedValue(generator = "generator")
    private Integer uid;
    @Column(nullable = false)
    private String privilege;           // 权限名称（管理员等）
    @Column(nullable = false, unique = true)
    private String username;            // 登录用户名
    @Column(nullable = false)
    private String password;            // 登陆密码
    private String salt;                // 加密密码的盐
    private byte state;                 // 用户状态（0：创建未认证；1：正常状态；2：用户被锁定）
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;            // 创建时间

    @ManyToMany(fetch= FetchType.EAGER) // 立即从数据库中进行加载数据;
    @JoinTable(name = "UserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    @JsonIgnore
    private List<Role> roleList;        // 一个用户具有多个角色

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    /**
     * 密码盐. 重新对盐重新进行了定义，用户名+salt，这样就不容易被破解，可以采用多种方式定义加盐
     * @return
     */
    @JsonIgnore
    public String getCredentialsSalt(){
        return this.username + this.salt;
    }

}