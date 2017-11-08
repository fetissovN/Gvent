package com.nick.gvent.dao.user;


import com.nick.gvent.dao.EventRepository;
import com.nick.gvent.entity.User;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDaoImpl implements UserDao  {

    private final Logger LOGGER = Logger.getLogger(getClass());

//    @Autowired
//    private MessageSource messageSource;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    @Autowired
    public EventRepository eventRepository;


    public Optional<User> getUserByUsername(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE name = :name");
        query.setParameter("username", name);

        List userEntities = query.list();
        if (userEntities.size()==0){
            return null;
        }
        User user = (User) userEntities.get(0);
//        LOGGER.info(messageSource.getMessage("log.get.user.email", new Object[] {email}, Locale.ENGLISH));
//        User user = sessionFactory.getCurrentSession().get(User.class,email);
        return Optional.ofNullable(user);
    }

    public void save(@NonNull User user) {
            sessionFactory.getCurrentSession().save(user);
//            LOGGER.info(messageSource.getMessage("log.new.user", new Object[] {user}, Locale.ENGLISH));
    }
}
