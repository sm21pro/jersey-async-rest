package com.srikanth.service;

import com.google.common.util.concurrent.ListenableFuture;
import com.srikanth.model.Employee;
import com.srikanth.valueobject.EmployeeServiceResponseVO;

public interface EmployeeService {

    ListenableFuture<EmployeeServiceResponseVO> addEmployee(Employee employee);

    ListenableFuture<EmployeeServiceResponseVO> deleteEmployee(Employee employee);

    ListenableFuture<EmployeeServiceResponseVO> getEmployees();

    ListenableFuture<EmployeeServiceResponseVO> getEmployee(String id);

    ListenableFuture<EmployeeServiceResponseVO> checkLogin(String username, String password);


}
