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

    private float latitude;

    private float longitude;

}
