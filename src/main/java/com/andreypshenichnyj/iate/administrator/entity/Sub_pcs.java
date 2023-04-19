package com.andreypshenichnyj.iate.administrator.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sub_pcs")
public class Sub_pcs {
    //Добавить имя

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_pc_id")
    private int sub_pc_id;

    @Column(name = "sub_pc_ip")
    private String sub_pc_ip;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_room_id")
    private Work_rooms work_room;

    public Sub_pcs() {
    }

    public Sub_pcs(int sub_pc_id, String sub_pc_ip, Work_rooms work_room) {
        this.sub_pc_id = sub_pc_id;
        this.sub_pc_ip = sub_pc_ip;
        this.work_room = work_room;
    }

    public int getSub_pc_id() {
        return sub_pc_id;
    }

    public void setSub_pc_id(int sub_pc_id) {
        this.sub_pc_id = sub_pc_id;
    }

    public String getSub_pc_ip() {
        return sub_pc_ip;
    }

    public void setSub_pc_ip(String sub_pc_ip) {
        this.sub_pc_ip = sub_pc_ip;
    }

    public Work_rooms getWork_room() {
        return work_room;
    }

    public void setWork_room(Work_rooms work_room) {
        this.work_room = work_room;
    }
}
