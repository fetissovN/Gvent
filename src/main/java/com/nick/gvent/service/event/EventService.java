package com.nick.gvent.service.event;


import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.entity.Event;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EventService {

    Event save(Event event);

    void delete(Long id);

    Event update(Event event);

    Event getOne(Long id);

    List<EventDTO> getAll();

    List<EventDTO> getAllByUserId(Long id);

//    List<EventDTO> getAllPureTable();





}
