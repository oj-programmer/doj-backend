package com.doj.server.infrastructure.utils;

/**
 * 类描述：call-back接口(实现自定义的属性值拷贝)
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
@FunctionalInterface
public interface BeanCopyUtilCallBack<S, T> {

    /**
     * 定义默认回调方法
     *
     * @param t
     * @param s
     */
    void callBack(S t, T s);
}
