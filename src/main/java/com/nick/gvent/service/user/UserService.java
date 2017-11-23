package com.nick.gvent.service.user;

import com.nick.gvent.entity.User;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {

    void saveNewUser(User user, Long role);

    boolean isEmailExists(User user);

    boolean isNicknameExists(User user);

    User findUserByUsername(String username);

    UserDetails findByUsername(@NonNull String username);
}
