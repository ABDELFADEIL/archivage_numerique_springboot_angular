package com.archive.service;

import com.archive.dto.DocumentDto;
import com.archive.entity.DigitalDocumentEntity;
import com.archive.entity.EventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface IDocumentService {

    DigitalDocumentEntity add(DocumentDto documentDto);
    DigitalDocumentEntity getById(Integer documentId);
    DigitalDocumentEntity update(Integer documentId, DocumentDto documentDto);
    List<DigitalDocumentEntity> addEvent(Integer customerId, Integer accountId, Integer contractId, EventStatus status);
    Page<DigitalDocumentEntity> getAllDocs(Pageable pageable);
    List<DigitalDocumentEntity> getDocumentByContractId(Integer contract_id);
    List<DigitalDocumentEntity> getDocsAccountById(Integer account_id);
    List<DigitalDocumentEntity> getDocumentDfbmIsNullArchivingDateBefore(LocalDateTime dateBefore) throws ParseException;
    List<DigitalDocumentEntity> updateFBPD(List<DocumentDto> documents);
    DigitalDocumentEntity save(DigitalDocumentEntity digitalDocumentEntity);
    List<DigitalDocumentEntity> getDocumentsBetweenDateAfterAndDateBefore(LocalDateTime dateBefore, LocalDateTime dateAfter, String eventType);
}
