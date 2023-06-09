package com.andreypshenichnyj.iate.administrator.parser;

import com.andreypshenichnyj.iate.administrator.entity.Groups;
import com.andreypshenichnyj.iate.administrator.entity.Work_rooms;
import com.andreypshenichnyj.iate.administrator.entity.students.Students;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentsParser {

    @jakarta.validation.constraints.Pattern(regexp = "([А-я]+:[А-я]+:[А-я]+\s?)+")
    private String data;

    private Groups group;

    private Work_rooms work_room;

    List<Students> studentsList;

    public StudentsParser(String data, Groups group, Work_rooms work_room) {
        this.data = data;
        this.group = group;
        this.work_room = work_room;
        this.studentsList = new ArrayList<>();
    }

    public StudentsParser() {
        this.studentsList = new ArrayList<>();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Work_rooms getWork_room() {
        return work_room;
    }

    public void setWork_room(Work_rooms work_room) {
        this.work_room = work_room;
    }

    public List<Students> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Students> studentsList) {
        this.studentsList = studentsList;
    }

    public List<Students> parsing(){
        Pattern p = Pattern.compile("[А-я]+:[А-я]+:[А-я]+");
        Matcher m = p.matcher(data);
        while (m.find()){
            String s = m.group();
            String [] array = s.split(":");
            Students student = new Students();
            student.setName(array[0]);
            student.setSurname(array[1]);
            student.setMiddle_name(array[2]);
            student.setGroup(group);
            student.setWork_room(work_room);
            group.addStudentToGroup(student);
            studentsList.add(student);
        }

        return studentsList;
    }
}
