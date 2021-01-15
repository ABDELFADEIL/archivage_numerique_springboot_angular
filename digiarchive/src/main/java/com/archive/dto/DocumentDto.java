package com.archive.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DocumentDto {

    private String document_id;
    private String conserv_unit_id;
    private Date archiving_reference_date;
    private String frozen_label;
    private boolean hold_status;
    private boolean frozen;
    private String context_id;
    private String classification_nature_id;
    private String classification_nature_label;
    private int classification_nature_code;
    private int duration;
    private String id_event;
    private String event_type;
    private Date event_date;

}
