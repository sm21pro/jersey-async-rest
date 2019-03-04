package com.srikanth.dao;

import com.srikanth.exception.EmployeeNotFoundException;
import com.srikanth.model.Employee;
import com.srikanth.util.HibernateSessionUtil;
import com.srikanth.util.JerseyAppConstants;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class EmployeeDao {

    private static final Logger logger = Logger.getLogger(EmployeeDao.class);


    public Employee addEmployee(Employee employee) {
        logger.info("Persisting employee to database");
        Session session = null;
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = HibernateSessionUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            employee.setEmployeeId(UUID.randomUUID().toString().split("-")[0]);
            session.persist(employee);
            transaction.commit();
        } catch (PersistenceException ex) {
            logger.error("Failed saving employee. Rolling back the transaction");
            transaction.rollback();
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employee;
    }

//    public Employee deleteEmployee(Employee employee) {
//        logger.info("Deleting employee from database");
//        Session session = null;
//        Transaction transaction = null;
//        try {
//            SessionFactory sessionFactory = HibernateSessionUtil.getSessionFactory();
//            session = sessionFactory.openSession();
//            transaction = session.beginTransaction();
//            session.delete(employee);
//            transaction.commit();
//        } catch (HibernateException ex) {
//            logger.error("Failed deleting employee. Rolling back the transaction");
//            transaction.rollback();
//            throw ex;
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return employee;
//    }

    public Employee deleteEmployee(String employeeId) {
        logger.info("Deleting employee from database");
        Session session = null;
        Transaction transaction = null;
        Employee employeeToDel = null;
        try {
            SessionFactory sessionFactory = HibernateSessionUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            employeeToDel = session.get(Employee.class, employeeId);
            if (employeeToDel != null) {
                session.delete(employeeToDel);
                transaction.commit();
            }
        } catch (HibernateException ex) {
            logger.error("Failed deleting employee. Rolling back the transaction");
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employeeToDel;
    }

    public Collection<Employee> getEmployees() {
        logger.info("Fetching all employees");
        List<Employee> employeeList = new ArrayList<>();
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateSessionUtil.getSessionFactory();
            session = sessionFactory.openSession();
            employeeList = session.createQuery("from Employee").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employeeList;
    }

    public Collection<Employee> getEmployee(String username, String password) {
        logger.info("Fetching employee with username " + username);
        List<Employee> employeeList = new ArrayList<>();
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateSessionUtil.getSessionFactory();
            session = sessionFactory.openSession();
            Query query = session.createNamedQuery(JerseyAppConstants.EMPLOYEE_WITH_USERNAME_PASSWORD_NQ)
                    .setParameter("username", username)
                    .setParameter("password", password);
            employeeList = query.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employeeList;
    }


    public Employee getEmployee(String id) {
        logger.info("Fetching Employee using employee id " + id);
        Employee employee = null;
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateSessionUtil.getSessionFactory();
            session = sessionFactory.openSession();
            employee = session.get(Employee.class, id);

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employee;
    }
}
