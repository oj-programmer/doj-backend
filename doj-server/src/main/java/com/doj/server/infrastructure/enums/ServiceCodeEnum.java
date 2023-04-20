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
    USER_ARGUMENT_INVALID("用户管理", "参数管理", "101", "001", "参数非法", ""),
    USER_NOT_EXIST("用户管理", "业务管理", "102", "001", "用户不存在", ""),
    USER_EXIST("用户管理", "业务管理", "102", "002", "用户存在", ""),
    USER_TICKET_EXPIRED("用户管理", "业务管理", "102", "003", "ticket已过期", ""),

    // 算法
    ALGORITHM_AUTH_NOT_AUTH("算法管理", "权限管理", "200", "001", "无权限", ""),
    ALGORITHM_ARGUMENT_INVALID("算法管理", "参数管理", "201", "001", "参数非法", ""),

    // 帖子
    DISCUSSION_AUTH_NOT_AUTH("帖子管理", "权限管理", "300", "001", "无权限", ""),
    DISCUSSION_ARGUMENT_INVALID("帖子管理", "参数管理", "301", "001", "参数非法", "")
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
