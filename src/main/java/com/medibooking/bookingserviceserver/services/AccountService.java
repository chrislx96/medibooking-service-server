package com.medibooking.bookingserviceserver.services;

import com.medibooking.bookingserviceserver.dtos.account.AccountGetDto;
import com.medibooking.bookingserviceserver.dtos.account.AccountPostDto;
import com.medibooking.bookingserviceserver.entities.Account;
import com.medibooking.bookingserviceserver.entities.Patient;
import com.medibooking.bookingserviceserver.mappers.AccountMapper;
import com.medibooking.bookingserviceserver.repositories.AccountRepository;
import com.medibooking.bookingserviceserver.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PatientRepository patientRepository;

    public AccountGetDto createAccount(AccountPostDto accountPostDto){
        Account account = accountMapper.toEntity(accountPostDto);
        Patient patient = new Patient();
        patient.setAccount(account);
        patient.setFirstName("");
        patient.setAge(0);
        patient.setGender("");
        patient.setLastName("");
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

    public void deleteAccount(Long accountId){
        accountRepository.deleteById(accountId);
    }
}
