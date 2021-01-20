package com.archive.service;


import com.archive.dao.AccountRepository;
import com.archive.dto.AccountDto;
import com.archive.entity.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class AccountServiceImpl implements IAccountService{

    private final AccountRepository accountRepository;
    private final ICustomerService customerService;
    private final IUserService userService;

    public AccountServiceImpl(AccountRepository accountRepository, ICustomerService customerService, IUserService userService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;

        this.userService = userService;
    }

    @Override
    public AccountEntity findById(Integer accountId) {
        return accountRepository.findById(accountId).get();
    }


    @Override
    public AccountEntity add(AccountDto accountDto) {

        UserEntity user = userService.getAuthentificatedUser();
        CustomerEntity customerEntity = customerService.findById(accountDto.getCustomerId());
        String account_number = createNewAccountNumber();
        AccountEntity accountEntity = new AccountEntity( accountDto.getAccount_id_type_code(),  accountDto.getAccount_id_type_label(),
                account_number, customerEntity, LocalDateTime.now(), user.getId(), EventStatus.START_CUSTOMER_RELATIONSHIP);

        return accountRepository.save(accountEntity);
    }

    @Override
    public AccountEntity update(AccountDto accountDto) {
        EventStatus eventStatus = accountDto.getStatus();
        AccountEntity account = findById(accountDto.getId());
        if (account == null) throw new RuntimeException( "id does not existe");

        if (account.getAccount_id_type_code() != accountDto.getAccount_id_type_code()){
            account.setAccount_id_type_code(accountDto.getAccount_id_type_code());
        }
        if (account.getAccount_id_type_label() != accountDto.getAccount_id_type_label()){
            account.setAccount_id_type_label(accountDto.getAccount_id_type_label());
        }
        if (account.getStatus() != accountDto.getStatus()){
            account.setStatus(accountDto.getStatus());
        }

        return accountRepository.save(account);
    }

    @Override
    public boolean delete(Integer accounttId) {
        try {
            accountRepository.deleteById(accounttId);
            return true;
        }catch (Exception e){

        }
        return false;
    }

    @Override
    public AccountEntity getAccountByNumber(String account_number) {
        return accountRepository.findByAccount_number(account_number);
    }

    @Override
    public Set<AccountEntity> getAccountsByAccountNumberContains(String account_number) {
        return accountRepository.findByAccount_numberContains(account_number);
    }

    @Override
    public Set<AccountEntity> getAccountsByClientNameContains(String customer_name) {
        return accountRepository.getAccountsByClientNameContains(customer_name);
    }

    @Override
    public Set<AccountEntity> getAccountstsByClientNameAndAccountNumberContains(String customer_name, String account_number) {
        return accountRepository.getAccountstsByClientNameAndAccountNumberContains(customer_name, account_number);
    }

    @Override
    public Set<AccountEntity> getAccountByEventTypeAndDateBetween(int eventType, LocalDate dateAfter, LocalDate dateBefor) {
        return accountRepository.getAccountByEventTypeAndDateBetween(eventType, dateAfter, dateBefor);
    }


    public String getMaxAccountNumber() {
        return accountRepository.findMaxAccountNumber();
    }

    public String createNewAccountNumber() {

        Random random = new Random();
        String account_number_nex = random.nextLong()+"";
        return account_number_nex;
    }
}
