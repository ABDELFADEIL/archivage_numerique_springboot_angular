package com.archive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the classificationNature database table.
 */
@Entity
@Table(name = "classificationNature")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClassificationNatureEntity extends AbstractEntity{
    /*
    * Fields
    */
    @Column(name = "classification_nature_label", length = 60, nullable = false, unique = true)
    private String classification_nature_label;
    @Column(name = "classification_nature_code", length = 12, nullable = false, unique = true)
    private int classification_nature_code;
    @Column(name = "duration", length = 12, nullable = false)
    private int duration;

}
