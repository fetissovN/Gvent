package com.nick.gvent.converters;


import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.entity.Event;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpringConverterEventToEventDTO<T extends Event> implements Converter<Event, EventDTO> {

    private EventDTO eventDTO = new EventDTO();
    @Override
    public EventDTO convert(Event event) {
        eventDTO.setId(event.getId());
        eventDTO.setUserId(event.getUserId().getId());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLatitude(eventDTO.getLatitude());
        eventDTO.setLongitude(eventDTO.getLongitude());
        return eventDTO;
    }
}
