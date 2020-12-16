package com.medibooking.bookingserviceserver.repositories;

import com.medibooking.bookingserviceserver.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface
PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByFirstName(String firstName);

    @Query("select p from Patient p where p.account.id = :accountId")
    Patient findByAccountId(@Param("accountId") Long accountId);
}
