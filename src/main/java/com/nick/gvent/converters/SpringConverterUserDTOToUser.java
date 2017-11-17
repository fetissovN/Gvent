package com.nick.gvent.converters;


import com.nick.gvent.dto.UserDTO;
import com.nick.gvent.entity.User;
import com.nick.gvent.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SpringConverterUserDTOToUser<T extends User> implements Converter<UserDTO, User> {

    private User user = new User();

    public User convert(UserDTO userDTO){
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setAuthorities(userDTO.getAuthorities());
        user.setEmail(userDTO.getEmail());
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user.setAge(userDTO.getAge());
        user.setGender(userDTO.getGender());
        user.setEventsList(null);
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
