package com.medibooking.bookingserviceserver.repositories;

//import com.medibooking.bookingserviceserver.BookingServiceServerApplicationTests;
import com.medibooking.bookingserviceserver.entities.Doctor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.medibooking.bookingserviceserver.entities.Authority;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = BookingServiceServerApplicationTests.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//public class DoctorRepositoryTest {
//
//    @Autowired
//    private DoctorRepository doctorRepository;
//
//    @Test
//    public void shouldAddDoctorIntoDBSuccessfullyGivenProperDoctorObject() {
//        Doctor doctor = new Doctor();
//        doctor.setFirstname("abc");
//        doctor.setPassword("123");
//        Doctor returnedDoctor = doctorRepository.save(doctor);
//        Assertions.assertEquals("abc", returnedDoctor.getFirstname());
//        Assertions.assertNotNull(returnedDoctor.getPassword());
//    }
//}

import com.medibooking.bookingserviceserver.BookingServiceServerApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookingServiceServerApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DoctorRepositoryTest {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    public void shouldReturnUserGivenCorrectUsername() {

        // Create a new user.
        Doctor doctor = new Doctor();
        doctor.setFirstName("lt");
        doctor.setLastName("zh");
        doctor.setAge(30);
        doctor.setGender("male");
        Doctor savedDoctor = doctorRepository.save(doctor);


        // Create a new authority associated with the above user.
        Authority authority1 = new Authority();

        authority1.setPermission("ROLE_ADMIN");
        authorityRepository.save(authority1);

        // Create one more authority associated with the above user.
        Authority authority2 = new Authority();
        authority2.setPermission("ROLE_GROUPADMIN");
        authorityRepository.save(authority2);

        savedDoctor.setAuthorities(Set.of(authority1, authority2));

        doctorRepository.save(savedDoctor);


        Doctor savedDoctorWithAuthorities = doctorRepository.findByFirstname(savedDoctor.getFirstName());
        assertNotNull(savedDoctorWithAuthorities);
        assertEquals(2, savedDoctorWithAuthorities.getAuthorities().size());
    }
}