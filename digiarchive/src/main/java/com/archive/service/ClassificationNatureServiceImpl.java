package com.archive.service;


import com.archive.dao.ClassificationNatureRepository;
import com.archive.entity.ClassificationNatureEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationNatureServiceImpl implements IClassificationNatureService{

    private final ClassificationNatureRepository classificationNatureRepository;

    public ClassificationNatureServiceImpl(ClassificationNatureRepository classificationNatureRepository) {
        this.classificationNatureRepository = classificationNatureRepository;
    }


    @Override
    public ClassificationNatureEntity addClassificationNature(ClassificationNatureEntity classificationNature) {
        ClassificationNatureEntity cn = new ClassificationNatureEntity
                (
                        classificationNature.getClassification_nature_label(),
                        classificationNature.getClassification_nature_code(),
                        classificationNature.getDuration()
                );
        return classificationNatureRepository.save(cn);
    }

    @Override
    public ClassificationNatureEntity findByClassificationNatureCode(int classificationNatureCode) {
        return classificationNatureRepository.findByClassification_nature_code(classificationNatureCode);
    }

    @Override
    public ClassificationNatureEntity findById(Integer id) {
        return classificationNatureRepository.findByClassificationId(id);
    }

    @Override
    public ClassificationNatureEntity updateClassificationNature(ClassificationNatureEntity classificationNature) {
        return classificationNatureRepository.save(classificationNature);
    }

    @Override
    public void removeClassificationNature(Integer classificationNatureId) {
        classificationNatureRepository.deleteById(classificationNatureId);
    }

    @Override
    public ClassificationNatureEntity findByClassificationNatureId(Integer classificationNatureId) {
        return classificationNatureRepository.findById(classificationNatureId).get();
    }

    @Override
    public List<ClassificationNatureEntity> getAllClassificationNature() {
        return classificationNatureRepository.findAll();
    }

    @Override
    public Page<ClassificationNatureEntity> getAllClassificationNature(int page, int size) {
        return  classificationNatureRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<ClassificationNatureEntity> getAllClassificationNatureByKeyWord(String keyword) {
        return classificationNatureRepository.getAllClassificationNatureByKeyWord(keyword);
    }
}
