package com.doj.server.infrastructure.enums;

/**
 * 类描述：业务错误码
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public enum ServiceCodeEnum {

    // 用户
    USER_AUTH_NO_AUTH("用户管理", "权限管理", "100", "001", "无权限", ""),
    USER_NOT_EXIST("用户管理", "业务管理", "101", "001", "用户不存在", ""),
    USER_EXIST("用户管理", "业务管理", "101", "002", "用户存在", ""),

    // 算法

    // 帖子
    ;


    private final String item;
    private final String module;
    private final String moduleCode;
    private final String errorCode;
    private final String message;
    private final String errorMsg;


    ServiceCodeEnum(String item, String module, String moduleCode,
                    String errorCode, String message, String errorMsg) {
        this.item = item;
        this.module = module;
        this.moduleCode = moduleCode;
        this.errorCode = errorCode;
        this.message = message;
        this.errorMsg = errorMsg;
    }

    public String getItem() {
        return this.item;
    }

    public String getModule() {
        return this.module;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public String getMessage() {
        return this.message;
    }
}
