package com.doj.server.infrastructure.exception;

/**
 * 类描述：调用第三方业务异常类
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public class ExternalException extends BaseException {


    public ExternalException(String code, String message, String errorMsg) {
        super(code, message, errorMsg);
    }
}
