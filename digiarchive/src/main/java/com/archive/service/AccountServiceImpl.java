package com.archive.service;


import com.archive.dao.AccountRepository;
import com.archive.dto.AccountDto;
import com.archive.entity.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    public List<AccountEntity> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public AccountEntity add(AccountDto accountDto) {

        UserEntity user = userService.getAuthentificatedUser();
        CustomerEntity customerEntity = customerService.findById(accountDto.getCustomerId());
        String account_number = createNewAccountNumber();
        AccountEntity accountEntity = new AccountEntity( accountDto.getAccount_id_type_code(),  accountDto.getAccount_id_type_label(),
                account_number, customerEntity, LocalDate.now(), user.getId(), EventStatus.START_CUSTOMER_RELATIONSHIP);

        return accountRepository.save(accountEntity);
    }

    @Override
    public AccountEntity update(AccountDto accountDto) {

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


    public String getMaxAccountNumber() {
        return accountRepository.findMaxAccountNumber();
    }

    public String createNewAccountNumber() {

        String account_number_pre = getMaxAccountNumber();
        if (account_number_pre == null){
            account_number_pre = "00000000000";
        }
        long account_number =  Long.parseLong(account_number_pre);
        long new_account_number = account_number + 1;
        String account_number_nex = "00000000000".substring(String.valueOf(new_account_number).length()+1)+new_account_number;
        return account_number_nex;
    }
}
