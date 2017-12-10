package com.nick.gvent.controllers;


import com.nick.gvent.converters.SpringConverterEventDTOToEvent;
import com.nick.gvent.converters.SpringConverterEventToEventDTO;
import com.nick.gvent.converters.SpringConverterUserToUserDTO;
import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.dto.UserDTO;
import com.nick.gvent.entity.User;
import com.nick.gvent.service.event.EventService;
import com.nick.gvent.service.user.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/user")
public class RestUserController {

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

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET
            ,produces = "application/json")
    public Map<String,UserDTO> getUserInfo(Authentication authentication){
        Map<String, UserDTO> map = new HashMap<>();
        if (authentication == null){
            map.put("auth",null);
            return map;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String nickname = userDetails.getUsername();
        if (nickname != null) {
            User user = userService.findUserByUsername(nickname);
            if (user != null){
                UserDTO userDTO = userToUserDTO.convert(user);
                map.put("user", userDTO);
            }else {
                map.put("error", null);
            }
            return map;
        }
        map.put("error", null);
        return map;
    }

    @RequestMapping(value = "/getUsersEvents/{json}", method = RequestMethod.GET,
            produces = "application/json")
    public Map<String, List<EventDTO>> getAllUserEvents(@PathVariable JSONObject json, Authentication authentication)
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

    @RequestMapping(value = "/getUsersEventsTakePart/{json}", method = RequestMethod.GET,
            produces = "application/json")
    public Map<String, List<EventDTO>> getAllUserEventsTakePart(@PathVariable JSONObject json, Authentication authentication)
            throws JSONException {
        Map<String, List<EventDTO>> map = new HashMap<>();
        if (authentication == null){
            map.put("auth",null);
            return map;
        }
        Integer id = (Integer) json.get("user");

        List<EventDTO> list = eventService.getAllTakePartInByUserId(id.longValue());
        map.put("events",list);
        return map;
    }

}
