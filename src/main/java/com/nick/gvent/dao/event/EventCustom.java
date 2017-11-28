package com.nick.gvent.dao.event;


import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.Role;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository("eventCustom")
@Transactional
public class EventCustom{

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

//    public List<EventDTO> getAllPureTable(){
//        String sql = "SELECT * FROM users_events";
//        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
//        query.addEntity(EventDTO.class);
//        List results = query.list();
//        return results;
//    }
}
