package com.nick.gvent.dao.event;

import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.Role;
import org.hibernate.annotations.Fetch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EventDao extends JpaRepository<Event,Long> {

    @Override
    List<Event> findAll();

    List<Event> findByUserId(Long id);

    //    @Query(value = "SELECT * FROM Event", nativeQuery = true)
//    List<Event> selectAll();
}
