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

    List<EventDTO> getAllInBoundaries(Float lat1,Float lat2,Float lng1,Float lng2);
//    List<EventDTO> getAllPureTable();





}
