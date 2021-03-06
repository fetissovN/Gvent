package com.nick.gvent.dto;


import com.nick.gvent.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class EventDTO {


    private Long id;

    private Long userId;

//    private List<Long> participants;

    private String name;

    private String description;

    private String latitude;

    private String longitude;


}
