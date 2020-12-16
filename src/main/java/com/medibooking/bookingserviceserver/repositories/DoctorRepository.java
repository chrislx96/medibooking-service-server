package com.medibooking.bookingserviceserver.repositories;

import com.medibooking.bookingserviceserver.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("select d from Doctor d where d.account.id = :accountId")
    Doctor findByAccountId(@Param("accountId") Long accountId);
}
