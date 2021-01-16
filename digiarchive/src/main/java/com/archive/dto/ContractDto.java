package com.archive.dto;

import com.archive.entity.AbstractEntity;
import com.archive.entity.CustomerEntity;
import com.archive.entity.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractDto extends AbstractEntity {

    private String contract_id_type_code;
    private String contract_id_type_label;
    private Integer customerId;
    private String contract_number;
    private LocalDate creating_date;
    private Integer user_id;
    private EventStatus status;

}
