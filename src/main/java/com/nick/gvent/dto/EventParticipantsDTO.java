package com.nick.gvent.dto;


import java.util.List;

public class EventParticipantsDTO extends EventDTO {

    private List<Long> patricipantsIds;

    private List<String> patricipantsNicknames;

    public EventParticipantsDTO() {
    }

    public List<Long> getPatricipantsIds() {
        return patricipantsIds;
    }

    public void setPatricipantsIds(List<Long> patricipantsIds) {
        this.patricipantsIds = patricipantsIds;
    }

    public List<String> getPatricipantsNicknames() {
        return patricipantsNicknames;
    }

    public void setPatricipantsNicknames(List<String> patricipantsNicknames) {
        this.patricipantsNicknames = patricipantsNicknames;
    }
}
