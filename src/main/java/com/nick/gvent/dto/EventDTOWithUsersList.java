package com.nick.gvent.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class EventDTOWithUsersList{

    private Long id;

    private Long userId;

    private List<Long> participants;

    private String name;

    private String description;

    private String latitude;

    private String longitude;
}
