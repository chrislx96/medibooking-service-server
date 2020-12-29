package com.medibooking.bookingserviceserver.utils;

import com.medibooking.bookingserviceserver.dtos.account.AccountGetDto;
import com.medibooking.bookingserviceserver.dtos.account.AccountPostDto;
import com.medibooking.bookingserviceserver.dtos.doctor.DoctorGetDto;
import com.medibooking.bookingserviceserver.dtos.doctor.DoctorPostDto;
import com.medibooking.bookingserviceserver.dtos.language.LanguageGetDto;
import com.medibooking.bookingserviceserver.dtos.specialization.SpecializationGetDto;

import java.util.Set;

public class Utilities {

    public AccountGetDto buildAccountGetDto(Long id,
                                            String username,
                                            String encodedPassword){
        AccountGetDto accountGetDto = new AccountGetDto();
        accountGetDto.setId(id);
        accountGetDto.setUsername(username);
        accountGetDto.setEncodedPassword(encodedPassword);
        return accountGetDto;
    }

    public AccountPostDto buildAccountPostDto(String username,
                                             String encodedPassword,
                                             int age,
                                             String gender,
                                             String firstName,
                                             String lastName){
        AccountPostDto accountPostDto = new AccountPostDto();
        accountPostDto.setUsername(username);
        accountPostDto.setEncodedPassword(encodedPassword);
        accountPostDto.setAge(age);
        accountPostDto.setGender(gender);
        accountPostDto.setFirstName(firstName);
        accountPostDto.setLastName(lastName);
        return accountPostDto;
    }

    public DoctorGetDto buildDoctorGetDto (Long id,
                                           int age,
                                           String gender,
                                           String firstName,
                                           String lastName,
                                           String description,
                                           Set<LanguageGetDto> languages,
                                           Set<SpecializationGetDto> specializations){
        DoctorGetDto doctorGetDto = new DoctorGetDto();
        doctorGetDto.setId(id);
        doctorGetDto.setAge(age);
        doctorGetDto.setGender(gender);
        doctorGetDto.setFirstName(firstName);
        doctorGetDto.setLastName(lastName);
        doctorGetDto.setDescription(description);
        doctorGetDto.setLanguages(languages);
        doctorGetDto.setSpecializations(specializations);
        return doctorGetDto;
    }

    public DoctorPostDto buildDoctorPostDto (int age,
                                                String gender,
                                                String firstName,
                                                String lastName,
                                                String description,
                                                Set<LanguageGetDto> languages,
                                                Set<SpecializationGetDto> specializations){
        DoctorPostDto doctorPostDto = new DoctorPostDto();
        doctorPostDto.setAge(age);
        doctorPostDto.setGender(gender);
        doctorPostDto.setFirstName(firstName);
        doctorPostDto.setLastName(lastName);
        doctorPostDto.setDescription(description);
        doctorPostDto.setLanguages(languages);
        doctorPostDto.setSpecializations(specializations);
        return doctorPostDto;
    }
}
