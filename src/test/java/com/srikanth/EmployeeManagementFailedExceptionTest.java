package com.srikanth;

import com.srikanth.exception.EmployeeManagementFailedException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EmployeeManagementFailedExceptionTest {

    @Test
    public void testConstructors() {

        assertTrue(
                new EmployeeManagementFailedException()
                        .getMessage() == null);

        assertTrue(
                new EmployeeManagementFailedException(
                        "message", new Exception())
                        .getMessage().equalsIgnoreCase("message"));

        assertTrue(
                new EmployeeManagementFailedException(
                        "message")
                        .getMessage().equalsIgnoreCase("message"));

        assertTrue(
                new EmployeeManagementFailedException(
                        200, "message", new Exception())
                        .getMessage().equalsIgnoreCase("message"));

        assertTrue(
                new EmployeeManagementFailedException(
                        new Exception("Testing"))
                        .getCause().getMessage().equalsIgnoreCase("Testing"));

    }
}
