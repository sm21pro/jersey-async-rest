package com.srikanth.resource;

import com.srikanth.dao.EmployeeDao;
import com.srikanth.model.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/employees")
public class EmployeeResource {

    //    EmployeeDao dao = new EmployeeDao();
// Replacing above code with binder context

    @Context
    EmployeeDao dao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Employee> getEmployees() {
        return dao.getEmployees();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployee(@PathParam("id") String id) {
        return dao.getEmployee(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Employee addEmployee(Employee employee) {
        return dao.addEmployee(employee);
    }

}
