package com.doj.server.infrastructure.interceptor;

import com.doj.server.infrastructure.constant.SystemConstant;
import com.doj.server.infrastructure.utils.EmptyUtil;
import com.doj.server.infrastructure.utils.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 类描述: 请求拦截器
 *
 * @author kongweikun@163.com
 * @date 2023/4/18
 */
@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = request.getHeader(SystemConstant.REQUEST_ID);
        if (EmptyUtil.isEmpty(request)) {
            requestId = UUID.randomUUID().toString();
        }
        ThreadUtil.setThreadRequestId(requestId);
        log.info("success generate requestId: [{}]", requestId);
        MDC.put("requestId", requestId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadUtil.clearRequestId();
        MDC.clear();
        log.info("清理 requestId 成功...");
    }
}
