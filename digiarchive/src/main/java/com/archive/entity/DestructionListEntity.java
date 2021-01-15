package com.archive.entity;

//import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * The persistent class for the destructionList database table.
 */
@Entity
@Table(name = "destructionList")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DestructionListEntity extends AbstractEntity{

    @Column(name= "document_id", nullable = false)
    private Integer document_id;
    @Column(name= "validation", nullable = false)
    private boolean validation = false;
    @Column(name= "description", nullable = false)
    private String description;
    @Column(name= "validation_date", nullable = false)
    //@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDate validation_date;
    @Column(name= "user_validate_id", nullable = false)
    private String user_validate_id;

}
