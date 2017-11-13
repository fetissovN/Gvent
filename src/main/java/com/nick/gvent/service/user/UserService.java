package com.nick.gvent.service.user;

import com.nick.gvent.entity.User;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService {

    void saveNewUser(User user, Long role);

    UserDetails findByUsername(@NonNull String username);
}
