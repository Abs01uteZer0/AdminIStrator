package com.andreypshenichnyj.iate.administrator.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private int department_id;

    @Column(name = "department_name")
    @NotEmpty(message = "Название не должно быть пустым!")
    private String department_name;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "department")
    private List<Groups> groups;

    public Departments() {
    }

    public Departments(int department_id, String department_name) {
        this.department_id = department_id;
        this.department_name = department_name;
    }

    public void addGroupToDepartment(Groups group){
        if (this.groups == null){
            this.groups = new ArrayList<>();
        }
        this.groups.add(group);
        group.setDepartment(this);
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "department_id=" + department_id +
                ", department_name='" + department_name + '\'' +
                '}';
    }
}
