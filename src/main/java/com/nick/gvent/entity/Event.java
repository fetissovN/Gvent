package com.nick.gvent.entity;

import javax.persistence.*;

@Entity
@Table(name = "users_events")
public class Event {

    @Id
    @Column(name = "id_event")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvent;

    @Id
    @Column(name = "id_user")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;


    @Column(name = "latitude")
    private int latitude;

    @Column(name = "longitude")
    private int longitude;

}
