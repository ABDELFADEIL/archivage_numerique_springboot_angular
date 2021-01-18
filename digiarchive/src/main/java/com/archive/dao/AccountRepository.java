package com.archive.dao;

import com.archive.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.archive.entity.UserEntity;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer>{

    @Query(value="select MAX(a.account_number) from account a group by a.account_number order by a.account_number desc limit 1", nativeQuery=true)
    public String findMaxAccountNumber();
    @Query("select account from AccountEntity account where account.account_number=:account_number")
    AccountEntity findByAccount_number(@Param("account_number") String account_number);
    @Query("select account from AccountEntity account where account.account_number like %:account_number%")
    Set<AccountEntity> findByAccount_numberContains(@Param("account_number") String account_number);
    @Query("select account from AccountEntity account where account.customer.client_name like %:client_name% or account.customer.client_first_name like %:client_name%")
    Set<AccountEntity> getAccountsByClientNameContains(@Param("client_name") String client_name);
    //findAccountByStatusAndEventDateAfterAndEvenDateBefor
    @Query(value="select * from  account where account.event in (select id_event from  event where event_type=:status and event_date between :dateAfter and :dateBefor)", nativeQuery=true)
    Set<AccountEntity> getAccountByEventTypeAndDateBetween(@Param("status") int eventType, @Param("dateAfter") LocalDate dateAfter, @Param("dateBefor") LocalDate dateBefor);
    @Query("select account from AccountEntity account where  account.account_number like %:account_number% or (account.customer.client_name like %:client_name% or account.customer.client_first_name like %:client_name%)")
    Set<AccountEntity> getAccountstsByClientNameAndAccountNumberContains(@Param("client_name") String client_name, @Param("account_number") String account_number);
}
