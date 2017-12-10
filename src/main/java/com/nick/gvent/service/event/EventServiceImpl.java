package com.nick.gvent.service.event;


import com.nick.gvent.converters.SpringConverterEventToEventDTO;
import com.nick.gvent.converters.SpringConverterEventToEventDTOWithParticipants;
import com.nick.gvent.dao.event.EventCustom;
import com.nick.gvent.dao.event.EventDao;
import com.nick.gvent.dao.user.UserDao;
import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.dto.EventDTOWithUsersList;
import com.nick.gvent.dto.UserDTO;
import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private SpringConverterEventToEventDTO eventToEventDTO;

    @Autowired
    private SpringConverterEventToEventDTOWithParticipants toEventDTOWithParticipants;
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
        return convertEventsToEventsDTO(list);
    }

    @Override
    public List<EventDTO> getAllByUserId(Long id) {
        User user = userDao.findOne(id);
        List<Event> list = eventDao.findByUserId(user);
        return convertEventsToEventsDTO(list);
    }

    @Override
    public EventDTO addParticipantToEvent(Long idEvent, String username){
        User user = userDao.findByUsername(username);
        Event event = eventDao.getOne(idEvent);
        if(checkParticipantValid(user,event)){

            List<User> users = event.getParticipants();
            users.add(user);
            event.setParticipants(users);
            Event eventDB = eventDao.save(event);
            return eventToEventDTO.convert(eventDB);
        }else {
            return null;
        }
    }

    @Override
    public List<EventDTO> getAllTakePartInByUserId(Long id) {
        User user = userDao.findOne(id);
        List<Event> list = eventDao.findByParticipants(user);
        return convertEventsToEventsDTO(list);
    }

    @Override
    public List<EventDTO> getAllInBoundaries(Float lat1, Float lat2, Float lng1, Float lng2) {
        List<Event> list = eventDao.findWithBoundaries(lat1,lat2,lng2,lng1);
        return convertEventsToEventsDTO(list);
    }

    @Override
    public List<EventDTOWithUsersList> getAllInBoundariesWithParticipants(Float lat1, Float lat2, Float lng1, Float lng2) {
        List<Event> list = eventDao.findWithBoundaries(lat1,lat2,lng2,lng1);
        return convertEventsToEventsDTOWithPatricipants(list);
    }

    private List<EventDTO> convertEventsToEventsDTO(List<Event> listEvents){
        List<EventDTO> listDTO = new Vector<>();
        for (Event e: listEvents){
            listDTO.add(eventToEventDTO.convert(e));
        }
        return listDTO;
    }

    private List<EventDTOWithUsersList> convertEventsToEventsDTOWithPatricipants(List<Event> listEvents){
        List<EventDTOWithUsersList> listDTO = new Vector<>();
        for (Event e: listEvents){
            listDTO.add(toEventDTOWithParticipants.convert(e));
        }
        return listDTO;
    }

    /**
     * Checks that user is not participant, and avoid duplicate
     * **/
    private boolean checkParticipantValid(User user, Event event){
        for (User u: event.getParticipants()){
            if (u.getId() == user.getId()){
                return false;
            }
        }
        if (user.getId() == event.getUserId().getId()){
            return false;
        }else {
            return true;
        }
    }

}
