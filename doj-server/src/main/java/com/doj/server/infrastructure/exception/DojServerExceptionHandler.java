package com.doj.server.infrastructure.exception;

import com.doj.server.infrastructure.enums.ServiceCodeEnum;
import com.doj.server.infrastructure.enums.SymbolEnum;
import com.doj.server.infrastructure.response.ResultModel;
import com.doj.server.infrastructure.utils.ThreadUtil;
import com.doj.server.strategy.common.ItemCheckStrategyEnum;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 类描述：统一异常处理
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
@Slf4j
@ControllerAdvice
public class DojServerExceptionHandler {
    
    /**
     * 业务异常类统一处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(DojServerException.class)
    public ResponseEntity<ResultModel> handleUserException(DojServerException exception) {
        String requestId = ThreadUtil.getThreadRequestId();
        String code = exception.getCode();
        String message = exception.getMessages();
        String errorMessage = exception.getErrorMsg();

        log.warn("requestId:[{}], DojServerException errorMessage:[{}], DojServerException code:[{}], "
                + "DojServerException handled:[{}] ", requestId, errorMessage, code, message);

        ResultModel error = ResultModel.builder()
                .success(true)
                .code(code)
                .message(message)
                .errorMsg(errorMessage)
                .requestId(requestId)
                .build();
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    /**
     * 调用外部系统业务异常类统一处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(ExternalException.class)
    public ResponseEntity<ResultModel> handleExternalException(ExternalException exception) {
        String requestId = ThreadUtil.getThreadRequestId();
        String code = exception.getCode();
        String message = exception.getMessages();
        String errorMessage = exception.getErrorMsg();

        log.warn("requestId:[{}],ExternalException errorMessage:[{}], ExternalException code:[{}], "
                + "ExternalException handled:[{}] ", requestId, errorMessage, code, message);

        ResultModel error = ResultModel.builder()
                .success(true)
                .code(code)
                .message(message)
                .errorMsg(errorMessage)
                .requestId(requestId)
                .build();
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    /**
     * http请求不支持
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResultModel> handleException(HttpRequestMethodNotSupportedException exception) {
        String requestId = ThreadUtil.getThreadRequestId();
        log.warn("requestId:[{}], HttpRequestMethodNotSupportedException handled:[{}] ",
                requestId, exception.getMessage());

        ResultModel error = ResultModel.builder()
                .success(true)
                .code(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()))
                .errorMsg("不支持" + exception.getMethod() + "请求")
                .requestId(requestId)
                .build();
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 运行时异常处理
     *
     * @param runtimeException
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResultModel> handleError(RuntimeException runtimeException) {
        String requestId = ThreadUtil.getThreadRequestId();
        ResultModel error = ResultModel.builder()
                .success(false)
                .requestId(requestId)
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .errorMsg(runtimeException.getMessage())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();

        log.error("exception handled", runtimeException);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 异常最终处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultModel> handleError(Exception exception) {
        String requestId = ThreadUtil.getThreadRequestId();
        ResultModel error = ResultModel.builder()
                .success(false)
                .requestId(requestId)
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .errorMsg(exception.getMessage())
                .build();

        log.error("exception handled", exception);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 参数转换错误
     *
     * @param req
     * @param response
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultModel> handleMessageNotReadable(
            HttpServletRequest req, HttpServletResponse response, Exception exception) {

        log.warn("exception handled", exception);

        String requestId = ThreadUtil.getThreadRequestId();

        ResultModel error = ResultModel.builder()
                .success(true)
                .requestId(requestId)
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .errorMsg("Bad request")
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * 拦截Hibernate-Validator异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultModel> handleValidatorException(MethodArgumentNotValidException exception) {

        String errorMessage = exception.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(SymbolEnum.COMMA.getValue()));

        return getResultModelResponseEntity(exception.getMessage(), errorMessage);
    }

    /**
     * 拦截Hibernate-Validator异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResultModel> handleValidatorException(BindException exception) {

        String errorMessage = exception.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(SymbolEnum.COMMA.getValue()));

        return getResultModelResponseEntity(exception.getMessage(), errorMessage);
    }

    /**
     * 拦截Hibernate-Validator PathVariable校验异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResultModel> handleConstraintViolationException(ConstraintViolationException exception) {

        ConstraintViolationImpl constraintViolation = (ConstraintViolationImpl) exception
                .getConstraintViolations().toArray()[0];
        String message = constraintViolation.getMessage();
        String rootBeanName = constraintViolation.getRootBeanClass().getName();
        String modulePath = rootBeanName.substring(0, rootBeanName.lastIndexOf(SymbolEnum.DOT.getValue()));
        return getResultModelResponseEntity(modulePath, message);
    }

    private ResponseEntity<ResultModel> getResultModelResponseEntity(String modulePath, String errorMessage) {

        String requestId = ThreadUtil.getThreadRequestId();
        // 策略获取业务码
        ItemCheckStrategyEnum strategy = ItemCheckStrategyEnum.getStrategyByMessage(modulePath);
        ServiceCodeEnum codeEnum = strategy.getArgumentInvalidEnum();
        String code = codeEnum.getModuleCode() + codeEnum.getErrorCode();

        log.warn("requestId:[{}], exception code:[{}], ArgumentNotValidException handled:[{}] ",
                requestId, code, errorMessage);

        ResultModel error = ResultModel.builder()
                .success(true)
                .requestId(requestId)
                .code(code)
                .message(errorMessage)
                .build();

        return new ResponseEntity<>(error, HttpStatus.OK);
    }
}
