package com.andreypshenichnyj.iate.administrator.entity.masters;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "masters")
public class Masters{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "master_id")
    private int master_id;

    @Column(name = "name")
    @Size(min = 2, max = 25, message = "Имя должно содержать от 2 до 25 символов!")
    private String name;

    @Column(name = "surname")
    @Size(min = 2, max = 35, message = "Фамилия должна содержать от 2 до 35 символов!")
    private String surname;

    @Column(name = "middle_name")
    @Size(min = 2, max = 30, message = "Отчество должно содержать от 2 до 30 символов!")
    private String middle_name;

    @Column(name = "login")
    @Size(min = 3, max = 50, message = "Логин должен содержать от 3 до 50 символов!")
    private String login;

    @Column(name = "password")
    @NotEmpty(message = "Пароль не должен быть пустым!")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    @NotNull
    private Status status = Status.ACTIVE;

    public Masters() {
    }

    public Masters(int master_id, String name, String surname, String middle_name, String login, String password, Role role, Status status) {
        this.master_id = master_id;
        this.name = name;
        this.surname = surname;
        this.middle_name = middle_name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public int getMaster_id() {
        return master_id;
    }

    public void setMaster_id(int master_id) {
        this.master_id = master_id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Masters{" +
                "master_id=" + master_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
