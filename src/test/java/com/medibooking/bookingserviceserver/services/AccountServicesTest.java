package com.medibooking.bookingserviceserver.services;

import com.medibooking.bookingserviceserver.dtos.account.AccountGetDto;
import com.medibooking.bookingserviceserver.entities.Account;
import com.medibooking.bookingserviceserver.mappers.*;
import com.medibooking.bookingserviceserver.repositories.AccountRepository;
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
public class AccountServicesTest {

    @Mock
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private Utility utility;

    AccountService accountService;

    @BeforeEach
    void setup() {
        accountService = new AccountService(accountRepository, accountMapper);
    }

    @Test
    public void shouldReturnAccountListGivenAccountsExist() {
        Account account1 = utility.buildAccount("accName",
                "accNumber");
        Account account2 = utility.buildAccount("accName",
                "accNumber");

        when(accountRepository.findAll()).thenReturn(List.of(account1, account2));
        List<AccountGetDto> returnedAccountList = accountService.getAllAccounts();
        assertNotNull(returnedAccountList);
        assertEquals(2, returnedAccountList.size());
    }

    @Test
    public void shouldThrowExceptionGivenInvalidUsername() {
        when(accountRepository.findByUsername(any())).thenReturn(Optional.empty());
        assertThrows(InvalidAccountException.class,() -> accountService.findAccountByUsername(111));
    }

}
