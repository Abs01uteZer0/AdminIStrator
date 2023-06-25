package com.andreypshenichnyj.iate.administrator.controller.management.main;

import com.andreypshenichnyj.iate.administrator.entity.Journals;
import com.andreypshenichnyj.iate.administrator.entity.students.State;
import com.andreypshenichnyj.iate.administrator.entity.students.Students;
import com.andreypshenichnyj.iate.administrator.service.MasterService;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import com.andreypshenichnyj.iate.administrator.service.scripts.ManagementScriptBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        model.addAttribute("managementScriptBuilder", new ManagementScriptBuilder());
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
                            @Validated @ModelAttribute("scriptBuilder") ManagementScriptBuilder managementScriptBuilder){
        Journals journal = new Journals();
        Map<String, String> doc = managementScriptBuilder.buildScript();

        journal.setScript(doc.get("SCRIPT"));
        journal.setType(doc.get("TYPE"));
        studentService.addJournal(journal);
        studentService.changeState(managementScriptBuilder.getStudentsList());
        model.addAttribute("journal", journal);

        return "raw_pages/journal";
    }

    @PostMapping (value = "/change-state")
    public String changeState(Model model,
                            @Validated @ModelAttribute("scriptBuilder") ManagementScriptBuilder managementScriptBuilder){

        studentService.changeState(managementScriptBuilder.getStudentsList());
        model.addAttribute("message", "Подтверждение студентов прошло успешно");

        return "success_page";
    }

    @GetMapping (value = "/change-state-work")
    public String changeStateR_TO_WORK(Model model){

        studentService.changeState(studentService.getAllRToWorkStudents());
        model.addAttribute("message", "Подтверждение студентов прошло успешно");

        return "success_page";
    }

    @GetMapping (value = "/change-state-delete")
    public String changeStateR_TO_DELETE(Model model){

        studentService.changeState(studentService.getAllRToDeleteStudents());
        model.addAttribute("message", "Подтверждение студентов прошло успешно");

        return "success_page";
    }

    @GetMapping(value = "/journal")
    public String getJournal(Model model){
        model.addAttribute("journals", studentService.getAllJournals());

        return "management_journal_page";
    }

    @GetMapping(value = "/show-journal/{id}")
    public String showJournal(@PathVariable("id") int id, Model model){
        model.addAttribute("journal", studentService.getJournal(id));

        return "raw_pages/journal";
    }

    @PostMapping(value = "script-group-work/{id}")
    public String scriptGroupWork(@PathVariable("id") int id, Model model){

        List<Students> createdStudents = studentService.getGroupById(id).getStudents().stream().filter(st -> st.getState().equals(State.CREATED)).collect(Collectors.toList());
        ManagementScriptBuilder managementScriptBuilder = new ManagementScriptBuilder();
        managementScriptBuilder.setStudentsList(createdStudents);
        Map<String, String> doc = managementScriptBuilder.buildScript();
        Journals journal = new Journals();

        journal.setScript(doc.get("SCRIPT"));
        journal.setType(doc.get("TYPE"));
        studentService.addJournal(journal);
        studentService.changeState(managementScriptBuilder.getStudentsList());
        model.addAttribute("journal", journal);

        return "raw_pages/journal";
    }

    @PostMapping(value = "script-group-delete/{id}")
    public String scriptGroupDelete(@PathVariable("id") int id, Model model){

        List<Students> createdStudents = studentService.getGroupById(id).getStudents().stream().filter(st -> st.getState().equals(State.IN_WORK)).collect(Collectors.toList());
        ManagementScriptBuilder managementScriptBuilder = new ManagementScriptBuilder();
        managementScriptBuilder.setStudentsList(createdStudents);
        Map<String, String> doc = managementScriptBuilder.buildScript();
        Journals journal = new Journals();

        journal.setScript(doc.get("SCRIPT"));
        journal.setType(doc.get("TYPE"));
        studentService.addJournal(journal);
        studentService.changeState(managementScriptBuilder.getStudentsList());
        model.addAttribute("journal", journal);

        return "raw_pages/journal";
    }
}
