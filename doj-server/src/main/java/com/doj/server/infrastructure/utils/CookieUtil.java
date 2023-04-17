package com.doj.server.infrastructure.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * 类描述: Cookie Util
 *
 * @author kongweikun@163.com
 * @date 2023/4/17
 */
public class CookieUtil {

    /**
     * 获取 cookie 某个key 的 value
     * @param request
     * @param name
     * @return
     */
    public static String getValue(HttpServletRequest request, String name) {
        if (EmptyUtil.isEmpty(request) || EmptyUtil.isEmpty(name)) {
            throw new IllegalArgumentException("参数为空");
        }

        if (EmptyUtil.isEmpty(request.getCookies())) {
            return null;
        }
        return Arrays.stream(request.getCookies())
                .filter(cookie -> Objects.equals(cookie.getName(), name))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow();
    }
}
