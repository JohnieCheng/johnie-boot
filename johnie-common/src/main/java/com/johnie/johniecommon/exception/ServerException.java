package com.johnie.johniecommon.exception;

import com.johnie.johniecommon.enums.ErrorCode;
import java.io.Serial;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** 自定义异常 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerException extends RuntimeException {
  @Serial private static final long serialVersionUID = 1L;
  private int code;
  private String msg;

  public ServerException(String msg) {
    super(msg);
    this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
    this.msg = msg;
  }

  public ServerException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.code = errorCode.getCode();
    this.msg = errorCode.getMessage();
  }

  public ServerException(String msg, Throwable e) {
    super(msg, e);
    this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
    this.msg = msg;
  }
}
