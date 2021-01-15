package com.archive.service;

import com.archive.entity.ClassificationNatureEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IClassificationNatureService {

    ClassificationNatureEntity addClassificationNature(ClassificationNatureEntity classificationNature);
    ClassificationNatureEntity findByClassificationNatureCode(int classificationNatureCode);
    ClassificationNatureEntity findById(Integer id);
    ClassificationNatureEntity updateClassificationNature(ClassificationNatureEntity classificationNature);
    void removeClassificationNature(Integer classificationNatureId);
    ClassificationNatureEntity findByClassificationNatureId(Integer classificationNatureId);
    List<ClassificationNatureEntity> getAllClassificationNature();
    Page<ClassificationNatureEntity> getAllClassificationNature(int page, int size);
    List<ClassificationNatureEntity> getAllClassificationNatureByKeyWord(String keyword);
}
