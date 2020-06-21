package com.leyou.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: 98050
 * @Time: 2018-10-23 10:49
 * @Feature: 密码加密
 */
public class CodecUtils {

    public static String passwordBcryptEncode(String username, String password) {

        return new BCryptPasswordEncoder().encode(username + password);
    }

    public static Boolean passwordConfirm(String rawPassword, String encodePassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodePassword);
    }

    /**
     * 1 密码加密工具类
     */
    public static String passwordAndUserNmaeEncode(String userName, String passsword) {
        return new BCryptPasswordEncoder().encode(userName + passsword);
    }

    /**
     * 2 新旧密码确认工具类
     */
    public static Boolean confirmPasword(String oldPassword, String newPassword) {
        return new BCryptPasswordEncoder().matches(oldPassword, newPassword);
    }
}
