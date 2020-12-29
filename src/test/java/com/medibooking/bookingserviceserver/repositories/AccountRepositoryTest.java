package com.medibooking.bookingserviceserver.repositories;

import com.medibooking.bookingserviceserver.BookingServiceServerApplication;
import com.medibooking.bookingserviceserver.entities.Account;
import com.medibooking.bookingserviceserver.utils.Utility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookingServiceServerApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Utility utility;

    @Test
    public void shouldReturnAccountListGivenAccountsHasBeenInserted() {
        // Create a new account.
        Account account1 = utility.buildAccount("jhgjg", "llglyf");
        Account account2 = utility.buildAccount("vljhg", "khghgfg");
        Account savedAccount = accountRepository.save(account1);
        accountRepository.save(account2);


        List<Account> accountList = accountRepository.findAll();
        assertNotNull(accountList);
        assertTrue(accountList.size() >= 2);
    }

}
