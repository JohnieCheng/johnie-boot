package com.johnie.johnieframework.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 异常处理器
 */
@Slf4j
@RestControllerAdvice
public class ServerExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ServerException.class)
    public ResponseEntity<String> handleException(ServerException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ex.getCode()).body(ex.getMessage());
    }

    /**
     * SpringMVC参数绑定，Validator校验不正确
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> bindException(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ErrorCode.PARAM_NOT_VALID.getCode()).body(fieldError.getDefaultMessage());

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ErrorCode.FORBIDDEN.getCode()).body(ErrorCode.FORBIDDEN.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getCode()).body(ErrorCode.INTERNAL_SERVER_ERROR.getMsg());
    }

}