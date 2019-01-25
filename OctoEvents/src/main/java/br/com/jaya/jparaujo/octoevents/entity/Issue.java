package br.com.jaya.jparaujo.octoevents.entity;

import javax.persistence.*;

@Entity
@Table(name = "EVENT")
public class Issue {

    /*private String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue")
    private Integer issue;
    @Column(name = "action")
    private String action;
    @Column(name = "created_at")
    private String created_at;

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

    public Integer getIssue() {
        return issue;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }
}
