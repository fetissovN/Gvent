package com.nick.gvent.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")}
                , inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> authorities;

    @Column(name = "username", unique = true)
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
