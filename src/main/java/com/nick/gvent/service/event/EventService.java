package com.nick.gvent.service.event;


import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.dto.EventDTOWithUsersList;
import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EventService {

    Event save(String usernameAuth, Event event);

    void delete(Long id, String usernameAuth);

    Event update(Event event, String usernameAuth);

    Event getOne(Long id, String usernameAuth);

    List<EventDTO> getAll();

    List<EventDTO> getAllByUserId(Long id);

    List<EventDTO> getAllInBoundaries(Float lat1,Float lat2,Float lng1,Float lng2);

    EventDTO addParticipantToEvent(Long idEvent, String username);

    List<EventDTOWithUsersList> getAllInBoundariesWithParticipants(Float lat1, Float lat2, Float lng1, Float lng2, String usernameAuth);

    List<EventDTO> getAllTakePartInByUserId(Long id, String usernameAuth);

    boolean isUserCreatorOfEvent(Long eventId, String username);
//    List<EventDTO> getAllPureTable();





}
