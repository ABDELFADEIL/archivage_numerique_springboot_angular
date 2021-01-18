package com.archive.dto;

import com.archive.entity.AbstractEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ContextDtoIn extends AbstractEntity {

    private Integer conserv_unit_id;
    private String mine_type;
    private LocalDateTime archiving_reference_date;
    private LocalDateTime final_business_processing_date;
    private String frozen_label;
    private boolean hold_status;
    private boolean frozen;
    private LocalDateTime final_hold_date;
    private LocalDateTime deletion_date;
    private Integer classification_natureId;
    private int eventType;
    private Integer contractId;
    private Integer accountId;
    private Integer customerId;



}
