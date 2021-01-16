package com.archive.service;

import com.archive.dto.AccountDto;
import com.archive.entity.AccountEntity;

import java.util.List;

public interface IAccountService {

    AccountEntity findById(Integer accountId);
    List<AccountEntity> getAll();
    AccountEntity add(AccountDto accountDto);
    AccountEntity update(AccountDto accountDto);
    boolean delete(Integer accounttId);
}
