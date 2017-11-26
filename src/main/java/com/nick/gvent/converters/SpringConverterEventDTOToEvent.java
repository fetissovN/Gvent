package com.nick.gvent.converters;


import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.dto.UserDTO;
import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpringConverterEventDTOToEvent<T extends Event> implements Converter<EventDTO, Event> {

    private Event event = new Event();
    @Override
    public Event convert(EventDTO eventDTO) {
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setUserId(null);
        event.setLatitude(eventDTO.getLatitude());
        event.setLongitude(eventDTO.getLongitude());
        return event;
    }
}
