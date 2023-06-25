package com.andreypshenichnyj.iate.administrator.service.scripts;

import com.andreypshenichnyj.iate.administrator.dao.journals.JournalsDAO;
import com.andreypshenichnyj.iate.administrator.entity.students.State;
import com.andreypshenichnyj.iate.administrator.entity.students.Students;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, String> buildScript(){
        Map<String, String> doc = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        if(studentsList!=null){
            String groupCode = studentsList.get(0).getGroup().getGroup_code();
            //Если студент находится в созданном состоянии
            if (studentsList.get(0).getState().equals(State.CREATED)){
                doc.put("TYPE", "Скрипт добавления");
                for (Students student: studentsList) {
                    stringBuilder.append("useradd -m -g ").append(groupCode).append(" -N ").append(student.getLogin()).append("\n");
                    stringBuilder.append("echo ").append(student.getLogin()).append(":").append(student.getPassword()).append("|chpasswd").append("\n");
                }
            }
            //Если студент находится в работе
            if (studentsList.get(0).getState().equals(State.IN_WORK)){
                doc.put("TYPE", "Скрипт удаления");
                for (Students student: studentsList) {
                    stringBuilder.append("userdel -r ").append(student.getLogin()).append("\n");
                }
            }
            doc.put("SCRIPT", stringBuilder.toString());
        }

        return doc;
    }
}
