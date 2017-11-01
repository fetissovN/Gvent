package com.nick.voter.controllers;

import com.nick.voter.dto.Theme;
import com.nick.voter.entity.Quiz;
import com.nick.voter.service.QuizServiceImpl;
import com.nick.voter.util.JsonParser;
import com.nick.voter.util.converters.ThemeToQuiz;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** api RestController class
 * @autor Fetissov Mikalai
 * @version 1.0
 */
@Controller
public class RestController {

    @Value("${host}")
    private String HOST;

    @Autowired
    private QuizServiceImpl quizService;


    /** Makes Quiz objects form from Theme object and saves to db
     * @param theme
     * @see RestController#makeTheme(Theme)()
     */
    @RequestMapping(value = "api/makeQuiz", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject makeTheme(@RequestBody Theme theme){
        Quiz quizNew = new ThemeToQuiz().convert(theme);
        Long id = quizService.saveNewQuiz(quizNew);
        return JsonParser.makeUrl(HOST + "/quiz/"+id);
    }

    /** Returns certain quiz by id like json object
     * @param quizId
     * @see RestController#getQuizURL(int)()
     */
    @RequestMapping(value = "api/get/{quizId}", method = RequestMethod.GET)
    @ResponseBody
    public Quiz getQuizURL(@PathVariable int quizId){
        Quiz quiz = quizService.getById((long) quizId);
        return quiz==null?null:quiz;
    }

    /** Starts quiz
     * @param quizId
     * @see RestController#closeQuiz(int)()
     */
    @RequestMapping(value = "api/start/{quizId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject startQuiz(@PathVariable int quizId){
        Quiz quiz = quizService.getById((long) quizId);
        if (quiz.getClosed()==1){
            return JsonParser.makeStatus("is closed");
        }else if (quiz.getStarted()==1){
            return JsonParser.makeStatus("already started");
        }
        quizService.startQuiz(quiz);
        return JsonParser.makeStatus("success");
    }

    /** Closes quiz
     * @param quizId
     * @see RestController#closeQuiz(int)()
     */
    @RequestMapping(value = "api/close/{quizId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject closeQuiz(@PathVariable int quizId){
        Quiz quiz = quizService.getById((long) quizId);
        if (quiz.getClosed()==1){
            return JsonParser.makeStatus("already closed");
        }else if (quiz.getStarted()==0){
            return JsonParser.makeStatus("not started");
        }
        quizService.closeQuiz(quiz);
        return JsonParser.makeStatus("success");
    }

    /** Makes +1 to vote counter of a certain quiz id
     * @param quizId
     * @see RestController#assertQuiz(int)()
     */
    @RequestMapping(value = "api/quiz/assert/{quizId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject assertQuiz(@PathVariable int quizId) {
        Quiz quiz = quizService.getById((long) quizId);
        if (quiz.getClosed()==0 && quiz.getStarted()==1){
            quizService.assertQuiz(quiz);
            return JsonParser.makeStatus("success");
        }else {
            return JsonParser.makeStatus("is closed or not started");
        }
    }

    /** Returns all quiz objects form db
     * @see RestController#listQuiz()
     */
    @RequestMapping(value = "api/get/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Quiz> listQuiz() {
        return quizService.getAllQuiz();
    }

}

