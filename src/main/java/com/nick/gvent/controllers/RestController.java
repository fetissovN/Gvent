package com.nick.gvent.controllers;

import com.nick.gvent.converters.SpringConverterEventDTOToEvent;
import com.nick.gvent.dao.event.EventDao;
import com.nick.gvent.dao.user.UserDao;
import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.User;
import com.nick.gvent.service.event.EventService;
import com.nick.gvent.service.user.UserService;
import com.nick.gvent.util.event.EventValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
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
    public @ResponseBody String createNewEvent(@RequestBody EventDTO eventDTO ,
                                               Principal principal,
                                               Authentication authentication){

        if (authentication == null){return "authFail";}
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User has authorities: " + userDetails.getAuthorities());
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

