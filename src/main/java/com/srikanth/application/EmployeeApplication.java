package com.srikanth.application;

import com.srikanth.dao.EmployeeDao;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class EmployeeApplication extends ResourceConfig {

    public EmployeeApplication(final EmployeeDao dao) {
        packages("com.srikanth");
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(dao).to(EmployeeDao.class);
            }
        });
    }
}
