package com.medibooking.bookingserviceserver.services;

import com.medibooking.bookingserviceserver.dtos.patient.PatientGetDto;
import com.medibooking.bookingserviceserver.entities.Doctor;
import com.medibooking.bookingserviceserver.entities.Patient;
import com.medibooking.bookingserviceserver.mappers.*;
import com.medibooking.bookingserviceserver.repositories.DoctorRepository;
import com.medibooking.bookingserviceserver.repositories.PatientRepository;
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
public class PatientServicesTest {

    @Mock
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private Utility utility;

    PatientService patientService;

    @BeforeEach
    void setup() {
        patientService = new PatientService(patientRepository, patientMapper);
    }

    @Test
    public void shouldReturnPatientListGivenPatientsExist() {
        Patient patient1 = utility.buildPatient(30,"male","Jack","Ali");
        Patient patient2 = utility.buildPatient(35,"female","Rita","Jo");

        when(patientRepository.findAll()).thenReturn(List.of(patient1, patient2));
        List<Patient> returnedPatientList = patientService.getAllPatients();
        assertNotNull(returnedPatientList);
        assertEquals(2, returnedPatientList.size());
    }

    @Test
    public void shouldReturnPatientGivenPatientId() {
        Patient patient1 = utility.buildPatientWithId(30L, 35, "male","Jack","io");
        when(patientRepository.getOne(30L)).thenReturn(patient1);
        PatientGetDto returnedPatient = patientService.findPatientById(30L);
        assertNotNull(returnedPatient);
    }

    @Test
    public void shouldReturnPatientGivenPatientName() {
        Patient patient1 = utility.buildPatientWithId(30, 35, "male","Jack","io");
        when(patientRepository.findByFirstName("Jack")).thenReturn(patient1);
        PatientGetDto returnedPatient = patientService.findPatientByName("Jack");
        assertNotNull(returnedPatient);
    }

    @Test
    public void NumberOfPatientsShouldBeLessAfterDeletion() {
        Patient patient1 = utility.buildPatientWithId(30L,35,"male","ee","tt");
        Patient patient2 = utility.buildPatientWithId(305L,35,"male","eem","tt");
        Patient patient3 = utility.buildPatientWithId(306L,35,"male","eet","tt");

        when(patientRepository.deleteById(306L)).thenReturn(List.of(patient1, patient2));
        patientService.delete(306L);
        List<Patient> returnedPatientList = patientService.getAllPatients();
        assertNotNull(returnedPatientList);
        assertEquals(2, returnedPatientList.size());
    }
}
