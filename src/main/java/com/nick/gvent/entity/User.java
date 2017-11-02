package com.nick.gvent.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Nick on 11/2/2017.
 */
public class User {
    @Entity
    @Table(name = "users")
    public class Quiz {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "nickname")
        private String nickname;

        @Column(name = "age")
        private String age;

        @Column(name = "gender")
        private String gender;

        @Column(name = "email")
        private String email;

        @Column(name = "password")
        private String password;

//        @Column(name = "latitude")
//        private int latitude;
//
//        @Column(name = "longitude")
//        private int longitude;

        @Column(name = "banned")
        @NotNull
        private int banned;




    }

}
