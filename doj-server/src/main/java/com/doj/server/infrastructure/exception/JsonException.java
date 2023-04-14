package com.doj.server.infrastructure.exception;

/**
 * JSON解析异常
 *
 * @author kongweikun@163.com
 * @date 2023/4/13
 */
public class JsonException extends RuntimeException{

    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonException(Throwable cause) {
        super(cause);
    }
}
