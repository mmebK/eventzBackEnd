package com.meb.service;

import com.meb.dao.ISessionRepo;
import com.meb.entity.EventSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventSessionService implements IEventSessionService {

    final ISessionRepo sessionRepo;

    public EventSessionService(ISessionRepo sessionRepo) {
        this.sessionRepo = sessionRepo;
    }

    @Override
    @Transactional
    public List<EventSession> findAllEventSessions() {
        return sessionRepo.findAllEventSessions();
    }

    @Override
    @Transactional
    public EventSession findEventSessionById(int id) {
        return sessionRepo.findEventSessionById(id);
    }

    @Override
    @Transactional
    public void saveEventSession(EventSession eventSession) {
        sessionRepo.saveEventSession(eventSession);
    }

    @Override
    @Transactional
    public void deleteEventSessionById(Long id) {

        sessionRepo.deleteEventSessionById(id);

    }
}
