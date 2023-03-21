package com.andreypshenichnyj.iate.administrator.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Roles implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int role_id;

    @Column(name = "role_name")
    private String role_name;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "role")
    private List<Masters> masters;

    public Roles() {
    }

    public Roles (int id){
        this.role_id = id;
    }

    public Roles(int role_id, String role_name) {
        this.role_id = role_id;
        this.role_name = role_name;
    }

    public void addMasterToRole(Masters master){
        if (this.masters == null){
            this.masters = new ArrayList<>();
        }
        this.masters.add(master);
        master.setRole(this);
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public List<Masters> getMasters() {
        return masters;
    }

    public void setMasters(List<Masters> masters) {
        this.masters = masters;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return getRole_name();
    }
}
