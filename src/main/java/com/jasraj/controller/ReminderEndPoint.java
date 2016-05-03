package com.jasraj.controller;

import com.jasraj.dto.AlertDto;
import com.jasraj.dto.AlertResponseDto;
import com.jasraj.dto.MonthDto;
import com.jasraj.entity.Alert;
import com.jasraj.entity.User;
import com.jasraj.job.Scheduler;
import com.jasraj.service.AlertService;
import com.jasraj.service.CalendarService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Path("/reminder")
public class ReminderEndPoint {

    AlertService alertService = new AlertService();



    @GET
    @Path("/calendar/{email}/{month}/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateCalender(@PathParam("email") String email, @PathParam("month") int month, @PathParam("year") int year) {
        Scheduler.start();
        LocalDateTime refDate = LocalDateTime.of(year, month, 1, 0, 0);
        MonthDto monthDto = CalendarService.populateMonth(refDate, alertService.getAlerts(email));
        return Response.ok(monthDto).build();
    }

    @GET
    @Path("/alerts/{email}/{day}/{month}/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlters(@PathParam("email") String email, @PathParam("day") int day,
                                     @PathParam("month") int month, @PathParam("year") int year) {
        return Response.ok(alertService.getAlerts(email, day, month, year)).build();
    }

    @DELETE
    @Path("/alerts/{alertId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAlert(@PathParam("alertId") String alertId) {
        return Response.ok(alertService.deleteAlert(Integer.parseInt(alertId))).build();
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAlter(final AlertDto alertDto) {
        AlertResponseDto alertResponseDto = new AlertResponseDto();
        String msg = "Alert submitted successful for " + alertDto.getEmail() + "\n" +
                "Alert text - " + alertDto.getMsg() + "\n" +
                "Date time - " + alertDto.getHh() + alertDto.getMm() + "Hrs " + alertDto.getMonth() + "/" + alertDto.getYear();
        alertResponseDto.setResponseMsg(msg);
        User user = new User();
        user.setEmailId(alertDto.getEmail());
        user.addAlert(new Alert()
                .setMessage(alertDto.getMsg())
                .setLocalDate(LocalDateTime.of(alertDto.getYear(),
                        alertDto.getMonth(),
                        alertDto.getDay(),
                        alertDto.getHh(),
                        alertDto.getMm()))
                .setUser(user));
        alertService.addUserAlerts(user);
        return Response.ok(alertResponseDto).build();
    }
}