package com.medibooking.bookingserviceserver.dtos.appointment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class AppointmentGetDto {
    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String appointmentMessage;
    private String isCancelled;
    private Long patient;
    private String patientFirstName;
    private String patientLastName;
    private Long doctor;
    private String doctorFirstName;
    private String doctorLastName;
}
