package com.andreypshenichnyj.iate.administrator.controller.management.main;

import com.andreypshenichnyj.iate.administrator.service.MasterService;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/management/main")
public class ManagementMainController {

    @Autowired
    private MasterService masterService;

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "")
    public String getManagementMainPage(Model model){
        //Добавляем в модель необходимые параметры
        model.addAttribute("students", studentService.getAllActiveStudents());
        model.addAttribute("groups", studentService.getGroups());
        model.addAttribute("departments", studentService.getDepartments());
        model.addAttribute("roles", masterService.getRoles());
        model.addAttribute("teachers", masterService.getTeachers());
        model.addAttribute("admins", masterService.getAdmins());

        return "management_main_page";
    }
}
