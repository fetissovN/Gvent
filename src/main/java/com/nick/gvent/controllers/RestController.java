package com.nick.gvent.controllers;

import com.nick.gvent.converters.SpringConverterEventDTOToEvent;
import com.nick.gvent.converters.SpringConverterEventToEventDTO;
import com.nick.gvent.dao.event.EventDao;
import com.nick.gvent.dao.user.UserDao;
import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.User;
import com.nick.gvent.entity.json.MarkerSortingJSON;
import com.nick.gvent.service.event.EventService;
import com.nick.gvent.service.user.UserService;
import com.nick.gvent.util.event.EventValidation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api")
public class RestController {

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
            Event eventDB = eventService.save(event);
            EventDTO eventDTODB = eventToEventDTO.convert(eventDB);
            if (eventDB != null){
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

    @RequestMapping(value = "/getUsersEvents/{json}", method = RequestMethod.GET,
                    produces = "application/json")
    public Map<String, List<EventDTO> > getAllUserEvents(@PathVariable JSONObject json, Authentication authentication)
            throws JSONException {
        Map<String, List<EventDTO>> map = new HashMap<>();
        if (authentication == null){
            map.put("auth",null);
            return map;
        }
        Integer id = (Integer) json.get("user");

        List<EventDTO> list = eventService.getAllByUserId(id.longValue());
        map.put("events",list);
        return map;
    }

    @RequestMapping(value = "/removeEvent/{json}", method = RequestMethod.DELETE,
            produces = "application/json")
    public Map<String, List<EventDTO>> removeEvent(@PathVariable JSONObject json, Authentication authentication)
            throws JSONException {
        Map<String, List<EventDTO>> map = new HashMap<>();
        if (authentication == null){
            map.put("auth",null);
            return map;
        }
        Integer id = (Integer) json.get("event");
        eventService.delete(id.longValue());
        map.put("deleted",new ArrayList<>());
        return map;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.POST,
                    produces = "application/json",
                    consumes = "application/json")
    @ResponseBody
    public Map<String, List<EventDTO>> getAll(@RequestBody MarkerSortingJSON json, Authentication authentication) throws JSONException {
        Map<String, List<EventDTO>> map = new HashMap<>();
        if (authentication == null){
            map.put("auth",null);
            return map;
        }
        double south = (Double) json.getBoundaries().get("south");
        double north = (Double) json.getBoundaries().get("north");
        double east = (Double) json.getBoundaries().get("east");
        double west = (Double) json.getBoundaries().get("west");
        List<EventDTO> list = eventService.getAllInBoundaries(
                (float) south,
                (float) north,
                (float) east,
                (float) west);
        map.put("events",list);
        return map;
    }
}

