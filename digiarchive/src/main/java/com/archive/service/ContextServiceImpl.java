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
    public ContextEntity update(ContextEntity contextEntity) {
        return contextRepository.save(contextEntity);
    }

    @Override
    public ContextEntity findById(Integer contextEntityId) {
        return contextRepository.findById(contextEntityId).get();
    }

    @Override
    public boolean deleteById(Integer contextEntityId) {
        try{
            contextRepository.deleteById(contextEntityId);
            return true;
        }catch (Exception e){

        }
        return false;
    }

    @Override
    public ContextEntity save(ContextEntity contextEntity) {
        return contextRepository.save(contextEntity);
    }
}
