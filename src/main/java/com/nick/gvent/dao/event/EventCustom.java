package com.nick.gvent.dao.event;


import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.entity.Event;
import com.nick.gvent.entity.Role;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository("eventCustom")
@Transactional
public class EventCustom{

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

//    public List<Event> getAllPureTable(Float lat1,Float lat2,Float lng1,Float lng2){
//
//        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Event.class)
//                .add(Restrictions.lt("post_id", post));
//        criteria.addOrder(Order.desc("message_date"));
//
//        List<Event> results = criteria.list();
//        return results;
//    }
}
