package com.andreypshenichnyj.iate.administrator.controller.management.main;

import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/management/main")
public class ManagementRemoveController {

    @Autowired
    private StudentService studentService;

    @PatchMapping(value = "/delete-student/{id}")
    public String deleteStudent(Model model, @PathVariable int id){
        studentService.deleteAccessOfStudent(id);
        model.addAttribute("message", "Удаление доступа студента прошло успешно");

        return "success_page";
    }

    @PatchMapping(value = "/delete-group/{id}")
    public String deleteGroupOfStudents(Model model, @PathVariable int id){
        studentService.deleteAccessGroupOfStudents(id);
        model.addAttribute("message", "Удаление доступа e у группы студентов прошло успешно");

        return "success_page";
    }

    @PatchMapping(value = "/delete-department/{id}")
    public String deleteGroupOfDepartment(Model model, @PathVariable int id){
        studentService.deleteAccessDepartmentOfStudents(id);
        model.addAttribute("message", "Удаление доступа у отделения студентов прошло успешно");

        return "success_page";
    }
}
