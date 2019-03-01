package com.srikanth.resource;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.srikanth.model.Employee;
import com.srikanth.service.EmployeeService;
import com.srikanth.valueobject.EmployeeServiceResponseVO;
import org.glassfish.jersey.server.ManagedAsync;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Class that handles REST calls asynchronously
 */
@Path("/EmpMgt")
public class EmployeeResource {

    @Context
    EmployeeService empService;

    /**
     * ADD EMPLOYEE TO DATABASE
     *
     * @param employee
     * @param response
     */
    @PUT
    @Path("/addEmp")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON + ";qs=1", MediaType.APPLICATION_XML + ";qs=0.5"})
    @ManagedAsync
    public void addEmployee(Employee employee, @Suspended final AsyncResponse response) {
        // response.resume(dao.addEmployee(employee));
        ListenableFuture<EmployeeServiceResponseVO> empFuture = empService.addEmployee(employee);
        Futures.addCallback(empFuture, new FutureCallback<EmployeeServiceResponseVO>() {
            @Override
            public void onSuccess(EmployeeServiceResponseVO addedEmployee) {
                response.resume(addedEmployee);
            }

            @Override
            public void onFailure(Throwable throwable) {
                response.resume(throwable);
            }
        });
    }

    /**
     * DELETE EMPLOYEE WITH EMPLOYEE ID
     *
     * @param empId
     * @param response
     */
    @PUT
    @Path("/deleteEmp/{empId}")
    @Produces({MediaType.APPLICATION_JSON + ";qs=1", MediaType.APPLICATION_XML + ";qs=0.5"})
    @ManagedAsync
    public void deleteEmployee(@PathParam("empId") String empId, @Suspended final AsyncResponse response) {
        // response.resume(dao.addEmployee(employee));
        ListenableFuture<EmployeeServiceResponseVO> empFuture = empService.deleteEmployee(empId);
        Futures.addCallback(empFuture, new FutureCallback<EmployeeServiceResponseVO>() {
            @Override
            public void onSuccess(EmployeeServiceResponseVO addedEmployee) {
                response.resume(addedEmployee);
            }

            @Override
            public void onFailure(Throwable throwable) {
                response.resume(throwable);
            }
        });
    }

    /**
     * GET ALL EMPLOYEES FROM DATABASE
     *
     * @param response
     */
    @GET
    @Path("/getAllEmpDetails")
    // priority of mediatype to be sent
    @Produces({MediaType.APPLICATION_JSON + ";qs=1", MediaType.APPLICATION_XML + ";qs=0.5"})
    @ManagedAsync
    public void getEmployees(@Suspended final AsyncResponse response) {
        // return dao.getEmployees();
        // response.resume(dao.getEmployees());
        ListenableFuture<EmployeeServiceResponseVO> empFuture = empService.getEmployees();
        Futures.addCallback(empFuture, new FutureCallback<EmployeeServiceResponseVO>() {
            @Override
            public void onSuccess(EmployeeServiceResponseVO employees) {
                response.resume(employees);
            }

            @Override
            public void onFailure(Throwable throwable) {
                response.resume(throwable);
            }
        });
    }

    /**
     * GET EMPLOYEE USING EMPLOYEE ID
     *
     * @param id
     * @param response
     */
    @Path("/getByEmpId/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON + ";qs=1", MediaType.APPLICATION_XML + ";qs=0.5"})
    @ManagedAsync
    public void getEmployee(@PathParam("id") String id, @Suspended final AsyncResponse response) {
        // response.resume(dao.getEmployee(id));
        ListenableFuture<EmployeeServiceResponseVO> empFuture = empService.getEmployee(id);
        Futures.addCallback(empFuture, new FutureCallback<EmployeeServiceResponseVO>() {
            @Override
            public void onSuccess(EmployeeServiceResponseVO empFetched) {
                response.resume(empFetched);
            }

            @Override
            public void onFailure(Throwable throwable) {
                response.resume(throwable);
            }
        });
    }


    /**
     * AUTHENTICATE USER WITH USERNAME AND PASSWORD
     *
     * @param employee
     * @param response
     */
    @POST
    @Path("/checkLogin")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON + ";qs=1", MediaType.APPLICATION_XML + ";qs=0.5"})
    @ManagedAsync
    public void checkLogin(Employee employee, @Suspended final AsyncResponse response) {
        ListenableFuture<EmployeeServiceResponseVO> empFuture = empService.checkLogin(employee.getUsername(), employee.getPassword());
        Futures.addCallback(empFuture, new FutureCallback<EmployeeServiceResponseVO>() {
            @Override
            public void onSuccess(EmployeeServiceResponseVO addedEmployee) {
                response.resume(addedEmployee);
            }

            @Override
            public void onFailure(Throwable throwable) {
                response.resume(throwable);
            }
        });
    }

}
