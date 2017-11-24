package com.nick.gvent.dto;

import com.nick.gvent.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@Builder
@ToString
public class EventDTO {


    private Long id;

    private User userId;

    private String name;

    private String description;

    private String latitude;

    private String longitude;

    public EventDTO(Long id, User userId, String name, String description, String latitude, String longitude) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public EventDTO() {
    }
}
