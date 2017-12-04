package com.nick.gvent.converters;


import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.entity.Event;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpringConverterEventToEventDTO<T extends Event> implements Converter<Event, EventDTO> {

    @Override
    public EventDTO convert(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setUserId(event.getUserId().getId());
        eventDTO.setParticipants(event.getParticipants());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLatitude(String.valueOf(event.getLatitude()));
        eventDTO.setLongitude(String.valueOf(event.getLongitude()));
        return eventDTO;
    }
}
