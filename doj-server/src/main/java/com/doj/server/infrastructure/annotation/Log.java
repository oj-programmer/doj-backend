package com.doj.server.infrastructure.annotation;

import org.redisson.api.annotation.REntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类描述：操作日志
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
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
