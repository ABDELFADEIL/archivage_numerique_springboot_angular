package com.archive.controller;

import com.archive.entity.ClassificationNatureEntity;
import com.archive.service.IClassificationNatureService;
import com.archive.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classificationNature")
@CrossOrigin("**")
public class ClassificationNatureController {

    private final IClassificationNatureService classificationNatureService;

    public ClassificationNatureController(IClassificationNatureService classificationNatureService) {
        this.classificationNatureService = classificationNatureService;
    }

    @PostMapping("/create-classificationNature")
    public ClassificationNatureEntity addClassificationNature(@RequestBody(required = true) ClassificationNatureEntity classificationNature) {
        return classificationNatureService.addClassificationNature(classificationNature);
    }


    @GetMapping("/get-classificationNature-code")
    public ClassificationNatureEntity findByClassificationNatureCode(@RequestParam(value = "classificationNature", required = true) int classificationNatureCode) {
        return classificationNatureService.findByClassificationNatureCode(classificationNatureCode);
    }

    @GetMapping("/get-all-classificationNature")
    public List<ClassificationNatureEntity> getAll() {
        return classificationNatureService.getAllClassificationNature();
    }

    @GetMapping("/get-all-classificationNature-keyWord")
    public List<ClassificationNatureEntity> getByKeyWord(@RequestParam(value = "keyword", required = true, defaultValue = "") String keyword) {
        return classificationNatureService.getAllClassificationNatureByKeyWord(keyword);
    }

    @GetMapping("/get-by-id")
    public ClassificationNatureEntity getById(@RequestParam(value = "classificationNatureId") Integer classificationNatureId){
        return classificationNatureService.findByClassificationNatureId(classificationNatureId);
    }

    @PutMapping("/update-one")
    public ClassificationNatureEntity updateClassificationNature(@RequestBody ClassificationNatureEntity classificationNature) {
        return classificationNatureService.updateClassificationNature(classificationNature);
    }

    @DeleteMapping("/delete-one")
    public void removeClassificationNature(@RequestParam(value = "classificationNatureId") Integer classificationNatureId) {
        classificationNatureService.removeClassificationNature(classificationNatureId);
    }
}
