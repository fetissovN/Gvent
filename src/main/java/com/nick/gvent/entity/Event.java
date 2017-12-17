package com.nick.gvent.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users_events")
public class Event {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToMany
    @JoinTable(name = "participants", joinColumns = {@JoinColumn(name = "event_id")}
            , inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> participants;


    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event_id")
    private List<Message> eventRelatedMessages;

    public Event() {
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public List<Message> getEventRelatedMessages() {
        return eventRelatedMessages;
    }

    public void setEventRelatedMessages(List<Message> eventRelatedMessages) {
        this.eventRelatedMessages = eventRelatedMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (!id.equals(event.id)) return false;
        return userId.equals(event.userId);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }
}
