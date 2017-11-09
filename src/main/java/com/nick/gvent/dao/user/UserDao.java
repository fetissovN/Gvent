package com.nick.gvent.dao.user;

import com.nick.gvent.entity.User;
import lombok.NonNull;

import java.util.Optional;

public interface UserDao{

    Optional<User> getUserByUsername(String name);

    void save(@NonNull User user);

    }
