package com.medibooking.bookingserviceserver.services;

import com.medibooking.bookingserviceserver.dtos.appointment.AppointmentGetDto;
import com.medibooking.bookingserviceserver.dtos.appointment.AppointmentPostDto;
import com.medibooking.bookingserviceserver.dtos.appointment.AppointmentPutDto;
import com.medibooking.bookingserviceserver.entities.Appointment;
import com.medibooking.bookingserviceserver.mappers.AppointmentMapper;
import com.medibooking.bookingserviceserver.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentGetDto createAppointment(AppointmentPostDto appointmentPostDto) {
        Appointment appointment = appointmentMapper.toEntity(appointmentPostDto);
        return appointmentMapper.fromEntity(appointmentRepository.save(appointment));
    }

    public AppointmentGetDto modifyAppointment(Long appointmentId, AppointmentPutDto appointmentPutDto) {
        Appointment appointment = new Appointment();
        appointmentMapper.copy(appointmentPutDto, appointment);
        appointment.setId(appointmentId);
        return appointmentMapper.fromEntity(appointmentRepository.save(appointment));
    }

    public AppointmentGetDto cancelAppointment(Long appointmentId){
        Appointment appointment = appointmentRepository.getOne(appointmentId);
        appointment.setIsCancelled(true);
        return appointmentMapper.fromEntity(appointmentRepository.save(appointment));
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<AppointmentGetDto> getAll() {
        return appointmentMapper.fromEntities(appointmentRepository.findAll());
    }

    public AppointmentGetDto findAppointmentById(Long id) {
        return appointmentMapper.fromEntity(appointmentRepository.getOne(id));
    }

    public List<AppointmentGetDto> findAppointmentsByPatient(Long patientId) {
        return appointmentMapper.fromEntities(appointmentRepository.findAppointmentsByPatientId(patientId));
    }

    public List<AppointmentGetDto> findAppointmentsByDoctor(Long doctorId) {
        return appointmentMapper.fromEntities(appointmentRepository.findAppointmentsByDoctorId(doctorId));
    }

    public List<AppointmentGetDto> findAppointmentsOfAPatientByDate(Long patientId, LocalDate date) {
        return appointmentMapper.fromEntities(appointmentRepository.findAppointmentsOfAPatientByDate(patientId, date));
    }

    public List<AppointmentGetDto> findAppointmentsOfADoctorByDate(Long doctorId, LocalDate date) {
        return appointmentMapper.fromEntities(appointmentRepository.findAppointmentsOfADoctorByDate(doctorId, date));
    }

    public List<AppointmentGetDto> findAppointmentsByDate(LocalDate date) {
        return appointmentMapper.fromEntities(appointmentRepository.findAppointmentsByDate(date));
    }
}
