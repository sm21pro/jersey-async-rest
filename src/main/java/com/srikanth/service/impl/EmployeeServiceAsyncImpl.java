package com.srikanth.service.impl;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.srikanth.dao.EmployeeDao;
import com.srikanth.exception.EmployeeManagementException;
import com.srikanth.model.Employee;
import com.srikanth.service.EmployeeService;
import com.srikanth.util.ResponseBuilderUtil;
import com.srikanth.valueobject.EmployeeServiceResponseVO;

import javax.ws.rs.core.Context;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class EmployeeServiceAsyncImpl implements EmployeeService {

    private ListeningExecutorService service;

    private EmployeeDao dao;


    public EmployeeServiceAsyncImpl() {
        this.service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        this.dao = new EmployeeDao();
    }

    @Override
    public ListenableFuture<EmployeeServiceResponseVO> addEmployee(final Employee employee) {

        ListenableFuture<EmployeeServiceResponseVO> future =
                service.submit(new Callable<EmployeeServiceResponseVO>() {
                    @Override
                    public EmployeeServiceResponseVO call() throws Exception {
                        try {
                            dao.addEmployee(employee);
                            return ResponseBuilderUtil.getResponse("200", null, "Employee data inserted successfully.");
                        } catch (EmployeeManagementException ex) {
                            return ResponseBuilderUtil.getResponse("500", "Failed inserting employee data. Please check log.", ex);
                        }
                    }
                });
        return future;

    }

    @Override
    public ListenableFuture<EmployeeServiceResponseVO> deleteEmployee(final Employee employee) {
        ListenableFuture<EmployeeServiceResponseVO> future =
                service.submit(new Callable<EmployeeServiceResponseVO>() {
                    @Override
                    public EmployeeServiceResponseVO call() throws Exception {
                        try {
                            dao.deleteEmployee(employee);
                            return ResponseBuilderUtil.getResponse("200", null, "Employee data deleted successfully.");
                        } catch (EmployeeManagementException ex) {
                            return ResponseBuilderUtil.getResponse("500", "Failed deleting employee data. Please check log.", ex);
                        }
                    }
                });
        return future;
    }

    @Override
    public ListenableFuture<EmployeeServiceResponseVO> getEmployees() {
        ListenableFuture<EmployeeServiceResponseVO> future =
                service.submit(new Callable<EmployeeServiceResponseVO>() {
                    @Override
                    public EmployeeServiceResponseVO call() throws Exception {
                        try {
                            Collection<Employee> employees = dao.getEmployees();
                            return ResponseBuilderUtil.getResponse("200", employees, "");
                        } catch (EmployeeManagementException ex) {
                            return ResponseBuilderUtil.getResponse("500", "Failed fetching employees data. Please check log.", ex);
                        }
                    }
                });
        return future;
    }

    @Override
    public ListenableFuture<EmployeeServiceResponseVO> getEmployee(final String id) {
        ListenableFuture<EmployeeServiceResponseVO> future =
                service.submit(new Callable<EmployeeServiceResponseVO>() {
                    @Override
                    public EmployeeServiceResponseVO call() throws Exception {
                        try {
                            Employee employee = dao.getEmployee(id);
                            return ResponseBuilderUtil.getResponse("200", employee, "");
                        } catch (EmployeeManagementException ex) {
                            return ResponseBuilderUtil.getResponse("500", "Failed fetching employee data. Please check log.", ex);
                        }
                    }
                });
        return future;
    }

    @Override
    public ListenableFuture<EmployeeServiceResponseVO> checkLogin(final String username, final String password) {
        ListenableFuture<EmployeeServiceResponseVO> future =
                service.submit(new Callable<EmployeeServiceResponseVO>() {
                    @Override
                    public EmployeeServiceResponseVO call() throws Exception {
                        try {
                            Collection<Employee> employees = dao.getEmployee(username, password);
                            if (employees.isEmpty()) {
                                // throw error
                                return ResponseBuilderUtil.getResponse("404", null, "Invalid Username and Password");
                            } else {
                                // login successful
                                return ResponseBuilderUtil.getResponse("200", null, "Employee has authenticated successfully");
                            }
                        } catch (EmployeeManagementException ex) {
                            return ResponseBuilderUtil.getResponse("500", "Failed fetching employee data. Please check log.", ex);
                        }
                    }
                });
        return future;
    }
}
