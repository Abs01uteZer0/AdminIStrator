package com.andreypshenichnyj.iate.administrator.controller.management.recovery;

import com.andreypshenichnyj.iate.administrator.entity.Groups;
import com.andreypshenichnyj.iate.administrator.entity.students.State;
import com.andreypshenichnyj.iate.administrator.entity.students.Students;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller()
@RequestMapping(value = "/management/recovery")
public class RecoveryController {

    @Autowired
    private StudentService studentService;

    @PatchMapping(value = "/recovery-student/{id}")
    public String recoveryStudent(Model model, @PathVariable int id){
        List<Students> students = List.of(studentService.getStudentById(id));
        studentService.changeState(students);

        model.addAttribute("message", "Восстановление студента прошло успешно");

        return "success_page";
    }

    @PatchMapping(value = "/recovery-group/{id}")
    public String recoveryGroupOfStudents(Model model, @PathVariable int id){
        List<Students> deletedStudents = studentService.getGroupById(id).getStudents().stream().filter(st -> st.getState().equals(State.DELETED)).collect(Collectors.toList());
        studentService.changeState(deletedStudents);

        model.addAttribute("message", "Восстановление группы студентов прошло успешно");

        return "success_page";
    }

    @PatchMapping(value = "/recovery-department/{id}")
    public String recoveryDepartmentOfStudents(Model model, @PathVariable int id){
        List<Groups> list = studentService.getDepartmentById(id).getGroups();
        for (int i = 0; i<list.size(); i++) {
            studentService.changeState(list.get(i).getStudents().stream().filter(st -> st.getState().equals(State.DELETED)).collect(Collectors.toList()));
        }

        model.addAttribute("message", "Восстановление отделения студентов прошло успешно");

        return "success_page";
    }
}
