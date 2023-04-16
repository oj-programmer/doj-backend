package com.doj.server.infrastructure.annotation;

import com.doj.server.infrastructure.enums.ItemEnum;
import com.doj.server.infrastructure.enums.ServiceCodeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类描述：数据库插入检测重复
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InsertDup {

    /**
     * 所属模块
     *
     * @return
     */
    ItemEnum item();

    /**
     * 业务异常码
     *
     * @return
     */
    ServiceCodeEnum serviceCodeEnum();

}
