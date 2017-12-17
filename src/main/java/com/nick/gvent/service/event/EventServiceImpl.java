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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventServiceImpl implements EventService{

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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
    public Event save(String usernameAuth, Event event) {
        Event eventDB = eventDao.save(event);
        if (eventDB != null){
            LOGGER.info("New event saved {} by user {}", eventDB, usernameAuth);
        }else {
            LOGGER.info("New event was not saved by user {}", usernameAuth);
        }
        return eventDB;
    }

    @Override
    public void delete( Long id, String usernameAuth) {
        eventDao.delete(id);
        LOGGER.info("Event {} was deleted by user {}", id, usernameAuth);
    }

    @Override
    public Event getOne(Long id, String usernameAuth) {
        Event event = eventDao.findOne(id);
        if (event != null){
            LOGGER.info("Event with id {} loaded by user {}", id, usernameAuth);
        }else {
            LOGGER.info("Event with id {} failed to load by user {}", id, usernameAuth);
        }
        return event;
    }

    @Override
    public Event update(Event event, String usernameAuth) {
        Event eventUpdated = eventDao.save(event);
        if (eventUpdated != null){
            LOGGER.info("Event {} updated to {} by user {}", event, eventUpdated, usernameAuth);
        }else {
            LOGGER.info("Event {} failed to update by user {}", event, usernameAuth);
        }
        return eventUpdated;
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
        if (id != null && list != null){
            LOGGER.info("Events {} loaded by user {}", list, user.getUsername());
        }else {
            LOGGER.info("Event failed to load by userId {}", id);
        }
        List<EventDTO> listDTOs = convertEventsToEventsDTO(list);
        if (listDTOs != null){
            LOGGER.info("Events converted by user {}", user.getUsername());
        }else {
            LOGGER.info("Events convertation failed by userName {}", user.getUsername());
        }
        return listDTOs;
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
            if (eventDB != null){
                LOGGER.info("Participant added to event {} with userName {}", event, user.getUsername());
            }
            return eventToEventDTO.convert(eventDB);
        }else {
            LOGGER.info("Participant {} failed to add to event {}", user.getUsername(), event);
            return null;
        }
    }

    //TO DO throw this to another rest controller
    @Override
    public List<EventDTO> getAllTakePartInByUserId(Long id, String usernameAuth) {
        User user = userDao.findOne(id);
        List<Event> list = eventDao.findByParticipants(user);
        List<EventDTO> eventDTOs = convertEventsToEventsDTO(list);
        if (eventDTOs != null){
            LOGGER.info("Get all events user takes part {} by user {}", eventDTOs, user.getUsername());
        }
        return eventDTOs;
    }

    @Override
    public List<EventDTO> getAllInBoundaries(Float lat1, Float lat2, Float lng1, Float lng2) {
        List<Event> list = eventDao.findWithBoundaries(lat1,lat2,lng2,lng1);
        List<EventDTO> eventDTOs = convertEventsToEventsDTO(list);
        return eventDTOs;
    }

    @Override
    public List<EventDTOWithUsersList> getAllInBoundariesWithParticipants(Float lat1, Float lat2, Float lng1, Float lng2, String usernameAuth) {
        List<Event> list = eventDao.findWithBoundaries(lat1,lat2,lng2,lng1);
        List<EventDTOWithUsersList> eventDTOs = convertEventsToEventsDTOWithPatricipants(list);
        if (eventDTOs != null){
            LOGGER.info("Get all events with boundaries {} by user {}", eventDTOs, usernameAuth);
        }
        return eventDTOs;
    }

    @Override
    public boolean isUserCreatorOfEvent(Long eventId, String username) {
        Event event = eventDao.findOne(eventId);
        return event.getUserId().getUsername().equals(username);
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
