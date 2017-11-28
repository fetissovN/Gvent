package com.nick.gvent.dao.event;

import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.Role;
import com.nick.gvent.entity.User;
import org.hibernate.annotations.Fetch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EventDao extends JpaRepository<Event,Long> {
    
    List<Event> findByUserId(User id);


    //    @Query(value = "SELECT * FROM Event", nativeQuery = true)
//    List<Event> selectAll();
}
