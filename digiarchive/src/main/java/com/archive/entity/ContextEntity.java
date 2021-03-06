package com.archive.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * The persistent class for the context database table.
 */
@Entity
@Table(name = "context")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class ContextEntity extends AbstractEntity{
    private Integer conserv_unit_id;
    private String mine_type;
    private LocalDateTime final_stage_date;
    private LocalDateTime archiving_reference_date;
    @Column(name = "final_business_processing_date")
    private LocalDateTime final_business_processing_date;
    @Column(name = "frozen_label")
    private String frozen_label;
    @Column(name = "hold_status")
    private boolean hold_status;
    @Column(name = "frozen")
    private boolean frozen;
    @Column(name = "final_hold_date")
    private LocalDateTime final_hold_date;
    @Column(name = "deletion_date")
    private LocalDateTime deletion_date;
    @LazyCollection(LazyCollectionOption.TRUE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "event_context", joinColumns = {
    @JoinColumn(name = "context_id", nullable = false, referencedColumnName = "id") }, inverseJoinColumns = {
    @JoinColumn(name = "event_id", nullable = false, referencedColumnName = "id") })
    private Set<EventEntity> events;
    @Column(name = "user_id", nullable = false)
    private Integer user_id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = true)
    private ContractEntity contract;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = true)
    private AccountEntity account;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_nature_id", nullable = false)
    private ClassificationNatureEntity classification_nature;


}
