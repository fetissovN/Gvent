package com.nick.gvent.dao;

import com.nick.gvent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<User, Long> {

    User findByUsername(User user);

}
