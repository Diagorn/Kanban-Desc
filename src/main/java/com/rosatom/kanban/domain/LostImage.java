package com.rosatom.kanban.domain;

import javax.persistence.*;

@Entity
@Table(name = "lost_images")
public class LostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Lost lost;

    public LostImage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lost getLost() {
        return lost;
    }

    public void setLost(Lost lost) {
        this.lost = lost;
    }
}
