package com.archive.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The persistent class for the contract database table.
 */
@Entity
@Table(name = "contract")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractEntity extends AbstractEntity{
    /*
    * Fields
    */
    @Column(name = "contract_id_type_code", nullable = false)
    private String contract_id_type_code;
    @Column(name = "contract_id_type_label", nullable = false)
    private String contract_id_type_label;
    // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @LazyCollection(LazyCollectionOption.TRUE)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
    @Column(name = "contract_number", nullable = false)
    private String contract_number;
    @Column(name = "creating_date", nullable = false)
    private LocalDateTime creating_date;
    @Column(name = "user_id", nullable = false)
    private Integer user_id;
    @Column(name = "status", nullable = false)
    private EventStatus status;

}
