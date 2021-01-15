package com.archive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * The persistent class for the history database table.
 */
@Entity
@Table(name = "history")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoryEntity extends AbstractEntity{
    /*
    * fields
     */
    @JoinColumn(name = "history_date", nullable = false)
    private LocalDate history_date;
    @JoinColumn(name = "event_type", nullable = false)
    private String event_type;
    @JoinColumn(name = "calling_application", nullable = false)
    private String calling_application;
    @JoinColumn(name = "user_id", nullable = false)
    private String user_id;

}
