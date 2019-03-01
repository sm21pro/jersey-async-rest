package com.srikanth;

import com.srikanth.application.EmployeeApplication;
import com.srikanth.service.impl.EmployeeServiceAsyncImpl;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeResourceTest extends JerseyTest {

    private String empId;

    protected Application configure() {
        return new EmployeeApplication(new EmployeeServiceAsyncImpl());
    }

    @Test
    public void testAddEmployee() {
        Response response = addEmployee("emp3", "pass3", "Employee Three", "emp3@email.com", "03/04/1991", "Female",
                "First pet?", "None");
        assertEquals(200, response.getStatus());

        Map<String, Object> responseMap = toHashMap(response);
        assertEquals(200, responseMap.get("statusCode"));

        Map<String, Object> responseData = toData(responseMap);
        assertNotNull(responseData.get("employeeId"));

        empId = (String) responseData.get("employeeId");

        testAddEmployeeFailure();

        testGetEmployee();

        testGetEmployeeFailure();

        testGetEmployees();

        testCheckLogin();

        testDeleteEmployee();

        testDeleteEmployeeFailure();

        testGetEmployeesFailure();

        testCheckLoginFailure();

    }

    public void testAddEmployeeFailure() {
        Response response = addEmployee("emp3", "pass3", "Employee Three", "emp3@email.com", "03/04/1991", "Female",
                "First pet?", "None");
        assertEquals(400, response.getStatus());

        Map<String, Object> responseMap = toHashMap(response);
        assertEquals(400, responseMap.get("statusCode"));
    }

    public void testGetEmployee() {
        Response response = target("EmpMgt/getByEmpId").path(empId).request()
                .get();
        assertEquals(200, response.getStatus());

        Map<String, Object> responseMap = toHashMap(response);
        assertEquals(200, responseMap.get("statusCode"));
    }

    public void testGetEmployeeFailure() {
        Response response = target("EmpMgt/getByEmpId").path(empId + "dummy").request()
                .get();
        assertEquals(400, response.getStatus());

        Map<String, Object> responseMap = toHashMap(response);
        assertEquals(400, responseMap.get("statusCode"));
    }

    public void testGetEmployees() {
        Response response = target("EmpMgt/getAllEmpDetails").request()
                .get();
        assertEquals(200, response.getStatus());

        Map<String, Object> responseMap = toHashMap(response);
        assertEquals(200, responseMap.get("statusCode"));
    }

    public void testCheckLogin() {

        Map<String, Object> employee = new HashMap<>();
        employee.put("username", "emp3");
        employee.put("password", "pass3");

        Entity<Map<String, Object>> empEntity = Entity.entity(employee, MediaType.APPLICATION_JSON_TYPE);

        Response response = target("EmpMgt/checkLogin").request()
                .post(empEntity);
        assertEquals(200, response.getStatus());

        Map<String, Object> responseMap = toHashMap(response);
        assertEquals(200, responseMap.get("statusCode"));
    }

    public void testCheckLoginFailure() {

        Map<String, Object> employee = new HashMap<>();
        employee.put("username", "emp3");
        employee.put("password", "pass3");

        Entity<Map<String, Object>> empEntity = Entity.entity(employee, MediaType.APPLICATION_JSON_TYPE);

        Response response = target("EmpMgt/checkLogin").request()
                .post(empEntity);
        assertEquals(401, response.getStatus());

        Map<String, Object> responseMap = toHashMap(response);
        assertEquals(401, responseMap.get("statusCode"));
    }

    public void testGetEmployeesFailure() {
        Response response = target("EmpMgt/getAllEmpDetails").request()
                .get();
        assertEquals(200, response.getStatus());

        Map<String, Object> responseMap = toHashMap(response);
        assertEquals(200, responseMap.get("statusCode"));
        assertEquals("No Employees data exist", responseMap.get("message"));
    }

    public void testDeleteEmployee() {

        Response response = target("EmpMgt/deleteEmp").path(empId).request()
                .put(Entity.entity(new HashMap<>(), MediaType.APPLICATION_JSON_TYPE));
        assertEquals(200, response.getStatus());

        Map<String, Object> responseMap = toHashMap(response);
        assertEquals(200, responseMap.get("statusCode"));
    }

    public void testDeleteEmployeeFailure() {

        Response response = target("EmpMgt/deleteEmp").path(empId).request()
                .put(Entity.entity(new HashMap<>(), MediaType.APPLICATION_JSON_TYPE));
        assertEquals(400, response.getStatus());

        Map<String, Object> responseMap = toHashMap(response);
        assertEquals(400, responseMap.get("statusCode"));
    }


    protected Response addEmployee(String username, String password, String fullName, String emailID,
                                   String dateOfBirth, String gender, String securityQuestion, String securityAnswer) {

        Map<String, Object> employee = new HashMap<>();
        employee.put("username", username);
        employee.put("password", password);
        employee.put("fullName", fullName);
        employee.put("dateOfBirth", dateOfBirth);
        employee.put("emailID", emailID);
        employee.put("gender", gender);
        employee.put("securityQuestion", securityQuestion);
        employee.put("securityAnswer", securityAnswer);

        Entity<Map<String, Object>> empEntity = Entity.entity(employee, MediaType.APPLICATION_JSON_TYPE);

        return target("EmpMgt/addEmp").request()
                .put(empEntity);
    }

    private Map<String, Object> toHashMap(Response response) {
        return response.readEntity(new GenericType<HashMap<String, Object>>() {
        });
    }

    private Map<String, Object> toData(Map<String, Object> responseMap) {
        return (HashMap<String, Object>) responseMap.get("data");
    }

}
