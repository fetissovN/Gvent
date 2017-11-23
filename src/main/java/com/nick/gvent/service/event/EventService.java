package com.nick.gvent.service.event;


import com.nick.gvent.entity.Event;
import org.springframework.stereotype.Service;


public interface EventService {

    Event save(Event event);

    Event delete(Event event);

    Event update(Event event);


}
