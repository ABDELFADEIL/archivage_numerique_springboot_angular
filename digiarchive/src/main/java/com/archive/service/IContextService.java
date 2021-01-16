package com.archive.service;


import com.archive.entity.ContextEntity;

public interface IContextService {

    ContextEntity add(ContextEntity contextEntity);

    ContextEntity update(ContextEntity contextEntity);

    ContextEntity findById(Integer contextEntityId);

    boolean deleteById(Integer contextEntityId);
    ContextEntity save(ContextEntity contextEntity);
}
