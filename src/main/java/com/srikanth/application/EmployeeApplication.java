package com.srikanth.application;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;
import com.srikanth.dao.EmployeeDao;
import com.srikanth.service.EmployeeService;
import com.srikanth.service.impl.EmployeeServiceAsyncImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Register various configuration used in the app through context
 */
public class EmployeeApplication extends ResourceConfig {

    public EmployeeApplication(final EmployeeService empService) {

        JacksonJsonProvider json = new JacksonJsonProvider()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(SerializationFeature.INDENT_OUTPUT, true);

        JacksonXMLProvider xml = new JacksonXMLProvider()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(SerializationFeature.INDENT_OUTPUT, true);

        packages("com.srikanth");

//        register(new AbstractBinder() {
//            @Override
//            protected void configure() {
//                bind(dao).to(EmployeeDao.class);
//            }
//        });
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(empService).to(EmployeeService.class);
            }
        });

        register(json);

        register(xml);
    }
}
