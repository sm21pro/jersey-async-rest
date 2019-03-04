package com.srikanth;

import com.srikanth.dao.EmployeeDao;
import com.srikanth.util.HibernateSessionUtil;
import org.easymock.EasyMock;
import org.hibernate.HibernateException;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
//@PrepareForTest(StandardServiceRegistryBuilder.class)
@PrepareForTest(HibernateSessionUtil.class)
public class HibernateUtilTest {

    @Test
    public void testExceptionLogging() throws Exception {

//        StandardServiceRegistryBuilder standardServiceRegistry =
//                PowerMock.createMock(StandardServiceRegistryBuilder.class);
//
//        PowerMock.expectNew(StandardServiceRegistryBuilder.class).andThrow(
//                new Exception("Exception block")
//        );
//        PowerMock.replayAll();
//        HibernateSessionUtil.getSessionFactory();
//
//        PowerMock.verifyAll();

        PowerMock.mockStatic(HibernateSessionUtil.class);

        EasyMock.expect(HibernateSessionUtil.getSessionFactory())
                .andThrow(new HibernateException("Exception block"));

        PowerMock.replayAll();

        try {
            new EmployeeDao().deleteEmployee("empId");
        } catch (Exception ex) {
            Assert.assertTrue(ex.getMessage().equalsIgnoreCase("Exception block"));
        }

        PowerMock.verifyAll();


    }
}
