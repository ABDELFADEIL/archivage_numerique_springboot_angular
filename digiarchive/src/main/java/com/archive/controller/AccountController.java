package com.archive.controller;


import com.archive.dto.AccountDto;
import com.archive.entity.AccountEntity;
import com.archive.entity.DigitalDocumentEntity;
import com.archive.service.IAccountService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/account")
@CrossOrigin("*")
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/add", consumes = {"application/json"})
    public AccountEntity addAccount(
            @RequestBody AccountDto accountDto
                                   )
    {
        System.out.println(accountDto);
        return accountService.add(accountDto);
    }

    @PutMapping(value = "/update", consumes = {"application/json"})
    public AccountEntity updateAccount(
            @RequestBody AccountDto accountDto
    )
    {
        return accountService.update(accountDto);
    }




    @GetMapping("/get-accounts-event-and-date")
    public Set<AccountEntity> getAccountByEventTypeAndDateBetween(
            @RequestParam(name = "status", required = true) int eventType,
            @RequestParam("dateAfter") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateAfter,
            @RequestParam("dateBefor") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateBefor
    )
    {
        return accountService.getAccountByEventTypeAndDateBetween(eventType, dateAfter, dateBefor);
    }


    @GetMapping("/get-account-by-customer-id")
    public AccountEntity getAccountById(@RequestParam("customerId") Integer customerId) {
        return accountService.findById(customerId);
    }

    @GetMapping("/get-account-by-account-number")
    public AccountEntity getAccountByNumber(@RequestParam("account_number") String account_number){
        return accountService.getAccountByNumber(account_number);
    }

    @DeleteMapping("/delete-account/{accountId}")
    public boolean deleteAccount(@RequestBody Integer accountId){
        return accountService.delete(accountId);
    }

    @GetMapping("/get-accounts-by-number-key")
    public Set<AccountEntity> getAccountsByAccountNumberContains(String account_number) {
        return accountService.getAccountsByAccountNumberContains(account_number);
    }

    @GetMapping("/get-accounts-by-client-name-key")
    public Set<AccountEntity> getAccountsByClientNameContains(String name) {
        return accountService.getAccountsByClientNameContains(name);
    }


    @GetMapping("/get-accounts-by-client-name-account-number")
    public Set<AccountEntity> getAccountsByClientNameAndAccountNumberContains(
            @RequestParam(value = "account_number") String account_number,
            @RequestParam(value = "client_name") String client_name) {
        return accountService.getAccountstsByClientNameAndAccountNumberContains(client_name, account_number);
    }

    @GetMapping("/get--accounts-name")
    public Set<AccountEntity> getAllAccounts(
            @RequestParam(value = "client_name") String client_name) {
        return accountService.getAccountsByClientNameContains(client_name);
    }
}
