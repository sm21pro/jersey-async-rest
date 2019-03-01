package com.srikanth.service.impl;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.srikanth.dao.EmployeeDao;
import com.srikanth.exception.EmployeeManagementFailedException;
import com.srikanth.exception.EmployeeNotFoundException;
import com.srikanth.model.Employee;
import com.srikanth.service.EmployeeService;
import com.srikanth.util.ResponseBuilderUtil;
import com.srikanth.valueobject.EmployeeServiceResponseVO;
import org.hibernate.HibernateException;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class EmployeeServiceAsyncImpl implements EmployeeService {

    private ListeningExecutorService service;

    private EmployeeDao dao;

    /**
     * Instantiates Service impl bean with dependencies.
     */
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
                            return ResponseBuilderUtil.getResponse(200, employee, "Employee data inserted successfully.");
                        } catch (PersistenceException ex) {
                            if (ex.getMessage() != null && ex.getMessage().contains("ConstraintViolationException")) {
                                throw new EmployeeManagementFailedException(400, "Duplicate employee details! Please provide unique data", ex);
                            }
                            throw new EmployeeManagementFailedException(500, "Failed adding employee data! Please check log", ex);
                        }
                    }
                });
        return future;

    }

    @Override
    public ListenableFuture<EmployeeServiceResponseVO> deleteEmployee(final String empId) {
        ListenableFuture<EmployeeServiceResponseVO> future =
                service.submit(new Callable<EmployeeServiceResponseVO>() {
                    @Override
                    public EmployeeServiceResponseVO call() throws Exception {
                        try {
                            Employee emp = dao.deleteEmployee(empId);
                            if (emp == null) {
                                throw new EmployeeManagementFailedException(400, "Failed deleting employee data. No Employee with ID passed");
                            }
                            return ResponseBuilderUtil.getResponse(200, null, "Employee data deleted successfully.");
                        } catch (HibernateException ex) {
                            throw new EmployeeManagementFailedException(500, ex.getMessage(), ex);
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
                            return ResponseBuilderUtil.getResponse(200, employees, (employees.isEmpty() ? "No Employees data exist" : ""));
                        } catch (HibernateException ex) {
                            throw new EmployeeNotFoundException(500, "Failed fetching employee data. Please check log.", ex);
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
                            if (employee == null) {
                                throw new EmployeeNotFoundException(400, "No employee with data passed!");
                            }
                            return ResponseBuilderUtil.getResponse(200, employee, "");
                        } catch (HibernateException ex) {
                            throw new EmployeeNotFoundException(500, "Failed fetching employee data. Please check log.", ex);
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
                                throw new EmployeeNotFoundException(401, "Invalid Username and Password!");
                            } else {
                                // login successful
                                return ResponseBuilderUtil.getResponse(200, null, "Employee has authenticated successfully");
                            }
                        } catch (HibernateException ex) {
                            throw new EmployeeNotFoundException(500, "Authentication failure!", ex);
                        }
                    }
                });
        return future;
    }
}
