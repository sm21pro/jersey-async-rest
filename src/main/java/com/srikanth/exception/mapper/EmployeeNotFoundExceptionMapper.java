package com.srikanth.exception.mapper;

import com.srikanth.exception.EmployeeNotFoundException;
import com.srikanth.util.ResponseBuilderUtil;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider // Register with Jersey
public class EmployeeNotFoundExceptionMapper implements ExceptionMapper<EmployeeNotFoundException> {

    @Override
    public Response toResponse(EmployeeNotFoundException ex) {
        int statusCode = ex.getStatusCode() != 0 ? ex.getStatusCode() : 500;
        return Response.status(statusCode)
                .entity(ResponseBuilderUtil.getResponse(statusCode, ex.getMessage(), ex))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
