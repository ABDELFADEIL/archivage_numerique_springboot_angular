package com.archive.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.archive.entity.ClassificationNatureEntity;
import com.archive.entity.UserEntity;

import java.util.List;

@Repository
public interface ClassificationNatureRepository extends JpaRepository<ClassificationNatureEntity, Integer>{

    @Query("select cn from ClassificationNatureEntity cn where cn.classification_nature_code=:classificationNatureCode")
    public ClassificationNatureEntity findByClassification_nature_code(@Param("classificationNatureCode") int classificationNatureCode);
    @Query("select c from ClassificationNatureEntity c where c.classification_nature_label like %:keyword% or c.classification_nature_code like %:keyword%")
    public List<ClassificationNatureEntity> getAllClassificationNatureByKeyWord(@Param("keyword") String keyword);
    @Query(value="SELECT * FROM classification_nature WHERE classification_nature.classification_nature_id= :classification_nature_id", nativeQuery=true)
    public ClassificationNatureEntity findByClassificationId(@Param("classification_nature_id") Integer classification_nature_id);

}
