package com.jasraj.controller;

import com.jasraj.dto.AlertDto;
import com.jasraj.dto.AlertResponseDto;
import com.jasraj.dto.MonthDto;
import com.jasraj.service.CalendarService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @GET
    @Path("/alerts/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateCalender(@PathParam("email") String email) {
       // LocalDate refDate = LocalDate.of(year, month, 1);
       // MonthDto monthDto = CalendarService.populateMonth(refDate);
        List<AlertDto> alertDto = new ArrayList<>();
        alertDto.add(new AlertDto().setEmail(email).setHh(14).setMm(44).setMonth(4).setYear(2016).setMsg("ulalla"));
        alertDto.add(new AlertDto().setEmail(email).setHh(15).setMm(45).setMonth(5).setYear(2016).setMsg("asdasdasdqsda"));
        return Response.ok(alertDto).build();
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAlter(final AlertDto alertDto) {
        AlertResponseDto alertResponseDto = new AlertResponseDto();
        String msg = "Alert submitted successful for " + alertDto.getEmail() + "\n" +
                "Alert text - " + alertDto.getMsg() + "\n" +
                "Date time - " + alertDto.getHh() + alertDto.getMm() + " " + alertDto.getMonth() + " " + alertDto.getYear();
        alertResponseDto.setResponseMsg(msg);
        return Response.ok(alertResponseDto).build();
    }
}