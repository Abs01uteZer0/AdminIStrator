package com.andreypshenichnyj.iate.administrator.controller.management.recovery;

import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping(value = "/management/recovery")
public class RecoveryController {

    @Autowired
    private StudentService studentService;

    @PatchMapping(value = "/recovery-student/{id}")
    public String recoveryStudent(Model model, @PathVariable int id){
        studentService.recoveryAccessOfStudent(id);

        model.addAttribute("message", "Восстановление студента прошло успешно");

        return "success_page";
    }

    @PatchMapping(value = "/recovery-group/{id}")
    public String recoveryGroupOfStudents(Model model, @PathVariable int id){
        studentService.recoveryAccessGroupOfStudents(id);

        model.addAttribute("message", "Восстановление студента прошло успешно");

        return "success_page";
    }

    @PatchMapping(value = "/recovery-department/{id}")
    public String recoveryDepartmentOfStudents(Model model, @PathVariable int id){
        studentService.recoveryAccessDepartmentOfStudents(id);

        model.addAttribute("message", "Восстановление студента прошло успешно");

        return "success_page";
    }
}
