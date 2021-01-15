package com.archive.service;


import com.archive.dao.AccountRepository;
import com.archive.entity.AccountEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService{

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountEntity findById(Integer accountId) {
        return accountRepository.findById(accountId).get();
    }
}
