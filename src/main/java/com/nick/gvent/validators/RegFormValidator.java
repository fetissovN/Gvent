package com.nick.gvent.validators;


import com.nick.gvent.dto.UserDTO;
import com.nick.gvent.entity.Gender;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegFormValidator extends ValidatorSample implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;

        validNotBlank(errors, "username", "reg.username.empty", "Username must not be empty.");
        valid(errors,user.getUsername(),"username", "reg.username.tooLong", "Username must not more than 20 characters.",20);
        valid(errors,user.getFirstName(),"firstName", "reg.firstName.tooLong", "First name must not more than 30 characters.",30);
        valid(errors,user.getLastName(),"lastName", "reg.lastName.tooLong", "Last name must not more than 30 characters.",30);
//        while (true){
//            if (user.getGender() == null){
//                break;
//            }else if (user.getGender().equals("")){
//                break;
//            }else if (!(user.getGender().equals(Gender.MALE)) || !(user.getGender().equals(Gender.FEMALE))){
//                errors.rejectValue("gender","reg.gender","Wrong gender!");
//            }
//            break;
//        }
        while (true){
            if (user.getAge() == null){
                break;
            }
            if (!(user.getAge() > 100)){
                errors.rejectValue("age","reg.age.tooOld","You can't live any more!");
            }
            break;
        }
        validNotBlank(errors,"password", "reg.password.empty", "Password must not be empty.");
        if (!(user.getPassword()).equals(user
                .getPasswordCheck())) {
            errors.rejectValue("passwordCheck", "reg.passwordCheck.passwordDontMatch", "Passwords don't match.");
        }

        if( !EmailValidator.getInstance().isValid( user.getEmail() ) ){
            errors.rejectValue("email", "reg.email.notValid", "Email address is not valid.");
        }
    }
}
