package com.doj.server.infrastructure.exception;

/**
 * 类描述：基础异常类
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public class BaseException extends RuntimeException{

    /**
     * 所属项目
     */
    private String item;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 统一异常信息
     */
    private String message;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 业务错误码
     */
    private String code;

    public BaseException(String item, String module, String moduleCode,
                         String errorCode, String message, String errorMsg) {
        super(errorMsg);
        this.item = item;
        this.module = module;
        this.message = message;
        this.errorMsg = errorMsg;
        setCode(moduleCode + errorCode);
    }

    public BaseException(String code, String message, String errorMsg) {
        super(errorMsg);
        this.message = message;
        this.errorMsg = errorMsg;
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessages() {
        return this.message;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}
