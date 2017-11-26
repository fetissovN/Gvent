package com.nick.gvent.service.event;


import com.nick.gvent.converters.SpringConverterEventToEventDTO;
import com.nick.gvent.dao.event.EventCustom;
import com.nick.gvent.dao.event.EventDao;
import com.nick.gvent.dao.user.UserDao;
import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Event delete(Event event) {
        return null;
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @Override
    public List<EventDTO> getAll() {
        List<Event> list = eventDao.findAll();
        List<EventDTO> listDTO = new ArrayList<>();
        for (Event e: list){
            EventDTO eventDTO = eventToEventDTO.convert(e);
            listDTO.add(eventDTO);
        }
        return listDTO;
    }

//    public List<EventDTO> getAllPureTable(){
//        return eventCustom.getAllPureTable();
//
//    }



}
