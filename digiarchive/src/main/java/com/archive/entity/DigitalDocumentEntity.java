package com.archive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * The persistent class for the document database table.
 */
@Entity
@Table(name = "document")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DigitalDocumentEntity extends AbstractEntity{

    @Column(name= "file_name", nullable = false)
    private String file_name;
    @Column(name= "archive_format", nullable = false)
    private String archive_format;
    @Column(name = "encoded_doc", nullable = false, columnDefinition = "MEDIUMTEXT")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String encodedDoc;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "context_id", nullable = false)
    private ContextEntity context;


}
