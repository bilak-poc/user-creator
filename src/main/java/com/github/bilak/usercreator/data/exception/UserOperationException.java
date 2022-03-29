package com.github.bilak.usercreator.data.exception;

public class UserOperationException extends RuntimeException {

  public UserOperationException() {
  }

  public UserOperationException(String message) {
    super(message);
  }

  public UserOperationException(String message, Throwable cause) {
    super(message, cause);
  }
}
