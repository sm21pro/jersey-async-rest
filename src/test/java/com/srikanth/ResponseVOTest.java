package com.srikanth;

import com.srikanth.model.Employee;
import com.srikanth.valueobject.EmployeeServiceResponseVO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ResponseVOTest {

    @Test
    public void testVO() throws Exception {
        EmployeeServiceResponseVO responseVO1 = new EmployeeServiceResponseVO();
        EmployeeServiceResponseVO responseVO2 = null;
        Assert.assertFalse(responseVO1.equals(responseVO2));

        responseVO2 = new EmployeeServiceResponseVO();
        Assert.assertTrue(responseVO1.equals(responseVO2));

        responseVO1.setStatus("SUCCESS");
        responseVO2.setStatus("Failure");
        Assert.assertFalse(responseVO1.equals(responseVO2));


        responseVO1.setMessage("Message");
        responseVO2.setMessage("Message");
        Assert.assertFalse(responseVO1.equals(responseVO2));

        responseVO1.setStatusCode(200);
        responseVO2.setStatusCode(400);
        Assert.assertFalse(responseVO1.equals(responseVO2));

        responseVO1.setData(new ArrayList<>());
        responseVO2.setData(new ArrayList<>());
        Assert.assertFalse(responseVO1.equals(responseVO2));

        Assert.assertFalse(responseVO1.toString().contains("com.srikanth"));

    }
}
