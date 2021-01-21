package com.archive.controller;


import com.archive.dto.DocumentDto;
import com.archive.entity.DigitalDocumentEntity;
import com.archive.entity.EventStatus;
import com.archive.service.IDocumentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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


    @RequestMapping( method = RequestMethod.GET ,value = "/getDocumentsBetweenDateAfterAndDateBefore", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<DigitalDocumentEntity> getDocumentsBetweenDateAfterAndDateBefore(
            @RequestParam(name = "dateBefore") String dateAfter,
            @RequestParam(name = "dateAfter") String dateBefore,
            @RequestParam(name = "eventType") String eventType
                                                                               )
    {
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateAfterFromated = null;
        LocalDateTime dateBeforeFromated = null;
        try {
             dateAfterFromated = LocalDateTime.parse(dateAfter);
             dateBeforeFromated = LocalDateTime.parse(dateBefore);
        }catch(Exception e){
            System.out.println("Error parseing !");
        }
        List<DigitalDocumentEntity>  documentEntities = null;
        return documentService.getDocumentsBetweenDateAfterAndDateBefore(dateAfterFromated, dateBeforeFromated, eventType);

    }





}
