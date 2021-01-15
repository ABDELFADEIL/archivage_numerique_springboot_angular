package com.archive.dao;

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
import java.util.List;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;
import static org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH;

@Repository
public interface DigitalDocumentRepository extends JpaRepository<DigitalDocumentEntity, Integer>{

    /*
    @Query(value="select doc from DigitalDocumentEntity doc")
    @QueryHints(value={ @QueryHint(name= HINT_FETCH_SIZE, value=""+Integer.MIN_VALUE), @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public Page<DigitalDocumentEntity> getAllDocs(Pageable pageable);

    @Query(value="select * from digital_document limit 1000",  nativeQuery=true)
    @QueryHints(value= {@QueryHint(name= HINT_FETCH_SIZE, value=""+Integer.MIN_VALUE), @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public List<DigitalDocumentEntity> getAllDocs();
    // where digital_document.context.event not null order by digital_document.context.event.event_date
    //@Query(value="select document_id, context.context_id, context.conserv_unit_id, context.final_stage_date, context.archiving_reference_date, context.final_business_processing_date, context.frozen_label, context.hold_status, context.frozen, classification_nature, event FROM digital_document")
    //@CriteriaQuery
    //@QueryHints(value= {@QueryHint(name= HINT_FETCH_SIZE, value=""+Integer.MIN_VALUE), @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false")})
    //public List<DigitalDocument> getAllMetadata();

    @Query(value="select doc from DigitalDocument doc")
    @QueryHints(value= {@QueryHint(name= HINT_FETCH_SIZE, value=""+Integer.MIN_VALUE), @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false")})
    public Stream<DigitalDocumentEntity> getAllDocsStream();
    @Query(value = "SELECT * FROM digital_document WHERE digital_document.context in(SELECT c.context_id FROM context c WHERE c.contract=:contract_id)", nativeQuery = true)
    List<DigitalDocumentEntity> getDocsContractById(@Param("contract_id") String contract_id);
    @Query(value = "SELECT * FROM digital_document WHERE digital_document.context in(SELECT c.context_id FROM context c WHERE c.account=:account_id)", nativeQuery = true)
    List<DigitalDocumentEntity> getDocsAccountById(@Param("account_id") String account_id);
    @Query(value = "SELECT * FROM digital_document WHERE digital_document.context in(SELECT c.context_id FROM context c WHERE c.client=:client_id)", nativeQuery = true)
    List<DigitalDocumentEntity> getDocsClientById(@Param("client_id") String client_id);

    @Query(value = "select doc.* from digital_document doc WHERE doc.context in (select c.context_id from context c INNER JOIN event e On e.id_event=c.event WHERE c.client=:client_id AND e.event_type=:eventType AND c.account IS NULL AND c.contract IS NULL)", nativeQuery = true)
    List<DigitalDocumentEntity> findByEventTypeAndClientId(@Param("eventType") String eventType, @Param("client_id") String client_id);

     */

}
