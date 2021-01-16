package com.archive.service;


import com.archive.dao.ContextRepository;
import com.archive.entity.ContextEntity;
import org.springframework.stereotype.Service;

@Service
public class ContextServiceImpl implements IContextService{

    private final ContextRepository contextRepository;

    public ContextServiceImpl(ContextRepository contextRepository) {
        this.contextRepository = contextRepository;
    }


    @Override
    public ContextEntity add(ContextEntity contextEntity) {
        return contextRepository.save(contextEntity);
    }

    @Override
    public ContextEntity findById(Integer contextEntityId) {
        return contextRepository.findById(contextEntityId).get();
    }
}
