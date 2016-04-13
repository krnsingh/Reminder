package com.jasraj.controller;

import com.jasraj.service.CalendarService;
import com.jasraj.dto.MonthDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("/reminder")
public class ReminderEndPoint {

    @GET
    @Path("/calendar/{month}/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg(@PathParam("month") int month, @PathParam("year") int year) {
        LocalDate refDate = LocalDate.of(year, month, 1);
        MonthDto monthDto = CalendarService.populateMonth(refDate);

        return Response.ok(monthDto).build();
    }

}