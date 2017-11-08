package com.nick.gvent.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userId")
    private List<Role> authorities;

    @Column(name = "username")
    private String username;

    @Column(name = "age")
    private String age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    transient String passwordCheck;

    @Column(name = "accountNonExpired")
    private boolean accountNonExpired;

    @Column(name = "accountNonLocked")
    private boolean accountNonLocked;

    @Column(name = "credentialsNonExpired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userId")
    private List<Event> eventsList;

}
