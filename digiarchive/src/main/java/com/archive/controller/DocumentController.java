package com.archive.controller;


import com.archive.dto.DocumentDto;
import com.archive.entity.DigitalDocumentEntity;
import com.archive.service.IDocumentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/document")
@CrossOrigin("*")
public class DocumentController {

    private final IDocumentService documentService;

    public DocumentController(IDocumentService documentService) {
        this.documentService = documentService;
    }


    @RequestMapping(value = "/add-documents", produces = { "application/json;charset=UTF-8" }, consumes = {"application/json;charset=UTF-8" })
    public List<DigitalDocumentEntity>  addDocuments(@RequestBody List<DocumentDto> documentDtoList){
        List<DigitalDocumentEntity> documentEntities = new ArrayList<>();
        documentDtoList.forEach(documentDto -> {
            System.out.println(documentDto);
            DigitalDocumentEntity documentEntity = documentService.add(documentDto);
            documentEntities.add(documentEntity);
         });
        return documentEntities;
    }
}
