package com.medibooking.bookingserviceserver.services;


import com.medibooking.bookingserviceserver.dtos.doctor.DoctorGetDto;
import com.medibooking.bookingserviceserver.dtos.doctor.DoctorPostDto;
import com.medibooking.bookingserviceserver.dtos.doctor.DoctorPutDto;
import com.medibooking.bookingserviceserver.entities.Doctor;
import com.medibooking.bookingserviceserver.mappers.DoctorMapper;
import com.medibooking.bookingserviceserver.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public DoctorGetDto create(DoctorPostDto doctorPostDto) {

        Doctor doctorEntity = doctorMapper.toEntity(doctorPostDto);
        return doctorMapper.fromEntity(doctorRepository.save(doctorEntity));
    }

    public DoctorGetDto modify(Long doctorId, DoctorPutDto doctorPutDto) {
        Doctor doctor = new Doctor();
        doctorMapper.copy(doctorPutDto, doctor);
        doctor.setId(doctorId);
        return doctorMapper.fromEntity(doctorRepository.save(doctor));
    }

    public List<DoctorGetDto> getAllDoctors() {

        return doctorRepository.findAll().stream()
                .map(doctor -> doctorMapper.fromEntity(doctor))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }

    public DoctorGetDto findDoctorById(Long id) {
        return doctorMapper.fromEntity(doctorRepository.getOne(id));
    }

    public DoctorGetDto findDoctorByAccountId(Long accountId) {
        return doctorMapper.fromEntity(doctorRepository.findByAccountId(accountId));
    }
}
