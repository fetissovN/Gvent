package com.nick.gvent.service.user;

import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {

    UserDetails loadUserByUsername(@NonNull String username);
}
