package com.nick.gvent.converters;


import com.nick.gvent.dto.UserDTO;
import com.nick.gvent.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SpringConverterUserToUserDTO<T extends UserDTO> implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setAuthorities(user.getAuthorities());
        userDTO.setEmail(user.getEmail());
        userDTO.setEnabled(user.isEnabled());
        userDTO.setAccountNonLocked(user.isAccountNonLocked());
        userDTO.setCredentialsNonExpired(user.isCredentialsNonExpired());
        userDTO.setAccountNonExpired(user.isAccountNonExpired());
        userDTO.setAge(user.getAge());
        userDTO.setGender(user.getGender());
//        userDTO.setEventsList(null);
        userDTO.setPassword(null);
        userDTO.setEvents(user.getEvents());
        return userDTO;
    }
}
