package com.digiarchive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String classification_nature_label;
    private int classification_nature_code;
    private int duration;

}
