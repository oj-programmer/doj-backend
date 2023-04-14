package com.doj.server.infrastructure.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 类描述：通用返回
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultModel<T> implements Serializable {

    /**
     * 返回成功
     */
    private Boolean success;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 业务码
     */
    private String code;

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 信息
     */
    private String message;

    /**
     * 返回对象
     */
    private T result;

}
