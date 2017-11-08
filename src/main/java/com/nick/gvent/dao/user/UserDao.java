package com.nick.gvent.dao.user;

import com.nick.gvent.dao.EventRepository;
import com.nick.gvent.entity.User;

import java.util.Optional;

public interface UserDao{

    Optional<User> getUserByUsername(String name);


}
