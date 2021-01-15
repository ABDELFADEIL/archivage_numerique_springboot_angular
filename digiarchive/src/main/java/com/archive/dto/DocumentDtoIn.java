package com.archive.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentDtoIn implements Serializable {

    private String file_name;
    private String archive_format;
    private String encodedDoc;
    private ContextDtoIn contextDtoIn;
}
