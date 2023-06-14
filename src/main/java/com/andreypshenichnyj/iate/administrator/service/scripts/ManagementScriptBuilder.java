package com.andreypshenichnyj.iate.administrator.service.scripts;

import com.andreypshenichnyj.iate.administrator.entity.students.Students;

import java.util.ArrayList;
import java.util.List;

public class ManagementScriptBuilder {
    private List<Students> studentsList;

    public ManagementScriptBuilder() {
        studentsList = new ArrayList<>();
    }

    public List<Students> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Students> studentsList) {
        this.studentsList = studentsList;
    }

    public void buildScript(){
        StringBuilder script = new StringBuilder();
        for (Students student: studentsList) {
            script.append("useradd -m ").append(student.getLogin());
            script.append("\n");
            script.append("echo ").append(student.getLogin()).append(":").append(student.getPassword()).append("|chpasswd");
            script.append("\n");
        }
        System.out.println(script.toString());
    }
}
