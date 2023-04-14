package com.doj.server.infrastructure.annotation;

/**
 * 类描述：操作日志
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public @interface Log {

    /**
     * 内容
     *
     * @return
     */
    String title() default "";

    /**
     * 是否打印入参和结果 默认不打印
     * @return
     */
    boolean printParam() default false;
}
