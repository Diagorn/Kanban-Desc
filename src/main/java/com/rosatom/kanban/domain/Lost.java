package com.rosatom.kanban.domain;

import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.Set;

@Entity
public class Lost {
    @Id
    @GeneratedValue
    private Long id;

    private String content;
    private GregorianCalendar creationDate;
    private boolean isLost;

    @OneToMany(mappedBy = "lost", fetch = FetchType.EAGER)
    private Set<LostImage> lostImages;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;

    public Lost() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(GregorianCalendar creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }
}
