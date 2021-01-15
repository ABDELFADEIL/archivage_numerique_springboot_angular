package com.archive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * The persistent class for the customer database table.
 */
@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerEntity extends AbstractEntity{

    /*
    /* Fields
    */
    @Column(name = "client_number", nullable = false)
    private String client_number;
    @Column(name = "client_nature_id", nullable = false)
    private int client_nature_id;
    @Column(name = "client_name", nullable = false)
    private String client_name;
    @Column(name = "client_first_name", nullable = true)
    private String client_first_name;
    @Column(name = "civility_id", nullable = false)
    private int civility_id;
    @Column(name = "birth_date", nullable = false)
    private Date birth_date;
    @Column(name = "birth_dept", nullable = true)
    private String birth_dept;
    @Column(name = "siren_number", nullable = true)
    private String siren_number;
    @Column(name = "siret_number", nullable = true)
    private String siret_number;
    @Column(name = "user_id", nullable = false)
    private String user_id;
    @Column(name = "status", nullable = false)
    private EventStatus status;

}
