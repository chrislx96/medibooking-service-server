package com.medibooking.bookingserviceserver.dtos.appointment;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class AppointmentPostDto {
    //  pattern = "yyyy-MM-dd"
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    //  pattern = "HH:mm:ss"
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startingTime;
    //  pattern = "HH:mm:ss"
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endingTime;
    private String notes;
    private Boolean isCancelled;
    private Long patient;
    private Long doctor;
}
