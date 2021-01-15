package com.archive.service;


import com.archive.dao.EventRepository;
import com.archive.entity.EventEntity;
import com.archive.entity.EventStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventServiceImpl implements IEventService{

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventEntity findById(Integer eventId) {
        return eventRepository.findById(eventId).get();
    }

    @Override
    public EventEntity add(int eventType) {
        EventStatus eventStatus = EventStatus.fromValue(eventType);
        EventEntity eventEntity = new EventEntity( LocalDateTime.now(), eventStatus);
        return eventRepository.save(eventEntity);
    }
}
