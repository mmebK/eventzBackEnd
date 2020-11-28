package com.meb.dao;

import com.meb.entity.EventSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class SessionRepoImpl implements ISessionRepo {

    private final EntityManager entityManager;

    @Autowired
    public SessionRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<EventSession> findAllEventSessions() {
        return null;
    }

    @Override
    public EventSession findEventSessionById(int id) {
        return entityManager.find(EventSession.class, id);

    }

    @Override
    public void saveEventSession(EventSession eventSession) {
        entityManager.merge(eventSession);
    }

    @Override
    public void deleteEventSessionById(Long id) {
        EventSession eventSession = entityManager.find(EventSession.class, id);
        System.out.println(eventSession);
        entityManager.remove(eventSession);
    }


}
