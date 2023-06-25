package com.andreypshenichnyj.iate.administrator.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "journal")
public class Journals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_id")
    private int journal_id;

    @Column(name = "script")
    private String script;

    @Column(name = "date_time")
    private Date date_time = new Date();

    @Column(name = "type")
    private String type;

    public Journals() {
    }

    public Journals(int journal_id, String script, Date date_time, String type) {
        this.journal_id = journal_id;
        this.script = script;
        this.date_time = date_time;
        this.type = type;
    }

    public int getJournal_id() {
        return journal_id;
    }

    public void setJournal_id(int journal_id) {
        this.journal_id = journal_id;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Date getDate_time() {
        return date_time;
    }

    public void setDate_time(Date date_time) {
        this.date_time = date_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
