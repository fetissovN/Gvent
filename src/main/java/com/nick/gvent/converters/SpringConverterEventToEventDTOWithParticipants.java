package com.nick.gvent.converters;


import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.dto.EventDTOWithUsersList;
import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpringConverterEventToEventDTOWithParticipants<T extends Event> implements Converter<Event, EventDTOWithUsersList> {

    @Override
    public EventDTOWithUsersList convert(Event event) {
        EventDTOWithUsersList eventDTO = new EventDTOWithUsersList();
        eventDTO.setId(event.getId());
        eventDTO.setUserId(event.getUserId().getId());
        List<User> list = event.getParticipants();
        List<Long> longs = list.stream().filter(u->(u.getId()>=1L))
                    .map(user -> user.getId())
                    .collect(Collectors.toList());
        eventDTO.setParticipants(longs);
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLatitude(String.valueOf(event.getLatitude()));
        eventDTO.setLongitude(String.valueOf(event.getLongitude()));
        return eventDTO;
    }
}
