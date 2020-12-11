package com.medibooking.bookingserviceserver.controllers;

import com.medibooking.bookingserviceserver.dtos.account.AccountGetDto;
import com.medibooking.bookingserviceserver.dtos.account.AccountPostDto;
import com.medibooking.bookingserviceserver.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountGetDto>> getAll(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountGetDto> getByUsername(@PathVariable String username){
        return ResponseEntity.ok(accountService.findAccountByUsername(username));
    }

    @PostMapping
    public ResponseEntity<AccountGetDto> add(@RequestBody AccountPostDto accountPostDto) {
        AccountGetDto accountGetDto = accountService.createAccount(accountPostDto);
        return ResponseEntity.ok(accountGetDto);
    }

}
