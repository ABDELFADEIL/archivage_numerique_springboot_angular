package com.archive.service;


import com.archive.dao.*;
import com.archive.dto.DocumentDto;
import com.archive.dto.DocumentDtoIn;
import com.archive.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDateTime;
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

    public DigitalDocumentEntity add(DocumentDtoIn documentDtoIn)
    {
        final CustomerEntity customerEntity = customerService.findById(documentDtoIn.getContextDtoIn().getCustomerId());
        final AccountEntity accountEntity = accountService.findById(documentDtoIn.getContextDtoIn().getAccountId());
        final ContractEntity contractEntity = contractService.findById(documentDtoIn.getContextDtoIn().getContractId());
        final ClassificationNatureEntity classificationNatureEntity = classificationNatureService.findById(documentDtoIn.getContextDtoIn().getContractId());
        final EventEntity eventEntity = eventService.add(documentDtoIn.getContextDtoIn().getEventType());
        Set<EventEntity> eventEntitySet = new HashSet<>();
        eventEntitySet.add(eventEntity);
        final Integer userId = userService.getAuthentificatedUser().getId();

        //  final_stage_date
        java.time.LocalDateTime final_stage_date = null;
        if (documentDtoIn.getContextDtoIn().getFinal_business_processing_date()!=null){
            final_stage_date = documentDtoIn.getContextDtoIn().getFinal_business_processing_date().plusYears(classificationNatureEntity.getDuration());
        }

        ContextEntity contextEntity = new ContextEntity(null, documentDtoIn.getContextDtoIn().getMine_type(), final_stage_date,
                LocalDateTime.now(),  documentDtoIn.getContextDtoIn().getFinal_business_processing_date(),
                null, false, false, null, null, eventEntitySet,userId, customerEntity,
                contractEntity, accountEntity, classificationNatureEntity);
        ContextEntity context = contextService.add(contextEntity);

        DigitalDocumentEntity digitalDocumentEntity = new DigitalDocumentEntity(documentDtoIn.getFile_name(), "file",
                documentDtoIn.getEncodedDoc(), context );
        return digitalDocumentRepository.save(digitalDocumentEntity);
    }

    @Override
    public DigitalDocumentEntity createDocument(DigitalDocumentEntity document) throws IOException, NoSuchAlgorithmException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException
    {
        return null;
    }

    @Override
    public DigitalDocumentEntity getDocById(String docID) {
        return null;
    }

    @Override
    public DigitalDocumentEntity updateContext(String docID, ContextEntity context) {
        return null;
    }

    @Override
    public DigitalDocumentEntity saveDocFileWhithId(String docID, MultipartFile multipartFile) throws IOException, NoSuchAlgorithmException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException {
        return null;
    }

    @Override
    public Page<DigitalDocumentEntity> getAllDocs(Pageable pageable) {
        return null;
    }

    @Override
    public DigitalDocumentEntity saveDoc(DigitalDocumentEntity doc) {
        return null;
    }

    @Override
    public List<DigitalDocumentEntity> getAllDocsByContractId(String contract_id) {
        return null;
    }

    @Override
    public List<DigitalDocumentEntity> getDocsAccountById(String account_id) {
        return null;
    }

    @Override
    public List<DocumentDto> getDocumentDfbmIsNullArchivingDateBefore(LocalDateTime dateBefore) throws ParseException {
        return null;
    }

    @Override
    public boolean updateFBPD(List<DigitalDocumentEntity> documents) {
        return false;
    }

    @Override
    public DigitalDocumentEntity save(DigitalDocumentEntity doc) {
        return null;
    }
}
