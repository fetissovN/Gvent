package com.nick.voter.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/** Main POJO entity Quiz
 * @autor Fetissov Mikalai
 * @version 1.0
 */

@Entity
@Table(name = "quiz_all")
public class Quiz {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "theme")
    private String theme;

    @Column(name = "votes")
    private int votes;

    @Column(name = "started")
    @NotNull
    private int started;

    @Column(name = "closed")
    @NotNull
    private int closed;

    public Quiz() {
    }

    public int getStarted() {
        return started;
    }

    public void setStarted(int started) {
        this.started = started;
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
