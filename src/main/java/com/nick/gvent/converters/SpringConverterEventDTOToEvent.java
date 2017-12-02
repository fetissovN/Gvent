package com.nick.gvent.converters;


import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.dto.UserDTO;
import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpringConverterEventDTOToEvent<T extends Event> implements Converter<EventDTO, Event> {


    @Override
    public Event convert(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setUserId(null);
        event.setLatitude(Float.parseFloat(eventDTO.getLatitude()));
        event.setLongitude(Float.parseFloat(eventDTO.getLongitude()));
        return event;
    }
}
