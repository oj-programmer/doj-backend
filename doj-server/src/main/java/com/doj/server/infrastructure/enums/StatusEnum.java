package com.doj.server.infrastructure.enums;

/**
 * 类描述: 状态枚举类
 *
 * @author kongweikun@163.com
 * @date 2023/4/17
 */
public enum StatusEnum {
    NORMAL(0, "可用"),

    DELETED(1, "不可用")
    ;


    private final Integer value;

    private final String desc;

    StatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getValue() {
        return value;
    }
}
