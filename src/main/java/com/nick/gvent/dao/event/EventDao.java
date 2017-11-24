package com.nick.gvent.dao.event;

import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventDao extends JpaRepository<Event,Long> {
}
