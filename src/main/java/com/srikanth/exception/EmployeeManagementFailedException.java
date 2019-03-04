package com.srikanth.exception;

public class EmployeeManagementFailedException extends Exception {

    private int statusCode;

    public EmployeeManagementFailedException() {
        super();
    }

    public EmployeeManagementFailedException(String message) {
        super(message);
    }

    public EmployeeManagementFailedException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public EmployeeManagementFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeManagementFailedException(int statusCode, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public EmployeeManagementFailedException(Throwable cause) {
        super(cause);
    }



    public int getStatusCode() {
        return statusCode;
    }
}
