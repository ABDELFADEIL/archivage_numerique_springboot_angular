package com.archive.dto;

import com.archive.entity.AbstractEntity;
import com.archive.entity.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto extends AbstractEntity {

    private String account_id_type_code;
    private String account_id_type_label;
    private String account_number;
    private Integer customerId;
    private LocalDate creating_date;
    private Integer user_id;
    private EventStatus status;


}
