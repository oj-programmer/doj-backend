package com.doj.server.infrastructure.utils;

import com.doj.server.infrastructure.response.ResultModel;
import org.springframework.http.HttpStatus;

/**
 * 类描述：响应封装
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
public class ResultUtil {

    /**
     * 响应成功
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> ResultModel<T> resultSuccess(T result) {
        String requestId = ThreadUtil.getThreadRequestId();
        ResultModel<T> resultModel = new ResultModel<>();
        resultModel.setResult(result);
        resultModel.setRequestId(requestId);
        resultModel.setCode(String.valueOf(HttpStatus.OK.value()));
        return resultModel;
    }

    /**
     * 不带返回值的响应成功
     *
     * @return
     */
    public static ResultModel resultSuccess() {
        return resultSuccess(null);
    }
}
