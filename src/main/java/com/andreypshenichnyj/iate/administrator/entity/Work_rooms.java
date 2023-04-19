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
    private String main_pc_id;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "work_room")
    private List<Students> students;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "work_room")
    private List<Sub_pcs> sub_pcs;

    public Work_rooms() {
    }

    public Work_rooms(int work_room_id, String name, String main_pc_id) {
        this.work_room_id = work_room_id;
        this.name = name;
        this.main_pc_id = main_pc_id;
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

    public String getMain_pc_id() {
        return main_pc_id;
    }

    public void setMain_pc_id(String main_pc_id) {
        this.main_pc_id = main_pc_id;
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
}
