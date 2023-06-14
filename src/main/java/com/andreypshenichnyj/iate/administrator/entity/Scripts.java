package com.andreypshenichnyj.iate.administrator.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "scripts")
public class Scripts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "script_id")
    private int script_id;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "script")
    private List<Archives> archives;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "script")
    private List<Thread_scripts> thread_scripts;

    @Column(name = "label")
    private String label;

    @Column(name = "script_code")
    private String script_code;

    @Column(name = "status")
    private boolean status = true;

    public Scripts() {
    }

    public Scripts(int script_id, List<Archives> archives, List<Thread_scripts> thread_scripts, String label, String script_code, boolean status) {
        this.script_id = script_id;
        this.archives = archives;
        this.thread_scripts = thread_scripts;
        this.label = label;
        this.script_code = script_code;
        this.status = status;
    }

    public int getScript_id() {
        return script_id;
    }

    public void setScript_id(int script_id) {
        this.script_id = script_id;
    }

    public List<Archives> getArchives() {
        return archives;
    }

    public void setArchives(List<Archives> archives) {
        this.archives = archives;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getScript_code() {
        return script_code;
    }

    public void setScript_code(String script_code) {
        this.script_code = script_code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Thread_scripts> getThread_scripts() {
        return thread_scripts;
    }

    public void setThread_scripts(List<Thread_scripts> thread_scripts) {
        this.thread_scripts = thread_scripts;
    }
}
