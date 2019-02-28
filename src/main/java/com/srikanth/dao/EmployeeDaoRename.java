package com.srikanth.dao;

import com.srikanth.exception.EmployeeManagementException;
import com.srikanth.model.Employee;
import com.srikanth.util.HibernateSessionUtil;
import com.srikanth.util.JerseyAppConstants;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeeDaoRename {

    private static final Logger logger = Logger.getLogger(EmployeeDaoRename.class);


    public Employee addEmployee(Employee employee) throws EmployeeManagementException {
        logger.info("Persisting employee to database");
        Session session = null;
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = HibernateSessionUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        } catch (HibernateException ex) {
            logger.error("Failed saving employee. Rolling back the transaction");
            transaction.rollback();
            throw new EmployeeManagementException("Exception during database transaction" + ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employee;
    }

    public Collection<Employee> getEmployees() throws EmployeeManagementException {
        logger.info("Fetching all employees");
        List<Employee> employeeList = new ArrayList<>();
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateSessionUtil.getSessionFactory();
            session = sessionFactory.openSession();
            employeeList = session.createQuery("from Employee").list();
        } catch (HibernateException ex) {
            logger.error("Failed fetching employees list\n" + ex);
            throw new EmployeeManagementException("Exception during database transaction" + ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employeeList;
    }

    public Collection<Employee> getEmployeeWithCredentials(String username, String password) throws EmployeeManagementException {
        logger.info("Fetching employee with username " + username);
        List<Employee> employeeList = new ArrayList<>();
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateSessionUtil.getSessionFactory();
            session = sessionFactory.openSession();
            employeeList = session.createNamedQuery(JerseyAppConstants.EMPLOYEE_WITH_USERNAME_PASSWORD_NQ).list();
        } catch (HibernateException ex) {
            logger.error("Failed fetching employees list\n" + ex);
            throw new EmployeeManagementException("Exception during database transaction" + ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employeeList;
    }


    public Employee getEmployee(String id) throws EmployeeManagementException {
        logger.info("Fetching Employee using employee id " + id);
        Employee employee = null;
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateSessionUtil.getSessionFactory();
            session = sessionFactory.openSession();
            employee = session.get(Employee.class, id);
        } catch (HibernateException ex) {
            logger.error("Failed fetching employee\n" + ex);
            throw new EmployeeManagementException("Exception during database transaction" + ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employee;
    }
}
