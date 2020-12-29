package com.medibooking.bookingserviceserver.services;

import com.medibooking.bookingserviceserver.dtos.account.AccountGetDto;
import com.medibooking.bookingserviceserver.dtos.doctor.DoctorGetDto;
import com.medibooking.bookingserviceserver.entities.Account;
import com.medibooking.bookingserviceserver.entities.Doctor;
import com.medibooking.bookingserviceserver.mappers.*;
import com.medibooking.bookingserviceserver.repositories.AccountRepository;
import com.medibooking.bookingserviceserver.repositories.DoctorRepository;
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
public class DoctorServicesTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private Utility utility;

    DoctorService doctorService;

    @BeforeEach
    void setup() {
        doctorService = new DoctorService(doctorRepository, doctorMapper);
    }

    @Test
    public void shouldReturnDoctorListGivenDoctorsExist() {
        Doctor doctor1 = utility.buildDoctor(30,"male","Jack","Ali");
        Doctor doctor2 = utility.buildDoctor(35,"female","Rita","Jo");

        when(doctorRepository.findAll()).thenReturn(List.of(doctor1, doctor2));
        List<Doctor> returnedDoctorList = doctorService.getAllDoctors();
        assertNotNull(returnedDoctorList);
        assertEquals(2, returnedDoctorList.size());
    }

    @Test
    public void shouldThrowExceptionGivenInvalidFirstname() {
        when(doctorRepository.findByFirstname(any())).thenReturn(Optional.empty());
        assertThrows(InvalidAccountException.class,() -> doctorService.findDoctorById(5555));
    }

    @Test
    public void NumberOfDoctorsShouldBeLessAfterDeletion() {
        Long id1=30L;

        doctorRepository.deleteById(30L);
        Doctor doctor1 = utility.buildDoctorWithId(30L,35,"male","ee","tt");
        Doctor doctor2 = utility.buildDoctorWithId(305L,35,"male","eem","tt");
        Doctor doctor3 = utility.buildDoctorWithId(306L,35,"male","eet","tt");

        when(doctorRepository.findAll()).thenReturn(List.of(doctor1, doctor2));
        doctorService.delete(306L);
        List<Doctor> returnedDoctorList = doctorService.getAllDoctors();
        assertNotNull(returnedDoctorList);
        assertEquals(2, returnedDoctorList.size());
    }
}
