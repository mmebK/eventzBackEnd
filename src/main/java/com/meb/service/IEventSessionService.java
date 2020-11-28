package com.meb.service;

import com.meb.entity.EventSession;

import java.util.List;

public interface IEventSessionService {
    List<EventSession> findAllEventSessions();
    EventSession findEventSessionById(int id);
    void saveEventSession(EventSession eventSession);
    void deleteEventSessionById(Long id);
}



