package com.archive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContextDtoIn implements Serializable {

    private String conserv_unit_id;
    private String mine_type;
    private LocalDateTime final_stage_date;
    private LocalDateTime archiving_reference_date;
    private LocalDateTime final_business_processing_date;
    private String frozen_label;
    private boolean hold_status;
    private boolean frozen;
    private LocalDateTime final_hold_date;
    private LocalDateTime deletion_date;
    private Integer classification_natureId;
    private int eventType;



    private Integer customerId;

    private Integer contractId;

    private Integer accountId;
}
