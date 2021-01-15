package com.archive.service;

import com.archive.dto.DocumentDto;
import com.archive.entity.ContextEntity;
import com.archive.entity.DigitalDocumentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

public interface IDocumentService {

    DigitalDocumentEntity createDocument(DigitalDocumentEntity document) throws IOException, NoSuchAlgorithmException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException;
    DigitalDocumentEntity getDocById(String docID);
    DigitalDocumentEntity updateContext(String docID, ContextEntity context);
    DigitalDocumentEntity saveDocFileWhithId(String docID, MultipartFile multipartFile) throws IOException, NoSuchAlgorithmException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException;
    Page<DigitalDocumentEntity> getAllDocs(Pageable pageable);
    DigitalDocumentEntity saveDoc(DigitalDocumentEntity doc);
    List<DigitalDocumentEntity> getAllDocsByContractId(String contract_id);
    List<DigitalDocumentEntity> getDocsAccountById(String account_id);
    List<DocumentDto> getDocumentDfbmIsNullArchivingDateBefore(LocalDateTime dateBefore) throws ParseException;
    boolean updateFBPD(List<DigitalDocumentEntity> documents);
    DigitalDocumentEntity save(DigitalDocumentEntity doc);

}
