package com.srikanth;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.srikanth.application.EmployeeApplication;
import com.srikanth.dao.EmployeeDao;
import com.srikanth.model.Employee;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class EmployeeResourceTest extends JerseyTest {

    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        // another instance for mock DAO.
        final EmployeeDao dao = new EmployeeDao();

        return new EmployeeApplication(dao);
    }

    @Test
    public void testAddEmployee() {
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

        Entity<Employee> empEntity = Entity.entity(emp1, MediaType.APPLICATION_JSON_TYPE);

        Response response = target("employees").request()
                .post(empEntity);
        Assert.assertEquals(200, response.getStatus());

        Employee rspEmp = response.readEntity(Employee.class);
        Assert.assertNotNull(rspEmp.getEmployeeId());
        Assert.assertEquals("emp1", rspEmp.getUsername());
    }

//    @Test
//    public void testGetEmployee() {
//        Employee response = target("employees").path("1").request().get(Employee.class);
//        Assert.assertNotNull(response);
//    }
//
//    @Test
//    public void testGetEmployees() {
//        Collection<Employee> response = target("employees").request().get(new GenericType<Collection<Employee>>() {
//        });
//        Assert.assertEquals(2, response.size());
//    }
}
