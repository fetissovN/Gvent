package com.nick.gvent.controllers;

import com.nick.gvent.converters.SpringConverterEventDTOToEvent;
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

    @RequestMapping(value = "/createEvent", method = RequestMethod.POST,
            consumes="application/json")
    public String createNewEvent(@RequestBody EventDTO eventDTO ,
                                               Principal principal,
                                               Authentication authentication){
        if (authentication == null){return "authFail";}
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails.getUsername() != null){
            User user = userService.findUserByUsername(userDetails.getUsername());
            EventValidation validation = EventValidation.getInstance();
            if (!validation.validate(eventDTO)){
                return "0";
            }
            Event event = eventDTOToEvent.convert(eventDTO);

            event.setEventRelatedMessages(null);
            event.setUserId(user);
            Event eventDB = eventService.save(event);
            if (eventDB != null){
                return "1";
            }else {
                return "0";
            }
        }else {
            return "0";
        }
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
    public Map<String, List<EventDTO>> getAll(@RequestBody MarkerSortingJSON json) throws JSONException {
        Map<String, List<EventDTO>> map = new HashMap<>();
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
//        if (json.("name").equals("all")){
//            List<EventDTO> list = eventService.getAll();
//            JSONObject object = new JSONObject();
//            object.put("events", list);
//            return map;
//        }

        return map;
    }

//    /** Makes Quiz objects form from UserDTO object and saves to db
//     * @param theme
//     * @see RestController#makeTheme(UserDTO)()
//     */
//    @RequestMapping(value = "api/makeQuiz", method = RequestMethod.POST)
//    @ResponseBody
//    public JSONObject makeTheme(@RequestBody UserDTO theme){
//        Quiz quizNew = new ThemeToQuiz().convert(theme);
//        Long id = quizService.saveNewQuiz(quizNew);
//        return JsonParser.makeUrl(HOST + "/quiz/"+id);
//    }
//
//    /** Returns certain quiz by id like json object
//     * @param quizId
//     * @see RestController#getQuizURL(int)()
//     */
//    @RequestMapping(value = "api/get/{quizId}", method = RequestMethod.GET)
//    @ResponseBody
//    public Quiz getQuizURL(@PathVariable int quizId){
//        Quiz quiz = quizService.getById((long) quizId);
//        return quiz==null?null:quiz;
//    }
//
//    /** Starts quiz
//     * @param quizId
//     * @see RestController#closeQuiz(int)()
//     */
//    @RequestMapping(value = "api/start/{quizId}", method = RequestMethod.GET)
//    @ResponseBody
//    public JSONObject startQuiz(@PathVariable int quizId){
//        Quiz quiz = quizService.getById((long) quizId);
//        if (quiz.getClosed()==1){
//            return JsonParser.makeStatus("is closed");
//        }else if (quiz.getStarted()==1){
//            return JsonParser.makeStatus("already started");
//        }
//        quizService.startQuiz(quiz);
//        return JsonParser.makeStatus("success");
//    }
//
//    /** Closes quiz
//     * @param quizId
//     * @see RestController#closeQuiz(int)()
//     */
//    @RequestMapping(value = "api/close/{quizId}", method = RequestMethod.GET)
//    @ResponseBody
//    public JSONObject closeQuiz(@PathVariable int quizId){
//        Quiz quiz = quizService.getById((long) quizId);
//        if (quiz.getClosed()==1){
//            return JsonParser.makeStatus("already closed");
//        }else if (quiz.getStarted()==0){
//            return JsonParser.makeStatus("not started");
//        }
//        quizService.closeQuiz(quiz);
//        return JsonParser.makeStatus("success");
//    }
//
//    /** Makes +1 to vote counter of a certain quiz id
//     * @param quizId
//     * @see RestController#assertQuiz(int)()
//     */
//    @RequestMapping(value = "api/quiz/assert/{quizId}", method = RequestMethod.GET)
//    @ResponseBody
//    public JSONObject assertQuiz(@PathVariable int quizId) {
//        Quiz quiz = quizService.getById((long) quizId);
//        if (quiz.getClosed()==0 && quiz.getStarted()==1){
//            quizService.assertQuiz(quiz);
//            return JsonParser.makeStatus("success");
//        }else {
//            return JsonParser.makeStatus("is closed or not started");
//        }
//    }
//
//    /** Returns all quiz objects form db
//     * @see RestController#listQuiz()
//     */
//    @RequestMapping(value = "api/get/all", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Quiz> listQuiz() {
//        return quizService.getAllQuiz();
//    }

}

