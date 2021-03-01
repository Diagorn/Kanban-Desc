package com.rosatom.kanban.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = -810758173520106459L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private TaskStatus status;

    private String title;

    private String description;

    private GregorianCalendar startDate;

    private GregorianCalendar endDate;

    private String color = "default";

    @ManyToOne(fetch = FetchType.EAGER)
    private Account owner;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account executer;

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

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Account getExecuter() {
        return executer;
    }

    public void setExecuter(Account executer) {
        this.executer = executer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
