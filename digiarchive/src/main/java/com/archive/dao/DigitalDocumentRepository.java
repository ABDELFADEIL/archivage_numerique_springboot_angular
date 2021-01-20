package com.archive.dao;

import com.archive.dto.DocumentDto;
import com.archive.entity.DigitalDocumentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.archive.entity.UserEntity;

import javax.persistence.QueryHint;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;
import static org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH;

@Repository
public interface DigitalDocumentRepository extends JpaRepository<DigitalDocumentEntity, Integer>{

    @Query(value="select doc from DigitalDocumentEntity doc")
    @QueryHints(value={ @QueryHint(name= HINT_FETCH_SIZE, value=""+Integer.MIN_VALUE), @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public Page<DigitalDocumentEntity> getAllDocs(Pageable pageable);

    @Query(value="select * from document limit 1000",  nativeQuery=true)
    @QueryHints(value= {@QueryHint(name= HINT_FETCH_SIZE, value=""+Integer.MIN_VALUE), @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public List<DigitalDocumentEntity> getAllDocs();
    // where digital_document.context.event not null order by digital_document.context.event.event_date
    //@Query(value="select document_id, context.context_id, context.conserv_unit_id, context.final_stage_date, context.archiving_reference_date, context.final_business_processing_date, context.frozen_label, context.hold_status, context.frozen, classification_nature, event FROM digital_document")
    //@CriteriaQuery
    //@QueryHints(value= {@QueryHint(name= HINT_FETCH_SIZE, value=""+Integer.MIN_VALUE), @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false")})
    //public List<DigitalDocument> getAllMetadata();

    @Query(value="select doc from DigitalDocumentEntity doc")
    @QueryHints(value= {@QueryHint(name= HINT_FETCH_SIZE, value=""+Integer.MIN_VALUE), @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public Stream<DigitalDocumentEntity> getAllDocsStream();
    @Query(value = "select document from DigitalDocumentEntity document where document.context.contract.id=:contractId")
    List<DigitalDocumentEntity> getDocsContractById(@Param("contractId") Integer contractId);
    @Query(value = "select document from DigitalDocumentEntity document where document.context.account.id=:accountId")
    List<DigitalDocumentEntity> getDocsAccountById(@Param("accountId") Integer accountId);
    @Query(value = "select document from DigitalDocumentEntity document where document.context.customer.id=:customerId")
    List<DigitalDocumentEntity> getDocsClientById(@Param("customerId") String customerId);

    @Query(value = "select doc.* from document doc WHERE doc.context_id in (select c.id from context c INNER JOIN event e On e.id=c.event WHERE c.customer_id=:client_id AND e.status=:eventType AND c.account_id IS NULL AND c.contract_id IS NULL)", nativeQuery = true)
    List<DigitalDocumentEntity> findByEventTypeAndClientId(@Param("eventType") String eventType, @Param("client_id") String client_id);

    // List<DigitalDocumentEntity> getDocumentDfbmIsNullArchivingDateBefore(LocalDateTime dateBefore);
}
