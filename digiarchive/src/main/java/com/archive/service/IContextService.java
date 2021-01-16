package com.archive.service;


import com.archive.entity.ContextEntity;

public interface IContextService {

    ContextEntity add(ContextEntity contextEntity);
    ContextEntity findById(Integer contextEntityId);
}
