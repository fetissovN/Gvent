package com.nick.gvent.controllers;

import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.entity.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/api")
public class RestController {

    @Value("${host}")
    private String HOST;

    @RequestMapping(value = "/createEvent", method = RequestMethod.GET)
    @ResponseBody
    public String createNewEvent(@RequestBody EventDTO eventDTO){
        System.out.println(eventDTO.toString());
        return "success";
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

