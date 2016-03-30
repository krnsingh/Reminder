package com.jasraj.controller;

import com.jasraj.dto.CalendarDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reminder")
public class ReminderEndPoint {

    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg(@PathParam("param") String msg) {

        String output = "Jersey say : " + msg;
        return Response.ok(new CalendarDto().setDate(null).setDayOfMonth(1)).build();
       // return new CalendarDto().setDate(null).setDayOfMonth(1);

    }

}