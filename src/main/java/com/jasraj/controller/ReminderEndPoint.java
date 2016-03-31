package com.jasraj.controller;

import com.jasraj.com.jasraj.service.CalendarService;
import com.jasraj.dto.CalendarDto;

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
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg(@PathParam("param") String msg) {
        Map<Integer, List<CalendarDto>> calendarByRow = CalendarService.populateMonth(LocalDate.now(), 0);
        return Response.ok(calendarByRow).build();
       // return new CalendarDto().setDate(null).setDayOfMonth(1);

    }

}