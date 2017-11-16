package com.nick.gvent.dao.role;

import com.nick.gvent.entity.Role;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Repository("roleCustom")
@Transactional
public class RoleCustom {

    @Autowired
    final Logger LOGGER = LoggerFactory.getLogger(RoleCustom.class);

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public void persistRole(Role role){
        sessionFactory.getCurrentSession().save(role);
        LOGGER.info("New role {} was set to db.",role);
    }
}
