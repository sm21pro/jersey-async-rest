package com.srikanth;

import com.srikanth.model.Employee;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeModelTest {

    @Test
    public void testModel() throws Exception {
//        Class<?> employeeClass = EmployeeModelTest.class.getClassLoader().loadClass("com.srikanth.model.Employee");
//        if (employeeClass != null) {
//            Method[] methods = employeeClass.getDeclaredMethods();
//            for (Method method : methods) {
//                if (method.isAccessible()) {
//                    Object t = employeeClass.newInstance();
//                    if (method.getName().startsWith("set")) {
//                        Object empty = null;
//                        method.invoke(t, empty);
//                    } else if (method.getName().startsWith("get")) {
//                        method.invoke(t);
//                    }
//                }
//            }
//        }

        Employee emp1 = new Employee();
        Employee emp2 = new Employee();
        Assert.assertEquals(emp1, emp2);

        emp1.setUsername("username");
        emp1.setPassword("password");
        emp1.setFullName("fullname");
        emp1.setDateOfBirth("dateOfBirth");
        emp1.setEmailID("emailID");
        emp1.setGender("gender");
        emp1.setSecurityQuestion("question");
        emp1.setSecurityQuestion("answer");

        emp2.setUsername("username");
        emp2.setPassword("password");
        emp2.setFullName("fullname");
        emp2.setDateOfBirth("dateOfBirth");
        emp2.setEmailID("emailID");
        emp2.setGender("gender");
        emp2.setSecurityQuestion("question");
        emp2.setSecurityQuestion("answer");

        Assert.assertTrue(emp1.equals(emp2));

        emp2.setFullName("FULLNAME");

        Assert.assertFalse(emp1.equals(emp2));


        Assert.assertFalse(emp1.toString().contains("com.srikanth"));

    }
}
