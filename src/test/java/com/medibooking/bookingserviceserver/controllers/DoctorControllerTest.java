package com.medibooking.bookingserviceserver.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medibooking.bookingserviceserver.auth.ApplicationUserService;
import com.medibooking.bookingserviceserver.dtos.doctor.DoctorGetDto;
import com.medibooking.bookingserviceserver.dtos.doctor.DoctorPostDto;
import com.medibooking.bookingserviceserver.dtos.language.LanguageGetDto;
import com.medibooking.bookingserviceserver.dtos.specialization.SpecializationGetDto;
import com.medibooking.bookingserviceserver.entities.Specialization;
import com.medibooking.bookingserviceserver.services.AccountService;
import com.medibooking.bookingserviceserver.services.DoctorService;
import com.medibooking.bookingserviceserver.utils.Utilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DoctorController.class)
@Import(DoctorController.class)
@ContextConfiguration(classes = {Utilities.class})
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ApplicationUserService applicationUserService;

    @Autowired
    private Utilities utilities;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnDoctorListWhenGetAllDoctors() throws Exception {
        LanguageGetDto languageGetDto = new LanguageGetDto();
        languageGetDto.setId(1L);
        languageGetDto.setLanguageName("test");
        Set<LanguageGetDto> languageGetDtoSet = new HashSet<>();
        languageGetDtoSet.add(languageGetDto);
        SpecializationGetDto specializationGetDto = new SpecializationGetDto();
        specializationGetDto.setId(1L);
        specializationGetDto.setSpecializationName("test");
        Set<SpecializationGetDto> specializationGetDtoSet = new HashSet<>();
        specializationGetDtoSet.add(specializationGetDto);
        DoctorGetDto doctorGetDto = utilities.buildDoctorGetDto(1L,
                25,
                "male",
                "test",
                "test",
                "test",
                languageGetDtoSet,
                specializationGetDtoSet
                );
        BDDMockito.given(doctorService.getAllDoctors()).willReturn(List.of(doctorGetDto));
        this.mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].age").value(25))
                .andExpect(jsonPath("$.[0].gender").value("male"))
                .andExpect(jsonPath("$.[0].firstName").value("test"))
                .andExpect(jsonPath("$.[0].lastName").value("test"))
                .andExpect(jsonPath("$.[0].description").value("test"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnDoctorWhenAddNewDoctor() throws Exception {
        LanguageGetDto languageGetDto = new LanguageGetDto();
        languageGetDto.setId(1L);
        languageGetDto.setLanguageName("test");
        Set<LanguageGetDto> languageGetDtoSet = new HashSet<>();
        languageGetDtoSet.add(languageGetDto);
        SpecializationGetDto specializationGetDto = new SpecializationGetDto();
        specializationGetDto.setId(1L);
        specializationGetDto.setSpecializationName("test");
        Set<SpecializationGetDto> specializationGetDtoSet = new HashSet<>();
        specializationGetDtoSet.add(specializationGetDto);
        DoctorPostDto doctorPostDto = utilities.buildDoctorPostDto(25,
                "male",
                "test",
                "test",
                "test",
                languageGetDtoSet,
                specializationGetDtoSet
        );
        DoctorGetDto doctorGetDto = utilities.buildDoctorGetDto(1L,
                25,
                "male",
                "test",
                "test",
                "test",
                languageGetDtoSet,
                specializationGetDtoSet
        );
        BDDMockito.given(doctorService.create(doctorPostDto)).willReturn(doctorGetDto);
        this.mockMvc.perform(post("/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.firstName").value("test"))
                .andExpect(jsonPath("$.lastName").value("test"))
                .andExpect(jsonPath("$.description").value("test"));
    }

}