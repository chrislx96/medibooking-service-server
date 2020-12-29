package com.medibooking.bookingserviceserver.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medibooking.bookingserviceserver.auth.ApplicationUserService;
import com.medibooking.bookingserviceserver.dtos.account.AccountGetDto;
import com.medibooking.bookingserviceserver.dtos.account.AccountPostDto;
import com.medibooking.bookingserviceserver.services.AccountService;
import com.medibooking.bookingserviceserver.utils.Utilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
@Import(AccountController.class)
@ContextConfiguration(classes = {Utilities.class})
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ApplicationUserService applicationUserService;

    @Autowired
    private Utilities utilities;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAccountListGivenAccountsExists() throws Exception {
        AccountGetDto accountGetDto = utilities.buildAccountGetDto(1L,
                "testAccount",
                "password");
        BDDMockito.given(accountService.getAllAccounts()).willReturn(List.of(accountGetDto));
        this.mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].username").value("testAccount"))
                .andExpect(jsonPath("$.[0].encodedPassword").value("password"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAccountGivenUsernameExists() throws Exception {
        AccountGetDto accountGetDto = utilities.buildAccountGetDto(1L,
                "testAccount",
                "password");
        BDDMockito.given(accountService.findAccountByUsername("testAccount")).willReturn(accountGetDto);
        this.mockMvc.perform(get("/register/testAccount"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testAccount"))
                .andExpect(jsonPath("$.encodedPassword").value("password"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void ShouldReturnAccountAddingNewAccount() throws Exception {
        AccountGetDto accountGetDto = utilities.buildAccountGetDto(1L,
                "testAccount",
                "password");
        AccountPostDto accountPostDto = utilities.buildAccountPostDto("testAccount",
                "password",
                25,
                "male",
                "test",
                "test");
        BDDMockito.given(accountService.createAccount(accountPostDto)).willReturn(accountGetDto);
        this.mockMvc.perform(post("/register"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testAccount"))
                .andExpect(jsonPath("$.encodedPassword").value("password"));
    }


}
