package com.starer.util;

import javax.servlet.http.Cookie;
import java.util.Map;

/**
 * @Author: pengxiong
 * @Date: 2023/12/30 13:52:30
 * @Version: V1.0
 * @Description:
 **/
public class CookieUtil {

    /**
     * 从Cookies中取得用户ID和用户角色
     * @param cookies
     * @return 长度为2的字符串数组，第一个元素是用户ID，第二元个素是用户角色
     */
    public static String[] getUserIdAndRole(Cookie[] cookies) {
        String[] strings = new String[2];

        for (Cookie cookie : cookies) {
            if("userId".equals(cookie.getName())) strings[0] = cookie.getValue();
            if("role".equals(cookie.getName())) strings[1] = cookie.getValue();
        }

        return strings;
    }

}
