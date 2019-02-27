package com.srikanth;

import com.srikanth.application.EmployeeApplication;
import com.srikanth.dao.EmployeeDao;
import com.srikanth.model.Employee;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

public class EmployeeResourceTest extends JerseyTest {

    String emp1_id;
    String emp2_id;

    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        // another instance for mock DAO.
        final EmployeeDao dao = new EmployeeDao();

        return new EmployeeApplication(dao);
    }

    @Before
    public void setUpEmployees() {
        emp1_id = addEmployee("emp1", "pass1", "Employee One", "emp1@email.com", "08/21/1991", "Male",
                "First pet?", "Dog").readEntity(Employee.class).getEmployeeId();
        emp2_id = addEmployee("emp2", "pass2", "Employee Two", "emp2@email.com", "06/04/1991", "Female",
                "First pet?", "None").readEntity(Employee.class).getEmployeeId();
    }

    @Test
    public void testAddEmployee() {

        Response response = addEmployee("emp3", "pass3", "Employee Three", "emp3@email.com", "03/04/1991", "Female",
                "First pet?", "None");
        Assert.assertEquals(200, response.getStatus());

        Employee rspEmp = response.readEntity(Employee.class);
        Assert.assertNotNull(rspEmp.getEmployeeId());
        Assert.assertEquals("emp3", rspEmp.getUsername());
    }

    @Test
    public void testGetEmployee() {
        Employee response = target("employees").path(emp1_id).request().get(Employee.class);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetEmployees() {
        Collection<Employee> response = target("employees").request().get(new GenericType<Collection<Employee>>() {
        });
        Assert.assertEquals(2, response.size());
    }


    private Response addEmployee(String username, String password, String fullName, String emailID,
                                 String dateOfBirth, String gender, String securityQuestion, String securityAnswer) {
        Employee emp = new Employee();
        emp.setUsername(username);
        emp.setPassword(password);
        emp.setFullName(fullName);
        emp.setDateOfBirth(emailID);
        emp.setEmailID(dateOfBirth);
        emp.setGender(gender);
        emp.setSecurityAnswer(securityQuestion);
        emp.setSecurityAnswer(securityAnswer);

        Entity<Employee> empEntity = Entity.entity(emp, MediaType.APPLICATION_JSON_TYPE);

        return target("employees").request()
                .post(empEntity);
    }
}
