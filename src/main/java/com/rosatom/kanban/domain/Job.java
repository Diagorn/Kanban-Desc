package com.rosatom.kanban.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class Job implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToMany
    @JoinTable(
            name = "job_articles",
            joinColumns = {@JoinColumn(name = "job_id")},
            inverseJoinColumns = {@JoinColumn(name = "article_id")}
    )
    private Set<Article> articles;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private Set<Account> accounts;

    public Job() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return id.equals(job.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
