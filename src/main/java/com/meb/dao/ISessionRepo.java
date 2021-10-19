package com.meb.dao;


import com.meb.entity.EventSession;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface ISessionRepo {


    List<EventSession> findAllEventSessions();
    EventSession findEventSessionById(int id);
    void saveEventSession(EventSession eventSession);
    void deleteEventSessionById(Long id);
}
