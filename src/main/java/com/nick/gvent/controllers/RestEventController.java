package com.nick.gvent.controllers;

import com.nick.gvent.converters.SpringConverterEventDTOToEvent;
import com.nick.gvent.converters.SpringConverterEventToEventDTO;
import com.nick.gvent.converters.SpringConverterUserToUserDTO;
import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.dto.EventDTOWithUsersList;
import com.nick.gvent.dto.EventParticipantsDTO;
import com.nick.gvent.dto.UserDTO;
import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.User;
import com.nick.gvent.entity.json.MarkerSortingJSON;
import com.nick.gvent.service.event.EventService;
import com.nick.gvent.service.user.UserService;
import com.nick.gvent.util.event.EventValidation;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api/event")
public class RestEventController {

    @Value("${host}")
    private String HOST;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private SpringConverterEventDTOToEvent eventDTOToEvent;

    @Autowired
    private SpringConverterEventToEventDTO eventToEventDTO;

    @Autowired
    private SpringConverterUserToUserDTO userToUserDTO;

    @RequestMapping(value = "/createEvent", method = RequestMethod.POST,
            consumes="application/json")
    public Map<String, EventDTO>  createNewEvent(@RequestBody EventDTO eventDTO ,
                                               Principal principal,
                                               Authentication authentication){
        Map<String, EventDTO> map = new HashMap<>();
        if (authentication == null){
            map.put("auth",null);
            return map;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails.getUsername() != null){
            EventValidation validation = EventValidation.getInstance();
            if (!validation.validate(eventDTO)){
                map.put("invalid",null);
                return map;
            }
            Event event = eventDTOToEvent.convert(eventDTO);
            User user = userService.findUserByUsername(userDetails.getUsername());

            event.setEventRelatedMessages(null);
            event.setUserId(user);
            Event eventDB = eventService.save(user.getUsername(),event);
            EventDTO eventDTODB = eventToEventDTO.convert(eventDB);
            if (eventDB != null){
                eventDB.setParticipants(null);
                map.put("event", eventDTODB);
                return map;
            }
        }else {
            map.put("error", null);
            return map;
        }
        return map;
    }

    @RequestMapping(value = "/getAll/absolute", method = RequestMethod.GET,
                    produces = "application/json")
    public Map<String, List<EventDTO> > getAllEventsAbs(Authentication authentication){
        Map<String, List<EventDTO>> map = new HashMap<>();
        if (authentication == null){
            map.put("auth",null);
            return map;
        }
        List<EventDTO> list = eventService.getAll();
        map.put("events",list);
        return map;
    }

    @RequestMapping(value = "/addParticipant/{id}", method = RequestMethod.GET,
            produces = "application/json")
    public Map<String, EventDTO> addParticipant(@PathVariable Long id, Authentication authentication)
            throws JSONException {
        Map<String, EventDTO> map = new HashMap<>();
        if (authentication == null){
            map.put("auth",null);
            return map;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        if (username != null) {
            EventDTO eventDTO = eventService.addParticipantToEvent(id,username);
//            List<User> users = eventDTO.getParticipants();
//            eventDTO.setParticipants(null);
//            List<Long> longs = users.stream()
//                    .filter(u->(u.getId()>=1L))
//                    .map(user -> user.getId())
//                    .collect(Collectors.toList());
//            List<String> usernames = users.stream()
//                    .filter(u->(u.getId()>=1L))
//                    .map(user -> user.getUsername())
//                    .collect(Collectors.toList());

//            EventParticipantsDTO eventPatricipantsDTO = new EventParticipantsDTO();
//            eventPatricipantsDTO.setPatricipantsIds(longs);
//            eventPatricipantsDTO.setPatricipantsNicknames(usernames);
            if (eventDTO == null){
                map.put("error", null);
            }else {
                map.put("event", eventDTO);
            }
            return map;
        }
        return map;
    }
    @RequestMapping(value = "/removeEvent/{id}", method = RequestMethod.DELETE,
            produces = "application/json")
    public Map<String, List<EventDTO>> removeEvent(@PathVariable("id") Long id, Authentication authentication)
            throws JSONException {
        Map<String, List<EventDTO>> map = new HashMap<>();
        if (authentication == null){
            map.put("auth",null);
            return map;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (eventService.isUserCreatorOfEvent(id,userDetails.getUsername())){
            try{
                eventService.delete(id, userDetails.getUsername());
            }catch (EmptyResultDataAccessException e){
                map.put("fail",null);
            }
        }
        map.put("deleted",null);
        return map;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.POST,
                    produces = "application/json",
                    consumes = "application/json")
    @ResponseBody
    public Map<String, List<EventDTOWithUsersList>> getAll(@RequestBody MarkerSortingJSON json, Authentication authentication) throws JSONException {
        Map<String, List<EventDTOWithUsersList>> map = new HashMap<>();
        if (authentication == null){
            map.put("auth",null);
            return map;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        double south = (Double) json.getBoundaries().get("south");
        double north = (Double) json.getBoundaries().get("north");
        double east = (Double) json.getBoundaries().get("east");
        double west = (Double) json.getBoundaries().get("west");
        List<EventDTOWithUsersList> list = eventService.getAllInBoundariesWithParticipants((float) south,
                (float) north, (float) east, (float) west, userDetails.getUsername());
        map.put("events",list);
        return map;
    }
}

