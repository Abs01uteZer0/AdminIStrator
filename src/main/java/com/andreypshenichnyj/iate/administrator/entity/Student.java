package com.andreypshenichnyj.iate.administrator.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int student_id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "middle_name")
    private String middle_name;

    @Column(name = "group_id")
    private int group_id;

    @Column(name = "department_id")
    private int department_id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "work_room")
    private String work_room;

    @Column(name = "access")
    private boolean access;

    public Student() {
    }

    public Student(int student_id, String name, String surname, String middle_name, int group_id, int department_id, String login, String password, String work_room, boolean access) {
        this.student_id = student_id;
        this.name = name;
        this.surname = surname;
        this.middle_name = middle_name;
        this.group_id = group_id;
        this.department_id = department_id;
        this.login = login;
        this.password = password;
        this.work_room = work_room;
        this.access = access;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWork_room() {
        return work_room;
    }

    public void setWork_room(String work_room) {
        this.work_room = work_room;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", group_id=" + group_id +
                ", department_id=" + department_id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", work_room='" + work_room + '\'' +
                ", access=" + access +
                '}';
    }
}
