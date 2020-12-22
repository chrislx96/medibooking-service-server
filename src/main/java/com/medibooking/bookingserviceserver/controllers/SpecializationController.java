package com.medibooking.bookingserviceserver.controllers;

import java.util.List;

import com.medibooking.bookingserviceserver.dtos.specialization.SpecializationGetDto;
import com.medibooking.bookingserviceserver.dtos.specialization.SpecializationPostDto;
import com.medibooking.bookingserviceserver.dtos.specialization.SpecializationPutDto;
import com.medibooking.bookingserviceserver.entities.Specialization;
import com.medibooking.bookingserviceserver.services.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specializations")
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpecializationController {

    private final SpecializationService specializationService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_PATIENT')")
    public ResponseEntity<SpecializationGetDto> add(@RequestBody SpecializationPostDto spePostDto) {
        SpecializationGetDto speGetDto = specializationService.createSpe(spePostDto);
        return ResponseEntity.ok(speGetDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_PATIENT')")
    public ResponseEntity<List<SpecializationGetDto>> find() {
        List<SpecializationGetDto> list = specializationService.getAllSpe();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{specializationId}")
    public ResponseEntity<SpecializationGetDto> update(@PathVariable Long specializationId, @RequestBody SpecializationPutDto spePutDto) {
        return ResponseEntity.ok(specializationService.modify(specializationId, spePutDto));
    }

    @DeleteMapping("/{specializationId}")
    public ResponseEntity delete(@PathVariable Long specializationId) {
        specializationService.delete(specializationId);
        return ResponseEntity.ok().build();
    }
}

