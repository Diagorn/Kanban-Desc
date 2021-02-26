package com.rosatom.kanban.domain;

import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.Set;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private TaskStatus status;

    private String title;

    private String description;

    private GregorianCalendar start;

    private GregorianCalendar end;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account owner;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Account> executer;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GregorianCalendar getStart() {
        return start;
    }

    public void setStart(GregorianCalendar start) {
        this.start = start;
    }

    public GregorianCalendar getEnd() {
        return end;
    }

    public void setEnd(GregorianCalendar end) {
        this.end = end;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Set<Account> getExecuter() {
        return executer;
    }

    public void setExecuter(Set<Account> executer) {
        this.executer = executer;
    }
}
