package com.nick.gvent.service;


import com.google.common.collect.ImmutableList;
import com.nick.gvent.dao.EventRepository;
import com.nick.gvent.dao.user.UserDaoImpl;
import com.nick.gvent.entity.Role;
import com.nick.gvent.entity.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private EventRepository eventRepository;
    @PostConstruct
    public void init(){
        Role role = new Role();
        role.
        String auth = "User";
        if (!userDao.getUserByUsername("username").isPresent()){
            userDao.save(User.builder()
                    .username("user")
                    .password("password")
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .age("26")
                    .email("fetissov.n@gmail.com")
                    .enabled(true)
                    .authorities()
                    .build());


        }
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userDao.getUserByUsername(username).orElse(null);

    }
}
