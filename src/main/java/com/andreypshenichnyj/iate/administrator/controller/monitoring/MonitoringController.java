package com.andreypshenichnyj.iate.administrator.controller.monitoring;

import com.andreypshenichnyj.iate.administrator.entity.Archives;
import com.andreypshenichnyj.iate.administrator.entity.Scripts;
import com.andreypshenichnyj.iate.administrator.entity.Thread_scripts;
import com.andreypshenichnyj.iate.administrator.service.MonitoringService;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import com.andreypshenichnyj.iate.administrator.service.scripts.RunningScriptBuilder;
import com.andreypshenichnyj.iate.administrator.service.scripts.ScriptBuilder;
import com.andreypshenichnyj.iate.administrator.service.threads.RunningThreadAdministrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(value = "monitoring")
public class MonitoringController {

    @Autowired
    private MonitoringService monitoringService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private RunningThreadAdministrator runningThreadAdministrator;

    private static int preInitializeFlag = 0;

    @GetMapping("/scripts")
    public String getMonitoringScriptsPage(Model model){
        //Сюда из администрирования добавить
        model.addAttribute("archiveActive", monitoringService.getAllActiveArchiveScripts());
        model.addAttribute("archiveStashed", monitoringService.getAllStashedArchiveScripts());
        model.addAttribute("scriptsActive", monitoringService.getAllActiveScripts());
        model.addAttribute("scriptsStashed", monitoringService.getAllStashedScripts());

        return "scripts";
    }

    @GetMapping("/scripts/swap/{id}")
    public String swapScriptStatus(@PathVariable("id") int id){
        Scripts script = monitoringService.getScript(id);
        script = monitoringService.swapScriptStatus(script);
        monitoringService.addScript(script);

        return "redirect:/monitoring/scripts";
    }

    @GetMapping("/scripts/add-script")
    public String addScriptPage(Model model){
        model.addAttribute("script", new Scripts());

        return "raw_pages/scripts/script";
    }

    @PostMapping("/scripts/success-script")
    public String successScript(@Validated @ModelAttribute("script") Scripts script,
                                BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "raw_pages/scripts/script";
        }
        monitoringService.addScript(script);
        model.addAttribute("message", "Добавление скрипта прошло успешно");

        return "success_page";
    }

    @GetMapping("/archives/swap/{id}")
    public String swapArchiveStatus(@PathVariable("id") int id){
        Archives archive = monitoringService.getArchive(id);
        archive = monitoringService.swarArchiveStatus(archive);
        monitoringService.addArchive(archive);

        return "redirect:/monitoring/scripts";
    }

    @GetMapping("/archives/{id}")
    public String getArchivePage(@PathVariable("id") int id, Model model){
        Archives archive = monitoringService.getArchive(id);
        model.addAttribute("archive", archive);
        model.addAttribute("Flag", false);

        return "raw_pages/scripts/archive";
    }

    @GetMapping("/archives/add-archive")
    public String addArchive(Model model){
        model.addAttribute("scripts", monitoringService.getAllScripts());
        model.addAttribute("work_rooms", studentService.getAllWorkRooms());
        model.addAttribute("sb", new ScriptBuilder());

        return "raw_pages/scripts/archivesb";
    }

    @PostMapping("/archives/between-success")
    public String redArchive(Model model, @ModelAttribute("sb") ScriptBuilder sb){
        Archives archive = new Archives();
        archive.setScript(sb.getScript());
        archive.setStatus(true);
        archive.setGenerated_script(sb.buildScript());
        archive.setWork_room(sb.getWork_room());
        model.addAttribute("archive", archive);
        model.addAttribute("Flag", true);
        model.addAttribute("scripts", monitoringService.getAllScripts());
        model.addAttribute("work_rooms", studentService.getAllWorkRooms());

        return "raw_pages/scripts/archive";
    }

    @PostMapping("/archives/success-archive")
    public String successArchive(@Validated @ModelAttribute("archive") Archives archive, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("Flag", true);
            model.addAttribute("scripts", monitoringService.getAllScripts());
            model.addAttribute("work_rooms", studentService.getAllWorkRooms());
            return "raw_pages/scripts/archive";
        }
        monitoringService.addArchive(archive);
        model.addAttribute("message", "Добавление скрипта прошло успешно");

        return "success_page";
    }

    //running scripts

    @GetMapping("/scripts/active")
    public String getMonitoringActiveSctiptsPage(Model model){
        if (preInitializeFlag == 0){
            monitoringService.refreshDate();
            preInitializeFlag++;
        }
        model.addAttribute("runningActive", monitoringService.getAllActiveThreadScripts());
        model.addAttribute("runningNonActive", monitoringService.getAllNonActiveThreadScripts());
        model.addAttribute("runningStashed", monitoringService.getAllStashedThreadScripts());
        model.addAttribute("logs", monitoringService.getAllLogs());

        return "active_scripts";
    }

    @GetMapping("/scripts/add-active-script")
    public String addActiveScriptPage(Model model){
        model.addAttribute("scripts", monitoringService.getAllScripts());
        model.addAttribute("work_rooms", studentService.getAllWorkRooms());
        model.addAttribute("sb", new RunningScriptBuilder());

        return "raw_pages/scripts/running_scriptsb";
    }

    @PostMapping("/active/between-success")
    public String redActive(Model model, @ModelAttribute("sb") RunningScriptBuilder sb){
        Thread_scripts thread_script = new Thread_scripts();
        thread_script.setScript(sb.getScript());
        thread_script.setStatus(true);
        thread_script.setGenerated_script(sb.buildScript());
        thread_script.setWork_room(sb.getWork_room());
        thread_script.setDate(new Date());
        model.addAttribute("thread_script", thread_script);
        model.addAttribute("Flag", true);
        model.addAttribute("scripts", monitoringService.getAllScripts());
        model.addAttribute("work_rooms", studentService.getAllWorkRooms());

        return "raw_pages/scripts/running_script";
    }

    @PostMapping("/active/success-active")
    public String successActive(@Validated @ModelAttribute("thread_script") Thread_scripts thread_script,
                                 BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("Flag", true);
            model.addAttribute("scripts", monitoringService.getAllScripts());
            model.addAttribute("work_rooms", studentService.getAllWorkRooms());
            return "raw_pages/scripts/running_script";
        }
        runningThreadAdministrator.addScript(thread_script);
        model.addAttribute("message", "Добавление автозапускаемого скрипта прошло успешно");

        return "success_page";
    }





    @GetMapping("/scripts/view-log/{id}")
    public String getLog(@PathVariable("id") int id, Model model){
        model.addAttribute("log", monitoringService.getLog(id));

        return "raw_pages/scripts/log";
    }

    @GetMapping("/scripts/view-script/{id}")
    public String getScript(@PathVariable("id") int id, Model model){
        model.addAttribute("thread_script", monitoringService.getThread_script(id));
        model.addAttribute("Flag", false);

        return "raw_pages/scripts/running_script";
    }

    @GetMapping("/scripts/go-stash/{id}")
    public String scriptToStash(@PathVariable("id") int id, Model model){
        Thread_scripts thread_scripts = monitoringService.getThread_script(id);
        thread_scripts = monitoringService.swapThread_ScriptStatus(thread_scripts);
        monitoringService.addThread_script(thread_scripts);

        return "redirect:/monitoring/scripts/active";
    }

    @GetMapping("/scripts/stop-script/{id}")
    public String stopScript(@PathVariable("id") int id){
        runningThreadAdministrator.deleteScript(monitoringService.getThread_script(id));

        return "";
    }

    @GetMapping("/scripts/run-script/{id}")
    public String runScript(@PathVariable("id") int id){
        runningThreadAdministrator.addScript(monitoringService.getThread_script(id));

        return "";
    }
}
