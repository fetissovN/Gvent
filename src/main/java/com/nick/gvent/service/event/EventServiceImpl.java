package com.nick.gvent.service.event;


import com.nick.gvent.converters.SpringConverterEventToEventDTO;
import com.nick.gvent.dao.event.EventCustom;
import com.nick.gvent.dao.event.EventDao;
import com.nick.gvent.dao.user.UserDao;
import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private SpringConverterEventToEventDTO eventToEventDTO;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EventCustom eventCustom;

    @Override
    public Event save(Event event) {
        Event eventDB = eventDao.save(event);
        return eventDB;
    }

    @Override
    public void delete(Long id) {
        eventDao.delete(id);
    }

    @Override
    public Event getOne(Long id) {
        return eventDao.findOne(id);
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @Override
    public List<EventDTO> getAll() {
        List<Event> list = eventDao.findAll();
        return convertEventsToUser(list);
    }

    @Override
    public List<EventDTO> getAllByUserId(Long id) {
        User user = userDao.findOne(id);
        List<Event> list = eventDao.findByUserId(user);
        return convertEventsToUser(list);
    }

    @Override
    public List<EventDTO> getAllInBoundaries(Float lat1, Float lat2, Float lng1, Float lng2) {
        List<Event> list = eventDao.findByLatitudeBetweenAndLongitudeBetween(lat1,lat2,lng1,lng2);
        return convertEventsToUser(list);
    }

    private List<EventDTO> convertEventsToUser(List<Event> listEvents){
        List<EventDTO> listDTO = new Vector<>();
        for (Event e: listEvents){
            listDTO.add(eventToEventDTO.convert(e));
        }
        return listDTO;
    }
}
