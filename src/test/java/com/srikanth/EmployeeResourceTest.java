package com.srikanth;

import com.srikanth.application.EmployeeApplication;
import com.srikanth.dao.EmployeeDao;
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
import java.util.HashMap;
import java.util.Map;

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
        emp1_id = (String) toHashMap(addEmployee("emp1", "pass1", "Employee One", "emp1@email.com", "08/21/1991", "Male",
                "First pet?", "Dog")).get("employeeId");
        emp2_id = (String) toHashMap(addEmployee("emp2", "pass2", "Employee Two", "emp2@email.com", "06/04/1991", "Female",
                "First pet?", "None")).get("employeeId");
    }

    @Test
    public void testAddEmployee() {

        Response response = addEmployee("emp3", "pass3", "Employee Three", "emp3@email.com", "03/04/1991", "Female",
                "First pet?", "None");
        Assert.assertEquals(200, response.getStatus());

        Map<String, Object> rspEmp = toHashMap(response);
        Assert.assertNotNull(rspEmp.get("employeeId"));
        Assert.assertEquals("emp3", rspEmp.get("username"));
    }

    @Test
    public void testGetEmployee() {
        Map<String, Object> response = toHashMap(target("employees").path(emp1_id).request().get());
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetEmployees() {
        Collection<HashMap<String, Object>> response = target("employees").request().get(new GenericType<Collection<HashMap<String, Object>>>() {
        });
        Assert.assertEquals(2, response.size());
    }


    protected Response addEmployee(String username, String password, String fullName, String emailID,
                                   String dateOfBirth, String gender, String securityQuestion, String securityAnswer, String... extras) {

        Map<String, Object> employee = new HashMap<>();
        employee.put("username", username);
        employee.put("password", password);
        employee.put("fullName", fullName);
        employee.put("dateOfBirth", dateOfBirth);
        employee.put("emailID", emailID);
        employee.put("gender", gender);
        employee.put("securityQuestion", securityQuestion);
        employee.put("securityAnswer", securityAnswer);

        if (extras != null) {
            int count = 1;
            for (String s : extras) {
                employee.put("extra" + count++, s);
            }
        }

        // Entity<Employee> empEntity = Entity.entity(emp, MediaType.APPLICATION_JSON_TYPE);
        Entity<Map<String, Object>> empEntity = Entity.entity(employee, MediaType.APPLICATION_JSON_TYPE);

        return target("employees").request()
                .put(empEntity);
    }

    @Test
    public void testAddExtraFields() {
        Response response = addEmployee("emp4", "pass4", "Employee Four", "emp4@email.com", "10/21/1993", "Male",
                "First pet?", "Dog", "Brother");

        Assert.assertEquals(200, response.getStatus());

        Map<String, Object> employee = toHashMap(response);
        Assert.assertNotNull(employee.get("employeeId"));
        Assert.assertEquals(employee.get("extra1"), "Brother");
    }

    private Map<String, Object> toHashMap(Response response) {
        return response.readEntity(new GenericType<HashMap<String, Object>>() {
        });
    }
}
