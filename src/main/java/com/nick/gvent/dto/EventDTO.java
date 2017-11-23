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

    private float latitute;

    private float longitute;

    public EventDTO(Long id, User userId, String name, String description, float latitute, float longitute) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.latitute = latitute;
        this.longitute = longitute;
    }

    public EventDTO() {
    }
}
