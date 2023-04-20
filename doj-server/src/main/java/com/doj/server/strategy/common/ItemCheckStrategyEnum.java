package com.doj.server.strategy.common;

import com.doj.server.infrastructure.enums.ItemEnum;
import com.doj.server.infrastructure.enums.ServiceCodeEnum;
import com.doj.server.infrastructure.exception.BaseException;
import com.doj.server.infrastructure.exception.DojServerException;

import java.util.Arrays;
import java.util.Objects;

/**
 * 类描述：项目对应路径枚举策略
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public enum ItemCheckStrategyEnum {

    USER(ItemEnum.USER) {
        @Override
        public ServiceCodeEnum getArgumentInvalidEnum() {
            return ServiceCodeEnum.USER_ARGUMENT_INVALID;
        }

        @Override
        public BaseException throwInsertDupException(ServiceCodeEnum codeEnum) {
            return new DojServerException(codeEnum);
        }

        @Override
        public BaseException throwCheckOperateException(ServiceCodeEnum codeEnum) {
            return new DojServerException(codeEnum);
        }
    },


    ALGORITHM(ItemEnum.ALGORITHM) {
        @Override
        public ServiceCodeEnum getArgumentInvalidEnum() {
            return ServiceCodeEnum.ALGORITHM_ARGUMENT_INVALID;
        }

        @Override
        public BaseException throwInsertDupException(ServiceCodeEnum codeEnum) {
            return new DojServerException(codeEnum);
        }

        @Override
        public BaseException throwCheckOperateException(ServiceCodeEnum codeEnum) {
            return new DojServerException(codeEnum);
        }
    },

    DISCUSSION(ItemEnum.DISCUSSION) {
        @Override
        public ServiceCodeEnum getArgumentInvalidEnum() {
            return ServiceCodeEnum.DISCUSSION_ARGUMENT_INVALID;
        }

        @Override
        public BaseException throwInsertDupException(ServiceCodeEnum codeEnum) {
            return new DojServerException(codeEnum);
        }

        @Override
        public BaseException throwCheckOperateException(ServiceCodeEnum codeEnum) {
            return new DojServerException(codeEnum);
        }
    }

    ;


    private final ItemEnum itemEnum;

    ItemCheckStrategyEnum(ItemEnum itemEnum) {
        this.itemEnum = itemEnum;
    }

    /**
     * 根据message获取枚举策略
     *
     * @param message
     * @return
     */
    public static ItemCheckStrategyEnum getStrategyByMessage(String message) {
        return Arrays.stream(ItemCheckStrategyEnum.values())
                .filter(strategyEnum ->
                        message.contains(strategyEnum.itemEnum.getValue()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("根据message获取ItemCheckStrategyEnum策略失败"));
    }

    /**
     * 根据ItemEnum获取枚举策略
     *
     * @param itemEnum
     * @return
     */
    public static ItemCheckStrategyEnum getStrategyByItemEnum(ItemEnum itemEnum) {
        return Arrays.stream(ItemCheckStrategyEnum.values())
                .filter(strategyEnum ->
                        Objects.equals(strategyEnum.itemEnum, itemEnum))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("根据ItemEnum获取ItemCheckStrategyEnum策略失败"));
    }

    /**
     * 获取非法参数业务码枚举
     *
     * @return
     */
    public abstract ServiceCodeEnum getArgumentInvalidEnum();

    /**
     * 抛出数据库唯一索引重复插入异常
     *
     * @param codeEnum
     * @return
     */
    public abstract BaseException throwInsertDupException(com.doj.server.infrastructure.enums.ServiceCodeEnum codeEnum);

    /**
     * 抛出数据库更新/删除失败异常
     *
     * @param codeEnum
     * @return
     */
    public abstract BaseException throwCheckOperateException(ServiceCodeEnum codeEnum);
}
