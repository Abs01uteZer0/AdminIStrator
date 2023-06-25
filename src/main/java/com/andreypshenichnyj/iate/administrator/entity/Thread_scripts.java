package com.andreypshenichnyj.iate.administrator.entity;

import com.andreypshenichnyj.iate.administrator.entity.students.Students;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "thread_scripts")
public class Thread_scripts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thread_script_id")
    private int thread_script_id;

    @Column(name = "label")
    @Size(min = 3, max = 50, message = "Название скрипта должно содержать от 3 до 50 символов!")
    private String label;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "script_id")
    private Scripts script;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_room_id")
    private Work_rooms work_room;

    @Column(name = "time_minutes")
    private int time_minutes;

    @Column(name = "launch_time")
    private Date date = null;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "generated_script")
    @NotEmpty(message = "Код скрипта не должен быть пустым!")
    private String generated_script;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "thread_script")
    private List<Logs> logs;

    public Thread_scripts() {
    }

    public Thread_scripts(int thread_script_id, String label, Scripts script, Work_rooms work_room, int time_minutes, Date date, boolean status, String generated_script, List<Logs> logs) {
        this.thread_script_id = thread_script_id;
        this.label = label;
        this.script = script;
        this.work_room = work_room;
        this.time_minutes = time_minutes;
        this.date = date;
        this.status = status;
        this.generated_script = generated_script;
        this.logs = logs;
    }

    public int getThread_script_id() {
        return thread_script_id;
    }

    public void setThread_script_id(int thread_script_id) {
        this.thread_script_id = thread_script_id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Scripts getScript() {
        return script;
    }

    public void setScript(Scripts script) {
        this.script = script;
    }

    public Work_rooms getWork_room() {
        return work_room;
    }

    public void setWork_room(Work_rooms work_room) {
        this.work_room = work_room;
    }

    public int getTime_minutes() {
        return time_minutes;
    }

    public void setTime_minutes(int time_minutes) {
        this.time_minutes = time_minutes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getGenerated_script() {
        return generated_script;
    }

    public void setGenerated_script(String generated_script) {
        this.generated_script = generated_script;
    }

    public List<Logs> getLogs() {
        return logs;
    }

    public void setLogs(List<Logs> logs) {
        this.logs = logs;
    }
}
