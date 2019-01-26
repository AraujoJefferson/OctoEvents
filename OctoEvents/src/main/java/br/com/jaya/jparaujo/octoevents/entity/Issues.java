package br.com.jaya.jparaujo.octoevents.entity;

import javax.persistence.*;

import static br.com.jaya.jparaujo.octoevents.constant.ApplicationConstants.*;

@Entity
@Table(name = TABLE_NAME_ISSUES)
public class Issues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = FIELD_NAME_ISSUES_ID)
    private Long id;
    @Column(name = FIELD_NAME_ISSUES_NUMBER)
    private Long number;
    @Column(name = FIELD_NAME_ISSUES_ACTION)
    private String action;
    @Column(name = FIELD_NAME_ISSUES_CREATED_AT)
    private String created_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
