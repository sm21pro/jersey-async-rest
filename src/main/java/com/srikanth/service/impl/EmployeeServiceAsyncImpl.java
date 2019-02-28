package com.srikanth.service.impl;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.srikanth.model.Employee;
import com.srikanth.service.EmployeeService;
import com.srikanth.valueobject.EmployeeServiceResponseVO;

public class EmployeeServiceAsyncImpl implements EmployeeService {

    private ListeningExecutorService service;

    @Override
    public EmployeeServiceResponseVO addEmployee(Employee employee) {
        return null;
    }

    @Override
    public EmployeeServiceResponseVO deleteEmployee(Employee employee) {
        return null;
    }

    @Override
    public EmployeeServiceResponseVO getEmployees() {
        return null;
    }

    @Override
    public EmployeeServiceResponseVO getEmployee(String id) {
        return null;
    }

    @Override
    public EmployeeServiceResponseVO checkLogin(String username, String password) {
        return null;
    }
}
