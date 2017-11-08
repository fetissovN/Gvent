package com.nick.gvent.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class Role implements GrantedAuthority {

    private Long id;

    private String name;

    private Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
