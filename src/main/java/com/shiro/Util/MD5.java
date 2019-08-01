package com.shiro.Util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author xiongyuxiang xiongyuxiang1996@163.com
 * @Date 2019/8/1
 * @Time 16:52
 * @Description 模拟用户注册时加密步骤，计算加密后密码
 */
public class MD5 {
    public static void main(String args[]){
        String username = "test";
        String password = "123456";
        String salt = "s6a54d3aw4d81c32as1d98a4w3w98e4d";
        // 散列1次
        System.out.println(MD5Tools.getMD5Str(username + salt, password, 1));

        // 散列2次
        System.out.println(MD5Tools.getMD5Str(username + salt, password, 2));

        Object result = new SimpleHash("MD5",password,username + salt,2);
        System.out.println(result.toString());
    }
}
