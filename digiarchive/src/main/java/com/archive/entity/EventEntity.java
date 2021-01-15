package com.archive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * The persistent class for the event database table.
 */
@Entity
@Table(name = "event")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventEntity extends AbstractEntity{
    /*
    * fields
     */
    @JoinColumn(name = "eventDate", nullable = false)
    private LocalDateTime eventDate;
    @Column(name = "status", nullable = false)
    private EventStatus status;


}
