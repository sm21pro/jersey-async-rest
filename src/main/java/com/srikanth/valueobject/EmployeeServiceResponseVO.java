package com.srikanth.valueobject;

import java.util.Objects;

public class EmployeeServiceResponseVO {

    private String statusCode;
    private String status;
    private String message;
    private Object data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeServiceResponseVO that = (EmployeeServiceResponseVO) o;
        return Objects.equals(statusCode, that.statusCode) &&
                Objects.equals(status, that.status) &&
                Objects.equals(message, that.message) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {

        return Objects.hash(statusCode, status, message, data);
    }

    @Override
    public String toString() {
        return "EmployeeServiceResponseVO{" +
                "statusCode='" + statusCode + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", Data=" + data +
                '}';
    }
}
