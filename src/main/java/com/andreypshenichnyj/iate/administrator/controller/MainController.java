package com.andreypshenichnyj.iate.administrator.controller;


import com.andreypshenichnyj.iate.administrator.entity.Masters.Role;
import com.andreypshenichnyj.iate.administrator.service.MasterService;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

    @Autowired
    private MasterService masterService;

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/management")
    public String getManagementPage(Model model){
        //Добавляем в модель необходимые параметры
        model.addAttribute("students", studentService.getStudents());
        model.addAttribute("groups", studentService.getGroups());
        model.addAttribute("departments", studentService.getDepartments());
        model.addAttribute("roles", masterService.getRoles());
        model.addAttribute("masters", masterService.getMasters());

        return "management_page";
    }

    @GetMapping(value = "/main")
    public String getMainPage(){

        return "main_page";
    }

    @GetMapping(value = "/monitoring")
    public String getMonitoringPage(){

        return "monitoring_page";
    }

    @GetMapping(value = "/administration")
    public String getAdministrationPage(){

        return "administration_page";
    }
}
