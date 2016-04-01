package com.jasraj.controller;

import com.jasraj.com.jasraj.service.CalendarService;
import com.jasraj.dto.MonthDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Path("/reminder")
public class ReminderEndPoint {

    @GET
    @Path("/calendar/{plusMinus}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg(@PathParam("plusMinus") int plusMinus) {
        MonthDto month = CalendarService.populateMonth(LocalDate.now(), 0);
        return Response.ok(month).build();
    }

}