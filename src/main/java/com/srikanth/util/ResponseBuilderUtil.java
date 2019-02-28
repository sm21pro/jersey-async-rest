package com.srikanth.util;

import com.srikanth.model.Employee;
import com.srikanth.valueobject.EmployeeServiceResponseVO;

import java.util.ArrayList;
import java.util.Collection;

public class ResponseBuilderUtil {

    private static final String SUCCESS = "Success";
    private static final String FAILURE = "failure";

    public static EmployeeServiceResponseVO getResponse(String responseCode, Object employeesObj, String message) {
        EmployeeServiceResponseVO responseVO = new EmployeeServiceResponseVO();
        responseVO.setStatusCode(responseCode);

        if ("200".equalsIgnoreCase(responseCode)) {
            responseVO.setStatus(SUCCESS);
        } else {
            responseVO.setStatus(FAILURE);
        }

        responseVO.setMessage(message);

        if (employeesObj != null) {
            if (employeesObj instanceof Collection) {
                responseVO.setData((Collection<Employee>) employeesObj);
            } else if (employeesObj instanceof Employee) {
                responseVO.setData((Employee) employeesObj);
            }
        } else {
            responseVO.setData(new ArrayList<>());
        }
        return responseVO;
    }

    public static EmployeeServiceResponseVO getResponse(String responseCode, String messageToUser, Exception exception) {
        EmployeeServiceResponseVO responseVO = new EmployeeServiceResponseVO();
        responseVO.setStatusCode(responseCode);
        responseVO.setStatus(FAILURE);
        responseVO.setMessage(messageToUser != null ? messageToUser : exception.getMessage());
        responseVO.setData(new ArrayList<>());
        return responseVO;
    }

}
