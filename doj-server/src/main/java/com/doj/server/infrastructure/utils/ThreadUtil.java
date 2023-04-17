package com.doj.server.infrastructure.utils;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.doj.server.dto.user.UserDTO;

/**
 * 类描述：线程工具类
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public class ThreadUtil {

    /**
     * 线程用户信息变量
     */
    private static ThreadLocal<UserDTO> userLocal = new TransmittableThreadLocal<>();

    /**
     * 线程用户sessionId
     */
    private static ThreadLocal<String> sessionIdLocal = new TransmittableThreadLocal<>();

    /**
     * 生成线程请求Id
     */
    private static ThreadLocal<String> threadRequestId = new TransmittableThreadLocal<>();


    /**
     * 设置用户user
     *
     * @param user
     */
    public static void setUser(UserDTO user) {
        userLocal.set(user);
    }

    /**
     * 设置用户sessionId
     *
     * @param sessionId
     */
    public static void setSessionId(String sessionId) {
        sessionIdLocal.set(sessionId);
    }

    /**
     * 清理用户Id
     */
    public static void clearUser() {
        userLocal.remove();
    }

    /**
     * 清理用户sessionId
     */
    public static void clearSessionId() {
        sessionIdLocal.remove();
    }

    /**
     * 清理用户requestId
     */
    public static void clearRequestId() {
        threadRequestId.remove();
    }

    /**
     * 获取用户user
     *
     * @return
     */
    public static UserDTO getUser() {
        return userLocal.get();
    }

    /**
     * 获取sessionId
     *
     * @return
     */
    public static String getSessionId() {
        return sessionIdLocal.get();
    }

    /**
     * 获取线程requestId
     *
     * @return
     */
    public static String getThreadRequestId() {
        return threadRequestId.get();
    }

    /**
     * 设置线程requestId
     *
     * @param requestId
     */
    public static void setThreadRequestId(String requestId) {
        threadRequestId.set(requestId);
    }
}
