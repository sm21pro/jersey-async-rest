package com.srikanth.service;

import com.srikanth.model.Employee;
import com.srikanth.valueobject.EmployeeServiceResponseVO;

public interface EmployeeService {

    EmployeeServiceResponseVO addEmployee(Employee employee);

    EmployeeServiceResponseVO deleteEmployee(Employee employee);

    EmployeeServiceResponseVO getEmployees();

    EmployeeServiceResponseVO getEmployee(String id);

    EmployeeServiceResponseVO checkLogin(String username, String password);


}
