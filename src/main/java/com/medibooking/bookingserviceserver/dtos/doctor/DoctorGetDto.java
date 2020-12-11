package com.medibooking.bookingserviceserver.dtos.doctor;

import com.medibooking.bookingserviceserver.entities.Language;
import com.medibooking.bookingserviceserver.entities.Specialization;
import lombok.Data;

import java.util.Set;

@Data
public class DoctorGetDto {
    private Long id;
    private int age;
    private String gender;
    private String firstName;
    private String lastName;
    private String description;
    private Set<Language> languages;
    private Set<Specialization> specializations;
}