package com.akbank.jpa.exceptions;

public class MyCustomException extends RuntimeException {
  public MyCustomException(String message) {
    super(message);
  }
}
