package com.archive.service;


import com.archive.dao.*;
import com.archive.dto.DocumentDto;
import com.archive.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.events.DocumentEndEvent;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DocumentServiceImpl implements IDocumentService{


    private final DigitalDocumentRepository digitalDocumentRepository;
    private final IContextService contextService;
    private final ICustomerService customerService;
    private final IAccountService accountService;
    private final IContractService contractService;
    private final IUserService userService;
    private final IClassificationNatureService classificationNatureService;
    private final IEventService eventService;



    public DocumentServiceImpl(
            DigitalDocumentRepository digitalDocumentRepository,
            IContextService contextService,
            ICustomerService customerService,
            IAccountService accountService,
            IContractService contractService,
            IUserService userService,
            IClassificationNatureService classificationNatureService,
            IEventService eventService
    )
    {
        this.digitalDocumentRepository = digitalDocumentRepository;
        this.contextService = contextService;
        this.customerService = customerService;
        this.accountService = accountService;
        this.contractService = contractService;
        this.userService = userService;
        this.classificationNatureService = classificationNatureService;
        this.eventService = eventService;
    }

    @Override
    public DigitalDocumentEntity add(DocumentDto documentDto)
    {
        final CustomerEntity customerEntity = customerService.findById(documentDto.getContextDtoIn().getCustomerId());
        final AccountEntity accountEntity = accountService.findById(documentDto.getContextDtoIn().getAccountId());
        final ContractEntity contractEntity = contractService.findById(documentDto.getContextDtoIn().getContractId());
        final ClassificationNatureEntity classificationNatureEntity = classificationNatureService.findById(documentDto.getContextDtoIn().getContractId());
        final EventEntity eventEntity = eventService.add(documentDto.getContextDtoIn().getEventType());
        Set<EventEntity> eventEntitySet = new HashSet<>();
        eventEntitySet.add(eventEntity);
        final Integer userId = userService.getAuthentificatedUser().getId();

        //  final_stage_date
        java.time.LocalDateTime final_stage_date = null;
        if (documentDto.getContextDtoIn().getFinal_business_processing_date()!=null){
            final_stage_date = documentDto.getContextDtoIn().getFinal_business_processing_date().plusYears(classificationNatureEntity.getDuration());
        }

        ContextEntity contextEntity = new ContextEntity(null, documentDto.getContextDtoIn().getMine_type(), final_stage_date,
                LocalDateTime.now(),  documentDto.getContextDtoIn().getFinal_business_processing_date(),
                null, false, false, null, null, eventEntitySet,userId, customerEntity,
                contractEntity, accountEntity, classificationNatureEntity);
        ContextEntity context = contextService.add(contextEntity);

        DigitalDocumentEntity digitalDocumentEntity = new DigitalDocumentEntity(documentDto.getFile_name(), "file",
                documentDto.getEncodedDoc(), context );
        return digitalDocumentRepository.save(digitalDocumentEntity);
    }

    @Override
    public DigitalDocumentEntity update(Integer documentId, DocumentDto documentDto) {

        DigitalDocumentEntity documentEntity = digitalDocumentRepository.findById(documentId).get();
        if (documentEntity == null) throw new RuntimeException( "document does not existe");
        if (documentDto.getArchive_format() != documentEntity.getArchive_format()){
            documentEntity.setArchive_format(documentDto.getArchive_format());
        }
        if (documentDto.getEncodedDoc() != documentEntity.getEncodedDoc()){
            documentEntity.setEncodedDoc(documentDto.getEncodedDoc());
        }
        if (documentDto.getFile_name() != documentEntity.getFile_name()){
            documentEntity.setFile_name(documentDto.getFile_name());
        }



        if (documentDto.getContextDtoIn().getMine_type() != documentEntity.getContext().getMine_type()){
            documentEntity.getContext().setMine_type(documentDto.getContextDtoIn().getMine_type());
        }
        if (documentDto.getContextDtoIn().getConserv_unit_id() != documentEntity.getContext().getConserv_unit_id()){
            documentEntity.getContext().setConserv_unit_id(documentDto.getContextDtoIn().getConserv_unit_id());
        }

        if (documentDto.getContextDtoIn().getFrozen_label() != documentEntity.getContext().getFrozen_label()){
            documentEntity.getContext().setFrozen_label(documentDto.getContextDtoIn().getFrozen_label());
        }
        if (documentDto.getContextDtoIn().getFinal_hold_date() != documentEntity.getContext().getFinal_hold_date()){
            documentEntity.getContext().setFinal_hold_date(documentDto.getContextDtoIn().getFinal_hold_date());
        }
        if (documentDto.getContextDtoIn().isFrozen() != documentEntity.getContext().isFrozen()){
            documentEntity.getContext().setFrozen(documentDto.getContextDtoIn().isFrozen());
        }
        if (documentDto.getContextDtoIn().getDeletion_date().equals(documentEntity.getContext().getDeletion_date())){
            documentEntity.getContext().setDeletion_date(documentDto.getContextDtoIn().getDeletion_date());
        }

        documentEntity.getContext().getEvents().forEach( eventEntity -> {
            EventStatus eventStatus = EventStatus.fromValue(documentDto.getContextDtoIn().getEventType());
            if (eventEntity.getStatus() != eventStatus){
                EventEntity event = eventService.add(documentDto.getContextDtoIn().getEventType());
                documentEntity.getContext().getEvents().add(event);
            }
        });

        if (documentEntity.getContext().getFinal_business_processing_date()==null && documentDto.getContextDtoIn().getFinal_business_processing_date() != null){
            java.time.LocalDateTime final_stage_date = null;
            documentEntity.getContext().setFinal_business_processing_date(documentDto.getContextDtoIn().getFinal_business_processing_date());
            final_stage_date = documentDto.getContextDtoIn().getFinal_business_processing_date().plusYears(documentEntity.getContext().getClassification_nature().getDuration());
            documentEntity.getContext().setFinal_stage_date(final_stage_date);
        }

        contextService.save(documentEntity.getContext());
        DigitalDocumentEntity document = digitalDocumentRepository.save(documentEntity);
        return document;
    }


    @Override
    public DigitalDocumentEntity getById(Integer documentId) {
        return digitalDocumentRepository.findById(documentId).get();
    }




    @Override
    public Page<DigitalDocumentEntity> getAllDocs(Pageable pageable) {
        return digitalDocumentRepository.findAll(pageable);
    }

    @Override
    public List<DigitalDocumentEntity> getDocumentByContractId(Integer contract_id) {
        return digitalDocumentRepository.getDocsContractById(contract_id);
    }


    @Override
    public List<DigitalDocumentEntity> getDocsAccountById(Integer account_id) {
        return digitalDocumentRepository.getDocsAccountById(account_id);
    }

    @Override
    public List<DigitalDocumentEntity> getDocumentDfbmIsNullArchivingDateBefore(LocalDateTime dateBefore)  {
        return null; // digitalDocumentRepository.getDocumentDfbmIsNullArchivingDateBefore(dateBefore);
    }

    @Override
    public  List<DigitalDocumentEntity> updateFBPD(List<DocumentDto> documents) {
        List<DigitalDocumentEntity> documentList = new ArrayList<>();
        for (DocumentDto documentDto :    documents) {
                DigitalDocumentEntity documentEntity = digitalDocumentRepository.findById(documentDto.getId()).get();
                if (documentEntity.getContext().getFinal_business_processing_date()==null && documentDto.getContextDtoIn().getFinal_business_processing_date() != null){
                    java.time.LocalDateTime final_stage_date = null;
                    documentEntity.getContext().setFinal_business_processing_date(documentDto.getContextDtoIn().getFinal_business_processing_date());
                    final_stage_date = documentDto.getContextDtoIn().getFinal_business_processing_date().plusYears(documentEntity.getContext().getClassification_nature().getDuration());
                    documentEntity.getContext().setFinal_stage_date(final_stage_date);
                    contextService.save(documentEntity.getContext());
                    DigitalDocumentEntity document = digitalDocumentRepository.save(documentEntity);
                    documentList.add(document);
                }
        }

        return documentList;
    }

    @Override
    public DigitalDocumentEntity save(DigitalDocumentEntity documentEntity) {
        return digitalDocumentRepository.save(documentEntity);
    }
}
