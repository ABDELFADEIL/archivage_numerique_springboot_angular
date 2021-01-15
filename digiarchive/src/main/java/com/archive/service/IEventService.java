package com.archive.service;

import com.archive.entity.EventEntity;

public interface IEventService {

    EventEntity findById(Integer eventId);
    EventEntity add(int eventType);
}
