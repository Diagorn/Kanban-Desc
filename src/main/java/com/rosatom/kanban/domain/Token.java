package com.rosatom.kanban.domain;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @UniqueElements
    private String token;

    @OneToOne(mappedBy = "token", fetch = FetchType.EAGER)
    private Account account;
}
