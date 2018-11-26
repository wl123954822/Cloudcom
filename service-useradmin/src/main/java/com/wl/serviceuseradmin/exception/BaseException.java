package com.wl.serviceuseradmin.exception;

public class BaseException extends RuntimeException {

    private String errorcode;

    public BaseException(String errorcode ,String message) {
        super(message);
        setErrorcode(errorcode);
    }

    public BaseException(String message ,Throwable cause) {
        super(message,cause);
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }
}
