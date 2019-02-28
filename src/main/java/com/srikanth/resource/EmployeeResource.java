package com.srikanth.resource;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.srikanth.dao.EmployeeDao;
import com.srikanth.model.Employee;
import org.glassfish.jersey.server.ManagedAsync;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/employees")
public class EmployeeResource {

    // EmployeeDao dao = new EmployeeDao();
    // Replacing above code with binder context

    @Context
    EmployeeDao dao;

    @GET
    // priority of mediatype to be sent
    @Produces({MediaType.APPLICATION_JSON + ";qs=1", MediaType.APPLICATION_XML + ";qs=0.5"})
    @ManagedAsync
    public void getEmployees(@Suspended final AsyncResponse response) {
        // return dao.getEmployees();
        // response.resume(dao.getEmployees());
        ListenableFuture<Collection<Employee>> empFuture = dao.getEmployeesAsync();
        Futures.addCallback(empFuture, new FutureCallback<Collection<Employee>>() {
            @Override
            public void onSuccess(Collection<Employee> employees) {
                response.resume(employees);
            }

            @Override
            public void onFailure(Throwable throwable) {
                response.resume(throwable);
            }
        });
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ManagedAsync
    public void getEmployee(@PathParam("id") String id, @Suspended final AsyncResponse response) {
        // response.resume(dao.getEmployee(id));
        ListenableFuture<Employee> empFuture = dao.getEmployeeAsync(id);
        Futures.addCallback(empFuture, new FutureCallback<Employee>() {
            @Override
            public void onSuccess(Employee empFetched) {
                response.resume(empFetched);
            }

            @Override
            public void onFailure(Throwable throwable) {
                response.resume(throwable);
            }
        });
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ManagedAsync
    public void addEmployee(Employee employee, @Suspended final AsyncResponse response) {
        // response.resume(dao.addEmployee(employee));
        ListenableFuture<Employee> empFuture = dao.addEmployeeAsync(employee);
        Futures.addCallback(empFuture, new FutureCallback<Employee>() {
            @Override
            public void onSuccess(Employee addedEmployee) {
                response.resume(addedEmployee);
            }

            @Override
            public void onFailure(Throwable throwable) {
                response.resume(throwable);
            }
        });
    }

}
