package com.andreypshenichnyj.iate.administrator.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "logs")
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int log_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_room_id")
    private Work_rooms work_room;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thread_script_id")
    private Thread_scripts thread_script;

    @Column(name = "launch_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date=new Date();

    @Column(name = "log_text")
    private String log_text;

    public Logs() {
    }

    public Logs(int log_id, Work_rooms work_room, Thread_scripts thread_script, Date date, String log_text) {
        this.log_id = log_id;
        this.work_room = work_room;
        this.thread_script = thread_script;
        this.date = date;
        this.log_text = log_text;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public Work_rooms getWork_room() {
        return work_room;
    }

    public void setWork_room(Work_rooms work_room) {
        this.work_room = work_room;
    }

    public Thread_scripts getThread_script() {
        return thread_script;
    }

    public void setThread_script(Thread_scripts thread_script) {
        this.thread_script = thread_script;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLog_text() {
        return log_text;
    }

    public void setLog_text(String log_text) {
        this.log_text = log_text;
    }
}
