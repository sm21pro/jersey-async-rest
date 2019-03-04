package com.srikanth.exception;

public class EmployeeNotFoundException extends Exception {

    private int statusCode;

    public EmployeeNotFoundException() {
        super();
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeNotFoundException(int statusCode, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public EmployeeNotFoundException(Throwable cause) {
        super(cause);
    }



    public int getStatusCode() {
        return statusCode;
    }
}
