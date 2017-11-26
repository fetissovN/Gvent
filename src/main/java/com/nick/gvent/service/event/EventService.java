package com.nick.gvent.service.event;


import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.entity.Event;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EventService {

    Event save(Event event);

    Event delete(Event event);

    Event update(Event event);

    List<EventDTO> getAll();

//    List<EventDTO> getAllPureTable();





}
