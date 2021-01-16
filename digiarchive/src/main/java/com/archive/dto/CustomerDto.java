package com.archive.dto;

import com.archive.entity.AbstractEntity;
import com.archive.entity.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto extends AbstractEntity {

    private String client_number;
    private int client_nature_id;
    private String client_name;
    private String client_first_name;
    private int civility_id;
    private Date birth_date;
    private String birth_dept;
    private String siren_number;
    private String siret_number;
    private Integer user_id;
    private EventStatus status;

}
