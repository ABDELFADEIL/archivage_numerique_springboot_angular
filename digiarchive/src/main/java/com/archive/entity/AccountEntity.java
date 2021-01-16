package com.archive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The persistent class for the account database table.
 */
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountEntity extends AbstractEntity{
/*
*  fields
 */
    private String account_id_type_code;
    private String account_id_type_label;
    private String account_number;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
    private LocalDate  creating_date;
    private Integer user_id;
    @Column(name = "status", nullable = false)
    private EventStatus status;


}
