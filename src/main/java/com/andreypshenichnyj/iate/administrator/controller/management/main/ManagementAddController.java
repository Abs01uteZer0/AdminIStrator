package com.andreypshenichnyj.iate.administrator.controller.management.main;

import com.andreypshenichnyj.iate.administrator.entity.Departments;
import com.andreypshenichnyj.iate.administrator.entity.Groups;
import com.andreypshenichnyj.iate.administrator.entity.masters.Masters;
import com.andreypshenichnyj.iate.administrator.entity.masters.Role;
import com.andreypshenichnyj.iate.administrator.entity.students.Students;
import com.andreypshenichnyj.iate.administrator.parser.StudentsParser;
import com.andreypshenichnyj.iate.administrator.service.MasterService;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/management/main")
public class ManagementAddController {
    @Autowired
    private MasterService masterService;

    @Autowired
    private StudentService studentService;

    private final boolean CHANGE_FLAG = false;

    @GetMapping(value = "/add-student")
    public String addStudent(Model model) {
        model.addAttribute("WorkRooms", studentService.getAllWorkRooms());
        model.addAttribute("change_flag", CHANGE_FLAG);
        model.addAttribute("student", new Students());
        model.addAttribute("groups", studentService.getGroups());

        return "raw_pages/student";
    }

    @GetMapping(value = "/add-group-of-students")
    public String addGroupOfStudents(Model model){
        model.addAttribute("WorkRooms", studentService.getAllWorkRooms());
        model.addAttribute("StParser", new StudentsParser());
        model.addAttribute("groups", studentService.getGroups());

        return "raw_pages/group_of_students";
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

    @GetMapping(value = "/add-teacher")
    public String addTeacher(Model model) {
        model.addAttribute("change_flag", CHANGE_FLAG);
        model.addAttribute("master", new Masters());
        model.addAttribute("roles", Role.TEACHER);

        return "raw_pages/master_teacher";
    }

    @GetMapping(value = "/add-admin")
    public String addAdmin(Model model) {
        model.addAttribute("change_flag", CHANGE_FLAG);
        model.addAttribute("master", new Masters());
        model.addAttribute("roles", Role.ADMIN);

        return "raw_pages/master_admin";
    }

    //Обработка полученных с форм результатов

    @PatchMapping(value = "/success-student")
    public String successStudent(@Validated @ModelAttribute("student") Students student,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("WorkRooms", studentService.getAllWorkRooms());
            model.addAttribute("change_flag", CHANGE_FLAG);
            model.addAttribute("groups", studentService.getGroups());
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
            model.addAttribute("change_flag", CHANGE_FLAG);
            model.addAttribute("departments", studentService.getDepartments());
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

    @PatchMapping(value = "/success-teacher")
    public String successTeacher(@Validated @ModelAttribute("master") Masters master,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("change_flag", CHANGE_FLAG);
            model.addAttribute("roles", Role.TEACHER);
            return "raw_pages/master_teacher";
        }
        masterService.saveMaster(master);
        model.addAttribute("message", "Добавление/изменение преподавателя/администратора прошло успешно");

        return "success_page";
    }

    @PatchMapping(value = "/success-admin")
    public String successAdmin(@Validated @ModelAttribute("master") Masters master,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("change_flag", CHANGE_FLAG);
            model.addAttribute("roles", Role.ADMIN);
            return "raw_pages/master_admin";
        }
        masterService.saveMaster(master);
        model.addAttribute("message", "Добавление/изменение преподавателя/администратора прошло успешно");

        return "success_page";
    }

    @PostMapping(value = "/success-student-group")
    public String successStudentGroup(@Validated @ModelAttribute("StParser") StudentsParser studentsParser,
                                      BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("WorkRooms", studentService.getAllWorkRooms());
            model.addAttribute("groups", studentService.getGroups());
            return "raw_pages/group_of_students";
        }
        List<Students> studentsList = studentsParser.parsing();
        studentService.addGroupOfStudents(studentsList);

        model.addAttribute("message", "Добавление группы студентов прошло успешно");

        return "success_page";
    }
}
