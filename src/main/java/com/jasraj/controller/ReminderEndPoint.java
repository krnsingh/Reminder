package com.jasraj.controller;

import com.jasraj.dto.AlertDto;
import com.jasraj.service.CalendarService;
import com.jasraj.dto.MonthDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("/reminder")
public class ReminderEndPoint {

    @GET
    @Path("/calendar/{month}/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateCalender(@PathParam("month") int month, @PathParam("year") int year) {
        LocalDate refDate = LocalDate.of(year, month, 1);
        MonthDto monthDto = CalendarService.populateMonth(refDate);

        return Response.ok(monthDto).build();
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAlter(final AlertDto alertDto) {

        return Response.ok().build();
    }
}