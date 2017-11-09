package com.nick.gvent.service.user;


import com.google.common.collect.ImmutableList;
import com.nick.gvent.dao.role.RoleDao;
import com.nick.gvent.dao.user.UserDao;
import com.nick.gvent.entity.Role;
import com.nick.gvent.entity.User;
import com.nick.gvent.service.user.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;


    @PostConstruct
    public void init(){
        Role role = new Role();
        role.setName("USER");

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
                    .authorities(ImmutableList.of(role))
                    .build());
        }
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userDao.getUserByUsername(username).orElse(null);

    }
}
