package com.andreypshenichnyj.iate.administrator.controller.management.main;

import com.andreypshenichnyj.iate.administrator.entity.students.Students;
import com.andreypshenichnyj.iate.administrator.service.MasterService;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import com.andreypshenichnyj.iate.administrator.service.scripts.ScriptBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("scriptBuilder", new ScriptBuilder());
        model.addAttribute("createdStudents", studentService.getAllCreatedStudents());
        model.addAttribute("inWorkStudents", studentService.getAllInWorkStudents());
        model.addAttribute("rToWorkStudents", studentService.getAllRToWorkStudents());
        model.addAttribute("rToDeleteStudents", studentService.getAllRToDeleteStudents());
        model.addAttribute("groups", studentService.getGroups());
        model.addAttribute("departments", studentService.getDepartments());
        model.addAttribute("roles", masterService.getRoles());
        model.addAttribute("teachers", masterService.getTeachers());
        model.addAttribute("admins", masterService.getAdmins());

        return "management_main_page";
    }

    @PostMapping (value = "/do-script")
    public String getScript(Model model,
                            @Validated @ModelAttribute("scriptBuilder") ScriptBuilder scriptBuilder){

        scriptBuilder.buildScript();


        return "main_page";
    }
}
