package com.andreypshenichnyj.iate.administrator.entity;

import com.andreypshenichnyj.iate.administrator.entity.students.Students;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "work_rooms")
public class Work_rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_room_id")
    private int work_room_id;

    @Column(name = "name")
    private String name;

    @Column(name = "main_pc_ip")
    private String main_pc_ip;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "work_room")
    private List<Students> students;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "work_room")
    private List<Sub_pcs> sub_pcs;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "work_room")
    private List<Archives> archives;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "work_room")
    private List<Thread_scripts> thread_scripts;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "work_room")
    private List<Logs> logs;

    public Work_rooms() {
    }

    public Work_rooms(int work_room_id, String name, String main_pc_ip, List<Students> students, List<Sub_pcs> sub_pcs, List<Archives> archives, List<Thread_scripts> thread_scripts, List<Logs> logs) {
        this.work_room_id = work_room_id;
        this.name = name;
        this.main_pc_ip = main_pc_ip;
        this.students = students;
        this.sub_pcs = sub_pcs;
        this.archives = archives;
        this.thread_scripts = thread_scripts;
        this.logs = logs;
    }

    public int getWork_room_id() {
        return work_room_id;
    }

    public void setWork_room_id(int work_room_id) {
        this.work_room_id = work_room_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMain_pc_ip() {
        return main_pc_ip;
    }

    public void setMain_pc_ip(String main_pc_ip) {
        this.main_pc_ip = main_pc_ip;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    public List<Sub_pcs> getSub_pcs() {
        return sub_pcs;
    }

    public void setSub_pcs(List<Sub_pcs> sub_pcs) {
        this.sub_pcs = sub_pcs;
    }

    public List<Archives> getArchives() {
        return archives;
    }

    public void setArchives(List<Archives> archives) {
        this.archives = archives;
    }

    public List<Thread_scripts> getThread_scripts() {
        return thread_scripts;
    }

    public void setThread_scripts(List<Thread_scripts> thread_scripts) {
        this.thread_scripts = thread_scripts;
    }

    public List<Logs> getLogs() {
        return logs;
    }

    public void setLogs(List<Logs> logs) {
        this.logs = logs;
    }
}
