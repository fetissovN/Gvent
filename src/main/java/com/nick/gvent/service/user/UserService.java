package com.nick.gvent.service.user;

import com.nick.gvent.entity.User;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {

    void save(User user);

    UserDetails loadUserByUsername(@NonNull String username);
}
