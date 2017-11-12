package com.nick.gvent.service.user;

import com.nick.gvent.entity.User;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    void save(User user);

    UserDetails loadUserByUsername(@NonNull String username);
}
