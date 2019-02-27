package com.srikanth.dao;

import com.srikanth.model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EmployeeDao {

    Map<String, Employee> employees;// = new HashMap<>();

    public EmployeeDao() {
//        populateEmployees();

        // post support
        employees = new ConcurrentHashMap<String, Employee>();
    }


    public Employee addEmployee(Employee employee) {
        employee.setEmployeeId(UUID.randomUUID().toString());
        employees.put(employee.getEmployeeId(), employee);
        return employee;
    }

    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    public Employee getEmployee(String id) {
        return employees.get(id);
    }


    private void populateEmployees() {
        Employee emp1 = new Employee();
        emp1.setEmployeeId("1");
        emp1.setUsername("emp1");
        emp1.setPassword("pass1");
        emp1.setFullName("Employee One");
        emp1.setDateOfBirth("08/21/1991");
        emp1.setEmailID("eone@email.com");
        emp1.setGender("Male");
        emp1.setSecurityAnswer("First pet?");
        emp1.setSecurityAnswer("dog");

        Employee emp2 = new Employee();
        emp2.setEmployeeId("2");
        emp2.setUsername("emp2");
        emp2.setPassword("pass2");
        emp2.setFullName("Employee Two");
        emp2.setDateOfBirth("06/04/1994");
        emp2.setEmailID("etwo@email.com");
        emp2.setGender("Female");
        emp2.setSecurityAnswer("First pet?");
        emp2.setSecurityAnswer("none");

        employees.put(emp1.getEmployeeId(), emp1);
        employees.put(emp2.getEmployeeId(), emp2);
    }
}
