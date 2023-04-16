package com.doj.server.infrastructure.exception;

import com.doj.server.infrastructure.enums.ServiceCodeEnum;

/**
 * 类描述：业务后端服务自定义异常
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public class DojServerException extends BaseException {


    public DojServerException(ServiceCodeEnum serviceCodeEnum) {
        this(serviceCodeEnum, serviceCodeEnum.getMessage());
    }

    public DojServerException(ServiceCodeEnum serviceCodeEnum, String errorMsg) {

        super(serviceCodeEnum.getItem(), serviceCodeEnum.getModule(),
                serviceCodeEnum.getModuleCode(), serviceCodeEnum.getErrorCode(),
                serviceCodeEnum.getMessage(), errorMsg);
    }
}
