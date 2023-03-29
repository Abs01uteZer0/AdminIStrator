package com.andreypshenichnyj.iate.administrator.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int student_id;

    @Column(name = "name")
    @NotEmpty(message = "Имя не может быть пустым!")
    @Size(min = 2, max = 25, message = "Имя должно содержать от 2 до 25 символов!")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Фамилия не может быть пустой!")
    @Size(min = 2, max = 35, message = "Фамилия должна содержать от 2 до 35 символов!")
    private String surname;

    @Column(name = "middle_name")
    @Max(value = 30, message = "Отчество не должно привышать 30 символов")
    private String middle_name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    @NotNull(message = "Пожалуйста, выберите группу!")
    private Groups group;

    @Column(name = "login")
    @NotEmpty(message = "Логин не должен быть пустым!")
    @Size(min = 2, max = 50, message = "Логин должен содержать от 2 до 50 символов!")
    private String login;

    @Column(name = "password")
    @NotEmpty(message = "Пароль не должен быть пустым!")
    private String password;

    @Column(name = "work_room")
    @NotEmpty(message = "Кабинет не должен быть пустым!")
    private String work_room;

    @Column(name = "access")
    private boolean access = true;

    public Students() {
    }

    public Students(int student_id, String name, String surname, String middle_name, String login, String password, String work_room, boolean access) {
        this.student_id = student_id;
        this.name = name;
        this.surname = surname;
        this.middle_name = middle_name;
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
        return "Students{" +
                "student_id=" + student_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", work_room='" + work_room + '\'' +
                ", access=" + access +
                '}';
    }
}
