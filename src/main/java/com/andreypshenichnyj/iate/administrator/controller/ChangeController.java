package com.andreypshenichnyj.iate.administrator.controller;

import com.andreypshenichnyj.iate.administrator.service.MasterService;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ChangeController {

    @Autowired
    private MasterService masterService;

    @Autowired
    private StudentService studentService;

    private final boolean CHANGE_FLAG = true;

    @GetMapping(value = "/edit-student/{id}")
    public String addStudent(Model model, @PathVariable int id){
        model.addAttribute("change_flag", CHANGE_FLAG);
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("groups", studentService.getGroups());

        return "raw_pages/student";
    }

    @GetMapping(value = "/edit-group/{id}")
    public String addGroup(Model model, @PathVariable int id){
        model.addAttribute("change_flag", CHANGE_FLAG);
        model.addAttribute("group", studentService.getGroupById(id));
        model.addAttribute("departments", studentService.getDepartments());

        return "raw_pages/group";
    }

    @GetMapping(value = "/edit-department/{id}")
    public String addDepartment(Model model, @PathVariable int id){
        model.addAttribute("department", studentService.getDepartmentById(id));

        return "raw_pages/department";
    }

    @GetMapping(value = "/edit-master/{id}")
    public String addMaster(Model model, @PathVariable int id){
        model.addAttribute("change_flag", CHANGE_FLAG);
        model.addAttribute("master", masterService.getMasterById(id));
        model.addAttribute("roles", masterService.getRoles());

        return "raw_pages/master";
    }
}
