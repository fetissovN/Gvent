package com.nick.gvent.service.event;


import com.nick.gvent.dao.event.EventDao;
import com.nick.gvent.dao.user.UserDao;
import com.nick.gvent.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventDao eventDao;

    @Autowired

    private UserDao userDao;


    @Override
    public Event save(Event event) {
        Event eventDB = eventDao.save(event);
        return eventDB;
    }

    @Override
    public Event delete(Event event) {
        return null;
    }

    @Override
    public Event update(Event event) {
        return null;
    }
}
