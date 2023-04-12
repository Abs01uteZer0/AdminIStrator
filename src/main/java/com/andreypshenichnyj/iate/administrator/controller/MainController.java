package com.andreypshenichnyj.iate.administrator.controller;


import com.andreypshenichnyj.iate.administrator.service.MasterService;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

    @Autowired
    private MasterService masterService;

    @Autowired
    private StudentService studentService;

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

    @GetMapping(value = "/role-checker")
    public String getRoleChecker(){

        return "Role_checker";
    }
}
