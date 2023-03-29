package com.andreypshenichnyj.iate.administrator.controller;

import com.andreypshenichnyj.iate.administrator.entity.Departments;
import com.andreypshenichnyj.iate.administrator.entity.Groups;
import com.andreypshenichnyj.iate.administrator.entity.Masters.Masters;
import com.andreypshenichnyj.iate.administrator.entity.Masters.Role;
import com.andreypshenichnyj.iate.administrator.entity.Students;
import com.andreypshenichnyj.iate.administrator.service.MasterService;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
public class AddController {
    @Autowired
    private MasterService masterService;

    @Autowired
    private StudentService studentService;

    private final boolean CHANGE_FLAG = false;

    @GetMapping(value = "/add-student")
    public String addStudent(Model model) {
        model.addAttribute("change_flag", CHANGE_FLAG);
        model.addAttribute("student", new Students());
        model.addAttribute("groups", studentService.getGroups());

        return "raw_pages/student";
    }

    @GetMapping(value = "/add-group")
    public String addGroup(Model model) {
        model.addAttribute("change_flag", CHANGE_FLAG);
        model.addAttribute("group", new Groups());
        model.addAttribute("departments", studentService.getDepartments());

        return "raw_pages/group";
    }

    @GetMapping(value = "/add-department")
    public String addDepartment(Model model) {
        model.addAttribute("department", new Departments());

        return "raw_pages/department";
    }

    @GetMapping(value = "/add-master")
    public String addMaster(Model model) {
        model.addAttribute("change_flag", CHANGE_FLAG);
        model.addAttribute("master", new Masters());
        model.addAttribute("roles", masterService.getRoles());

        return "raw_pages/master";
    }

    //Обработка полученных с форм результатов

    @PatchMapping(value = "/success-student")
    public String successStudent(@Validated @ModelAttribute("student") Students student,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            return "raw_pages/student";
        }
        studentService.saveStudent(student);
        model.addAttribute("message", "Добавление/изменение студента прошло успешно!");
        return "success_page";

    }

    @PatchMapping(value = "/success-group")
    public String successGroup(@Validated @ModelAttribute("group") Groups group,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "raw_pages/group";
        }
        studentService.saveGroup(group);
        model.addAttribute("message", "Добавление/изменение группы прошло успешно!");

        return "success_page";
    }

    @PatchMapping(value = "/success-department")
    public String successDepartment(@Validated @ModelAttribute("department") Departments department,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "raw_pages/department";
        }
        studentService.saveDepartment(department);
        model.addAttribute("message", "Добавление/изменение отделения прошло успешно");

        return "success_page";
    }

    @PatchMapping(value = "/success-master")
    public String successMaster(@Validated @ModelAttribute("master") Masters master,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "raw_pages/master";
        }
        masterService.saveMaster(master);
        model.addAttribute("message", "Добавление/изменение преподавателя/администратора прошло успешно");

        return "success_page";
    }
}
