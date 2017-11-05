package com.nick.gvent.entity;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event_id;

    @Column(name = "like_count")
    private int like;

    @Column(name = "message_date")
    private Date message_date;

}
