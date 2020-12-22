package com.medibooking.bookingserviceserver.services;

import com.medibooking.bookingserviceserver.dtos.account.AccountGetDto;
import com.medibooking.bookingserviceserver.dtos.account.AccountPostDto;
import com.medibooking.bookingserviceserver.dtos.account.AccountPutDto;
import com.medibooking.bookingserviceserver.dtos.appointment.AppointmentGetDto;
import com.medibooking.bookingserviceserver.dtos.appointment.AppointmentPutDto;
import com.medibooking.bookingserviceserver.entities.Account;
import com.medibooking.bookingserviceserver.entities.Appointment;
import com.medibooking.bookingserviceserver.entities.Authority;
import com.medibooking.bookingserviceserver.entities.Patient;
import com.medibooking.bookingserviceserver.mappers.AccountMapper;
import com.medibooking.bookingserviceserver.repositories.AccountRepository;
import com.medibooking.bookingserviceserver.repositories.AuthorityRepository;
import com.medibooking.bookingserviceserver.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PatientRepository patientRepository;
    private final AuthorityRepository authorityRepository;

    public AccountGetDto createAccount(AccountPostDto accountPostDto){
        Account account = accountMapper.toEntity(accountPostDto);
        Set<Authority> authorities = Stream.of(authorityRepository.getOne(Long.valueOf(2))).collect(Collectors.toSet());
        account.setAuthorities(authorities);
        Patient patient = new Patient();
        patient.setAccount(account);
        patient.setFirstName(accountPostDto.getFirstName());
        patient.setLastName(accountPostDto.getLastName());
        patient.setAge(accountPostDto.getAge());
        patient.setGender(accountPostDto.getGender());
        patientRepository.save(patient);
        return accountMapper.fromEntity(accountRepository.save(account));
    }

    public AccountGetDto findAccountByUsername(String username){
        Account account = accountRepository.findByUsername(username);
        return accountMapper.fromEntity(account);
    }

    public List<AccountGetDto> getAllAccounts(){
        return accountRepository.findAll().stream()
                .map(account -> accountMapper.fromEntity(account))
                .collect(Collectors.toList());
    }

    public String findUsernameById(Long accountId){
        Account account = accountRepository.getOne(accountId);
        return account.getUsername();
    }

    public AccountGetDto changePassword(Long accountId, AccountPutDto accountPutDto) {
        Account account = new Account();
        accountMapper.copy(accountPutDto, account);
        account.setId(accountId);
        account.setUsername(findUsernameById(accountId));
        return accountMapper.fromEntity(accountRepository.save(account));
    }

    public void deleteAccount(Long accountId){
        accountRepository.deleteById(accountId);
    }
}
