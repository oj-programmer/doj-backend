package com.doj.server.infrastructure.enums;

/**
 * 类描述：功能模块枚举类
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public enum ItemEnum {

    /**
     * 用户
     */
    USER(1, "com.doj.server.controller.user", "用户管理"),

    /**
     * 算法
     */
    ALGORITHM(2, "com.doj.server.controller.algorithm", "算法管理"),

    /**
     * 论坛
     */
    DISCUSSION(3, "com.doj.server.controller.discussion", "讨论管理"),

    ;

    private final Integer code;

    private final String value;

    private final String description;

    ItemEnum(Integer code, String value, String description) {
        this.code = code;
        this.value = value;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
