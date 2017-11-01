package com.nick.voter.util.converters;


import com.nick.voter.dto.Theme;
import com.nick.voter.entity.Quiz;
import org.springframework.core.convert.converter.Converter;

/** Spring object converter from Theme DTO to Quiz
 * @autor Fetissov Mikalai
 * @version 1.0
 */

public class ThemeToQuiz implements Converter<Theme, Quiz>{
    @Override
    public Quiz convert(Theme theme) {
        Quiz quiz = new Quiz();
        quiz.setTheme(theme.getTheme());
        quiz.setVotes(0);
        return quiz;
    }
}
