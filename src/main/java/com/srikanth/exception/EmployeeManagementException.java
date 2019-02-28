package com.srikanth.exception;

public class EmployeeManagementException extends Exception {

    public EmployeeManagementException() {
        super();
    }

    public EmployeeManagementException(String message) {
        super(message);
    }

    public EmployeeManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeManagementException(Throwable cause) {
        super(cause);
    }

    protected EmployeeManagementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
