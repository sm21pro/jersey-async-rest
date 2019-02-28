package com.srikanth.dao;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.srikanth.model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class EmployeeDao {

    private Map<String, Employee> employees;// = new HashMap<>();

    private ListeningExecutorService service;

    public EmployeeDao() {
//        populateEmployees();

        // post support
        employees = new ConcurrentHashMap<String, Employee>();
        service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    }


    public Employee addEmployee(Employee employee) {
        employee.setEmployeeId(UUID.randomUUID().toString());
        employees.put(employee.getEmployeeId(), employee);
        return employee;
    }

    public ListenableFuture<Employee> addEmployeeAsync(final Employee employee) {
        ListenableFuture<Employee> future =
                service.submit(new Callable<Employee>() {
                    @Override
                    public Employee call() throws Exception {
                        return addEmployee(employee);
                    }
                });
        return future;
    }

    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    public ListenableFuture<Collection<Employee>> getEmployeesAsync() {
        ListenableFuture<Collection<Employee>> future =
                service.submit(new Callable<Collection<Employee>>() {
                    @Override
                    public Collection<Employee> call() throws Exception {
                        return getEmployees();
                    }
                });
        return future;
    }

    public Employee getEmployee(String id) {
        return employees.get(id);
    }

    public ListenableFuture<Employee> getEmployeeAsync(final String id) {
        ListenableFuture<Employee> future =
                service.submit(new Callable<Employee>() {
                    @Override
                    public Employee call() throws Exception {
                        return getEmployee(id);
                    }
                });
        return future;
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
