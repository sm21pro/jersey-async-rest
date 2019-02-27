package com.srikanth.resource;

import com.srikanth.dao.EmployeeDao;
import com.srikanth.model.Employee;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/employees")
public class EmployeeResource {

    EmployeeDao dao = new EmployeeDao();

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
}
