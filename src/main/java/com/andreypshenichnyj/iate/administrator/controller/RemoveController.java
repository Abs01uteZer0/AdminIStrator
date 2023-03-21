package com.andreypshenichnyj.iate.administrator.controller;


import com.andreypshenichnyj.iate.administrator.entity.Departments;
import com.andreypshenichnyj.iate.administrator.entity.Groups;
import com.andreypshenichnyj.iate.administrator.entity.Students;
import com.andreypshenichnyj.iate.administrator.service.MasterService;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RemoveController {

    @Autowired
    private MasterService masterService;

    @Autowired
    private StudentService studentService;

    //----------------------------------------------------------
    //Удаление доступа у студентов

    @PatchMapping(value = "/delete-student/{id}")
    public String deleteStudent(Model model, @PathVariable int id){
        Students student = studentService.getStudentById(id);
        studentService.deleteAccessOfStudent(student);
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
