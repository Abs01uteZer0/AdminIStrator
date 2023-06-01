package com.andreypshenichnyj.iate.administrator.entity;

import com.andreypshenichnyj.iate.administrator.entity.students.Students;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private int group_id;

    @Column(name = "group_name")
    @Pattern(regexp = "[А-Я]+_[Б|С|М]\\d{2}"
            , message = "Название группы должно соответствовать следующему шаблону: [А-Я]+_[Б|С|М]\\d{2}")
    private String group_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Departments department;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "group")
    private List<Students> students;

    public Groups() {
    }

    public Groups(int group_id, String group_name) {
        this.group_id = group_id;
        this.group_name = group_name;
    }

    public void addStudentToGroup(Students student){
        if (this.students == null){
            this.students = new ArrayList<>();
        }
        this.students.add(student);
        student.setGroup(this);
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "group_id=" + group_id +
                ", group_name='" + group_name + '\'' +
                '}';
    }
}
