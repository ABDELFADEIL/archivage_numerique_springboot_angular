package com.archive.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@AllArgsConstructor @NoArgsConstructor @Data
public abstract class AbstractEntity implements Serializable {
    private static final Logger LOG = LogManager.getLogger();
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

}
