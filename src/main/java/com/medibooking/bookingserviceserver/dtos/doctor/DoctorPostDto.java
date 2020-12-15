package com.medibooking.bookingserviceserver.dtos.doctor;

import com.medibooking.bookingserviceserver.dtos.language.LanguageGetDto;
import com.medibooking.bookingserviceserver.dtos.specialization.SpecializationGetDto;
import lombok.Data;

import java.util.Set;

@Data
public class DoctorPostDto {
    private int age;
    private String gender;
    private String firstName;
    private String lastName;
    private String description;
    private Set<LanguageGetDto> languages;
    private Set<SpecializationGetDto> specializations;
}