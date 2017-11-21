package com.nick.gvent.controllers;

import com.nick.gvent.converters.SpringConverterUserDTOToUser;
import com.nick.gvent.dto.UserDTO;
import com.nick.gvent.entity.User;
import com.nick.gvent.service.user.UserService;
import com.nick.gvent.validators.RegFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private SpringConverterUserDTOToUser userDTOToUser;

    @Autowired
    private RegFormValidator validator;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("newUser",new UserDTO());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute("newUser") UserDTO newUser,Model model, BindingResult result) {
        validator.validate(newUser, result);
        if (result.hasErrors()){
            return "registration";
        }
        User user = userDTOToUser.convert(newUser);
        if (userService.isEmailExists(user)){
            model.addAttribute("errorEmailExist", "Already exists.");
            return "registration";
        }
        if (userService.isNicknameExists(user)){
            model.addAttribute("errorNicknameExist", "Already exists.");
            return "registration";
        }

        userService.saveNewUser(user,1L);
        return "dashboard";
    }

}
