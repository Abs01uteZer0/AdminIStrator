package com.andreypshenichnyj.iate.administrator.controller.management.recovery;

import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/management/recovery")
public class RecoveryMainController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "")
    public String getManagementRecoveryPage(Model model){
        model.addAttribute("groups", studentService.getGroups());
        model.addAttribute("departments", studentService.getDepartments());
        model.addAttribute("students", studentService.getAllDeletedStudents());
        //Добавлять в модель всех студентов, у кого доступ false

        return "management_recovery_page";
    }
}
