package com.nick.gvent.service.user;


import com.google.common.collect.ImmutableList;
import com.nick.gvent.dao.role.RoleDao;
import com.nick.gvent.dao.user.UserDao;
import com.nick.gvent.entity.Role;
import com.nick.gvent.entity.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void saveNewUser(User user, Long role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findOne(role));
        user.setAuthorities(roles);
        user.setAge(user.getAge());
        user.setUsername(user.getUsername());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setEmail(user.getEmail());
        user.setGender(user.getGender());
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

//    @PostConstruct
//    @Transactional
//    public void init() throws InterruptedException {
////        if (roleDao.findAll().size() == 0){
////            roleDao.save(Role.builder()
////                    .id(1L)
////                    .name("USER")
////                    .build());
////        }
////        Thread.sleep(1000);
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleDao.findOne(1L));
//
//        Object o = SecurityContextHolder.getContext();
//        System.out.println(0);
//
////        if (userDao.findByUsername("username") != null){
//            userDao.save(User.builder()
//                    .username("user")
//                    .password(passwordEncoder.encode("qwerty"))
//                    .accountNonExpired(true)
//                    .accountNonLocked(true)
//                    .credentialsNonExpired(true)
//                    .age("26")
//                    .gender("male")
//                    .email("fetissov.n@gmail.com")
//                    .enabled(true)
//                    .authorities(roles)
//                    .build());
////        }
//    }

}
