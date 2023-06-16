package com.andreypshenichnyj.iate.administrator.controller;


import com.andreypshenichnyj.iate.administrator.entity.Sub_pcs;
import com.andreypshenichnyj.iate.administrator.entity.Work_rooms;
import com.andreypshenichnyj.iate.administrator.service.AdministrationService;
import com.andreypshenichnyj.iate.administrator.service.MasterService;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class MainController {

    @Autowired
    private MasterService masterService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdministrationService administrationService;

    @GetMapping(value = "/main")
    public String getMainPage(){

        return "main_page";
    }

    @GetMapping(value = "monitoring/administration")
    public String getAdministrationPage(Model model){
        model.addAttribute("Work_rooms", studentService.getAllWorkRooms());

        return "administration_page";
    }

    @GetMapping(value = "/administration/info/{id}")
    public String addStudent(Model model, @PathVariable int id){
        Work_rooms wr = studentService.getWorkRoomById(id);
        List<Sub_pcs> list = wr.getSub_pcs();
//        Map<String, Boolean> map = administrationService.getIpWithInfo(list);
        Map<String, Boolean> fakeMap = administrationService.fakeFunction();
        model.addAttribute("computers", fakeMap);

        return "show_pcs_info";
    }
}
