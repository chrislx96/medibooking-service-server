package com.medibooking.bookingserviceserver.services;

import com.medibooking.bookingserviceserver.dtos.account.AccountGetDto;
import com.medibooking.bookingserviceserver.entities.Account;
import com.medibooking.bookingserviceserver.entities.Patient;
import com.medibooking.bookingserviceserver.entities.Specialization;
import com.medibooking.bookingserviceserver.mappers.*;
import com.medibooking.bookingserviceserver.repositories.AccountRepository;
import com.medibooking.bookingserviceserver.repositories.SpecializationRepository;
import com.medibooking.bookingserviceserver.utils.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {AccountMapperImpl.class, AppointmentMapperImpl.class, DoctorMapperImpl.class,
        LanguageMapperImpl.class, PatientMapperImpl.class, SpecializationMapperImpl.class, Utility.class})
public class SpecializationServiceTest {

    @Mock
    private SpecializationRepository specializationRepository;

    @Autowired
    private SpecializationMapper specializationMapper;

    @Autowired
    private Utility utility;

    SpecializationService specializationService;

    @BeforeEach
    void setup() {
        specializationService = new SpecializationService(specializationRepository, specializationMapper);
    }

    @Test
    public void shouldReturnAccountListGivenAccountsExist() {
        Specialization specialization1 = utility.buildSpecialization(666L,"medicine");
        Specialization specialization2 = utility.buildSpecialization(777L,"pregnancy");

        when(specializationRepository.findAll()).thenReturn(List.of(specialization1, specialization2));
        List<Specialization> returnedSpecializationList = specializationService.getAllSpe();
        assertNotNull(returnedSpecializationList);
        assertEquals(2, returnedSpecializationList.size());
    }

    @Test
    public void NumberOfSpecializationsShouldBeLessAfterDeletion() {
        Specialization specialization1 = utility.buildSpecialization(30L,"medicine");
        Specialization specialization2 = utility.buildSpecialization(55L,"pregnancy");

        when(specializationRepository.deleteById(55L)).thenReturn(specialization1);
        specializationService.delete(55L);
        List<Specialization> returnedSpecializationList = specializationService.getAllSpe();
        assertNotNull(returnedSpecializationList);
        assertEquals(1, returnedSpecializationList.size());
    }
}
