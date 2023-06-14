package com.andreypshenichnyj.iate.administrator.controller.monitoring;

import com.andreypshenichnyj.iate.administrator.entity.Archives;
import com.andreypshenichnyj.iate.administrator.entity.Scripts;
import com.andreypshenichnyj.iate.administrator.service.MonitoringService;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import com.andreypshenichnyj.iate.administrator.service.scripts.ScriptBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "monitoring")
public class MonitoringController {

    @Autowired
    private MonitoringService monitoringService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/scripts")
    public String getMonitoringSctiptsPage(Model model){
        //Сюда из администрирования добавить
        model.addAttribute("archiveActive", monitoringService.getAllActiveArchiveScripts());
        model.addAttribute("archiveStashed", monitoringService.getAllStashedArchiveScripts());
        model.addAttribute("scriptsActive", monitoringService.getAllActiveScripts());
        model.addAttribute("scriptsStashed", monitoringService.getAllStashedScripts());

        return "scripts";
    }

    @GetMapping("/scripts/active")
    public String getMonitoringActiveSctiptsPage(Model model){
        model.addAttribute("runningActive", monitoringService.getAllActiveThreadScripts());
        model.addAttribute("runningNonActive", monitoringService.getAllNonActiveThreadScripts());
        model.addAttribute("runningStashed", monitoringService.getAllStashedThreadScripts());
        model.addAttribute("logs", monitoringService.getAllLogs());

        return "active_scripts";
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
    public String successArchive(@ModelAttribute("archive") Archives archive, Model model){
        monitoringService.addArchive(archive);
        model.addAttribute("message", "Добавление скрипта прошло успешно");

        return "success_page";
    }
    //Главная страница: 2 таблицы с табами (скрипты одиночные и автоматические) + таблица с имеющимися скриптами с кнопкой добавить
    //К каждой таблице снизу кнопка для перехода на генератор скриптов
    //В начале страницы (в описание) добавить ссылку для перехода на администрирование, чтобы посмотреть компьютеры

    //Для каждой таблицы в поле сделать ссылку на просмотр записи без возможности редактировать, также рядом разместить
    //Кнопку в стэш (в стэше будут хранится записи с возможностью просмотра и восстановления)

    //+ в самое начало добавить таблицу в которой выводится информация о запущенных скриптах!!!!
}
