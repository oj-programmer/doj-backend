package com.doj.server.infrastructure.aspect;

import com.doj.server.dto.user.UserDTO;
import com.doj.server.infrastructure.annotation.Log;
import com.doj.server.infrastructure.constant.SystemConstant;
import com.doj.server.infrastructure.enums.SymbolEnum;
import com.doj.server.infrastructure.utils.JsonUtil;
import com.doj.server.infrastructure.utils.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Optional;

/**
 * 类描述: 日志切面
 *
 * @author kongweikun@163.com
 * @date 2023/4/16
 */
@Slf4j
@Order(1)
@Aspect
@Component
public class LogAspect {


    @Around("@annotation(commonLog)")
    public Object logAround(ProceedingJoinPoint pjp, Log commonLog) throws Throwable {

        String requestId = ThreadUtil.getThreadRequestId();
        String username = Optional.ofNullable(ThreadUtil.getUser())
                .map(UserDTO::getUsername)
                .orElse(SystemConstant.EMPTY_STR);

        // 方法名
        String className = pjp.getTarget().getClass().getName();
        String methodName = className + SymbolEnum.DOT.getValue() + pjp.getSignature().getName();

        // 参数打印
        String title = commonLog.title();
        boolean printParam = commonLog.printParam();
        String methodStartLog = String.format("business: [%s]. begin execute method: [%s]. requestId: [%s], " +
                "username: [%s].", title, methodName, requestId, username);
        if (printParam) {
            Object[] args = pjp.getArgs();
            methodStartLog = methodName + String.format(" param: [%s].", JsonUtil.serialize(args));
        }

        // 日志输出
        log.info(methodStartLog);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = pjp.proceed();
        stopWatch.stop();
        log.info("business: [{}]. finish execute method: [{}]. requestId: [{}], username: [{}], cost: [{}] ms",
                title, methodName, requestId, username, stopWatch.getTotalTimeMillis());

        return proceed;
    }
}
