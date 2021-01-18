package com.archive.service;

import com.archive.dto.AccountDto;
import com.archive.entity.AccountEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IAccountService {

    AccountEntity findById(Integer accountId);
    AccountEntity add(AccountDto accountDto);
    AccountEntity update(AccountDto accountDto);
    boolean delete(Integer accounttId);
    AccountEntity getAccountByNumber(String account_number);
    Set<AccountEntity> getAccountsByAccountNumberContains(String account_number);
    Set<AccountEntity> getAccountsByClientNameContains(String name);
    Set<AccountEntity> getAccountstsByClientNameAndAccountNumberContains(String client_name, String account_number);
    Set<AccountEntity> getAccountByEventTypeAndDateBetween(int eventType, LocalDate dateAfter, LocalDate dateBefor);
}
