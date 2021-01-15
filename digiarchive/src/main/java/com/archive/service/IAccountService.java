package com.archive.service;

import com.archive.entity.AccountEntity;

public interface IAccountService {

    AccountEntity findById(Integer accountId);
}
