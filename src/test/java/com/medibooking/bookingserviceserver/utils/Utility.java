package com.medibooking.bookingserviceserver.utils;

import com.medibooking.bookingserviceserver.dtos.account.AccountGetDto;
import com.medibooking.bookingserviceserver.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class Utility {
    public Account buildAccount(String username,
                                String encodedPassword) {

        Account account = new Account();
        account.setUsername(username);
        account.setEncodedPassword(encodedPassword);
        return account;
    }

    public Doctor buildDoctor(int age,
                               String gender,
                              String firstName,
                              String lastName) {

        Doctor doctor = new Doctor();
        doctor.setGender(gender);
        doctor.setAge(age);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        return doctor;
    }

    public Language buildLanguage(String languageName) {

        Language language = new Language();
        language.setLanguageName(languageName);
        return language;
    }

    public Patient buildPatient(int age,
                              String gender,
                              String firstName,
                              String lastName) {

        Patient patient = new Patient();
        patient.setGender(gender);
        patient.setAge(age);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        return patient;
    }

    public Patient buildPatientWithId(Long id,
                                      int age,
                                String gender,
                                String firstName,
                                String lastName) {

        Patient patient = new Patient();
        patient.setId(id);
        patient.setGender(gender);
        patient.setAge(age);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        return patient;
    }

    public Doctor buildDoctorWithId(Long id,
                                      int age,
                                      String gender,
                                      String firstName,
                                      String lastName) {

        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setGender(gender);
        doctor.setAge(age);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        return doctor;
    }

    public Specialization buildSpecialization(Long id, String specialization_name) {

        Specialization specialization = new Specialization();
        specialization.setId(id);;
        specialization.setSpecializationName(specialization_name);
        return specialization;
    }
}
