package com.nick.gvent.dao.role;

import com.nick.gvent.entity.Role;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Repository("roleCustom")
@Transactional
public class RoleCustom {

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public void persistUser(Role role){
        sessionFactory.getCurrentSession().persist(role);
//        LOGGER.info(messageSource.getMessage("log.new.user", new Object[] {user}, Locale.ENGLISH));
    }
}
