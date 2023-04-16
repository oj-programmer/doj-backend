package com.doj.server.infrastructure.config.aspect;

import com.doj.server.infrastructure.annotation.InsertDup;
import com.doj.server.infrastructure.constant.SystemConstant;
import com.doj.server.infrastructure.enums.ItemEnum;
import com.doj.server.infrastructure.enums.ServiceCodeEnum;
import com.doj.server.infrastructure.utils.EmptyUtil;
import com.doj.server.strategy.common.ItemCheckStrategyEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 类描述：数据库插入检测重复AOP
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
@Slf4j
@Aspect
@Component
public class InsertDupAspect {

    /**
     * 数据库插入重复
     */
    @AfterThrowing(value = "@annotation(insertDup)", throwing = "exception")
    public void insertDupAfterThrowing(InsertDup insertDup, Throwable exception) {

        Throwable cause = exception.getCause();
        if (cause instanceof SQLIntegrityConstraintViolationException) {
            String errMsg = cause.getMessage();
            ItemEnum itemEnum = insertDup.item();
            ServiceCodeEnum serviceCodeEnum = insertDup.serviceCodeEnum();
            if (!EmptyUtil.isEmpty(errMsg) && errMsg.contains(SystemConstant.UK_PREFIX)) {
                ItemCheckStrategyEnum strategy = ItemCheckStrategyEnum.getStrategyByItemEnum(itemEnum);
                strategy.throwInsertDupException(serviceCodeEnum);
            }
        }
    }

}
