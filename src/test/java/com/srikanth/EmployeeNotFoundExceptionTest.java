package com.srikanth;

import com.srikanth.exception.EmployeeNotFoundException;
import com.srikanth.util.JerseyAppConstants;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EmployeeNotFoundExceptionTest {

    @Test
    public void testConstructors() {

        assertTrue(
                new EmployeeNotFoundException()
                        .getMessage() == null);

        assertTrue(
                new EmployeeNotFoundException(
                        JerseyAppConstants.EMPLOYEE_WITH_USERNAME_PASSWORD_NQ, new Exception())
                        .getMessage().equalsIgnoreCase(JerseyAppConstants.EMPLOYEE_WITH_USERNAME_PASSWORD_NQ));

        assertTrue(
                new EmployeeNotFoundException(
                        "message")
                        .getMessage().equalsIgnoreCase("message"));

        assertTrue(
                new EmployeeNotFoundException(
                        200, "message", new Exception())
                        .getMessage().equalsIgnoreCase("message"));

        assertTrue(
                new EmployeeNotFoundException(
                        new Exception("Testing"))
                        .getCause().getMessage().equalsIgnoreCase("Testing"));

    }
}
