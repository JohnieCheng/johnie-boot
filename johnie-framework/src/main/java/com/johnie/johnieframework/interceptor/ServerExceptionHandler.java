package com.johnie.johnieframework.interceptor;

import com.johnie.johniecommon.enums.ErrorCode;
import com.johnie.johniecommon.exception.ServerException;
import com.johnie.johnieframework.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 */
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ServerExceptionHandler {
  /**
   * 处理自定义异常
   */
  @ExceptionHandler(ServerException.class)
  public Result<String> handleException(ServerException ex) {

        return Result.error();
    }

    /**
     * SpringMVC参数绑定，Validator校验不正确
     */
    @ExceptionHandler(BindException.class)
    public Result<String> bindException(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        return Result.error(fieldError.getDefaultMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result<String> handleAccessDeniedException(Exception ex) {

    return Result.error(ErrorCode.NO_PERMISSION);
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}