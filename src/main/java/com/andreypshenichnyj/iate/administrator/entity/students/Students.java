package com.andreypshenichnyj.iate.administrator.entity.students;

import com.andreypshenichnyj.iate.administrator.entity.Groups;
import com.andreypshenichnyj.iate.administrator.entity.Work_rooms;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int student_id;

    @Column(name = "name")
    @Size(min = 2, max = 25, message = "Имя должно содержать от 2 до 25 символов!")
    private String name;

    @Column(name = "surname")
    @Size(min = 2, max = 35, message = "Фамилия должна содержать от 2 до 35 символов!")
    private String surname;

    @Column(name = "middle_name")
    @Size(min = 2, max = 35, message = "Отчество должно содержать от 2 до 30 символов!")
    private String middle_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Groups group;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    @NotEmpty(message = "Пароль не должен быть пустым!")
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_room_id")
    private Work_rooms work_room;

    @Column(name = "access")
    private boolean access = true;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state")
    private State state;

    public Students() {
    }

    public Students(int student_id, String name, String surname, String middle_name, String login, String password, Work_rooms work_room, boolean access, State state) {
        this.student_id = student_id;
        this.name = name;
        this.surname = surname;
        this.middle_name = middle_name;
        this.login = login;
        this.password = password;
        this.work_room = work_room;
        this.access = access;
        this.state = state;
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

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
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

    public Work_rooms getWork_room() {
        return work_room;
    }

    public void setWork_room(Work_rooms work_room) {
        this.work_room = work_room;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Students{" +
                "student_id=" + student_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", work_room='" + work_room + '\'' +
                ", access=" + access + '\'' +
                ", state=" + state +
                '}';
    }
}
