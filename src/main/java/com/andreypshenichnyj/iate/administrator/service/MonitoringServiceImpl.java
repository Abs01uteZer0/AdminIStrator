package com.andreypshenichnyj.iate.administrator.service;

import com.andreypshenichnyj.iate.administrator.dao.archives.ArchiveDAO;
import com.andreypshenichnyj.iate.administrator.dao.logs.LogsDAO;
import com.andreypshenichnyj.iate.administrator.dao.scripts.ScriptDAO;
import com.andreypshenichnyj.iate.administrator.dao.thread_scripts.Thread_scriptDAO;
import com.andreypshenichnyj.iate.administrator.dao.work_rooms.Work_roomDAO;
import com.andreypshenichnyj.iate.administrator.entity.*;
import com.andreypshenichnyj.iate.administrator.service.threads.RunningThreadAdministratorImpl;
import com.andreypshenichnyj.iate.administrator.terminal.Connector;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class MonitoringServiceImpl implements MonitoringService{

    @Autowired
    private ArchiveDAO archiveDAO;

    @Autowired
    private ScriptDAO scriptDAO;

    @Autowired
    private Thread_scriptDAO thread_scriptDAO;

    @Autowired
    private LogsDAO logsDAO;

    @Autowired
    private Work_roomDAO work_roomDAO;

    @Override
    public List<Archives> getAllArchives() {
        return archiveDAO.getAllArchives();
    }

    @Override
    public void addArchive(Archives archive) {
        archiveDAO.addArchive(archive);
    }

    @Override
    public Archives getArchive(int id) {
        return archiveDAO.getArchive(id);
    }

    @Override
    public List<Scripts> getAllScripts() {
        return scriptDAO.getAllScripts();
    }

    @Override
    public void addScript(Scripts script) {
        scriptDAO.addScript(script);
    }

    @Override
    public Scripts getScript(int id) {
        return scriptDAO.getScript(id);
    }

    @Override
    public Scripts getScriptByCode(String code) {
        return scriptDAO.getScriptByCode(code);
    }

    @Override
    public List<Thread_scripts> getAllThread_scripts() {
        return thread_scriptDAO.getAllThread_scripts();
    }

    @Override
    public void addThread_script(Thread_scripts thread_script) {
        thread_scriptDAO.addThread_script(thread_script);
    }

    @Override
    public Thread_scripts getThread_script(int id) {
        return thread_scriptDAO.getThread_script(id);
    }

    @Override
    public List<Logs> getAllLogs() {
        return logsDAO.getAllLogs();
    }

    @Override
    public void addLog(Logs log) {
        logsDAO.addLog(log);
    }

    @Override
    public Logs getLog(int id) {
        return logsDAO.getLog(id);
    }

    @Override
    public List<Thread_scripts> getAllActiveThreadScripts() {
        return thread_scriptDAO.getAllActiveThreadScripts();
    }

    @Override
    public List<Thread_scripts> getAllNonActiveThreadScripts() {
        return thread_scriptDAO.getAllNonActiveThreadScripts();
    }

    @Override
    public List<Thread_scripts> getAllStashedThreadScripts() {
        return thread_scriptDAO.getAllStashedThreadScripts();
    }

    @Override
    public List<Archives> getAllActiveArchiveScripts() {
        return archiveDAO.getAllActiveArchiveScripts();
    }

    @Override
    public List<Archives> getAllStashedArchiveScripts() {
        return archiveDAO.getAllStashedArchiveScripts();
    }

    @Override
    public List<Scripts> getAllActiveScripts() {
        return scriptDAO.getAllActiveScripts();
    }

    @Override
    public List<Scripts> getAllStashedScripts() {
        return scriptDAO.getAllStashedScripts();
    }

    @Override
    public Scripts swapScriptStatus(Scripts script) {
        script.setStatus(!script.isStatus());
        return script;
    }

    @Override
    public Archives swarArchiveStatus(Archives archive) {
        archive.setStatus(!archive.isStatus());
        return archive;
    }

    @Override
    public Map<String, String> getPcsInfo(Work_rooms work_room) {
        List<Sub_pcs> list = work_room.getSub_pcs();
        StringBuilder stringBuilder = new StringBuilder();
//        StringBuilder stringBuilder_error = new StringBuilder();

        stringBuilder.append("ssh -l root ").append(work_room.getMain_pc_ip()).append(" \"pdsh");
        for (Sub_pcs sub_pc: list){
            stringBuilder.append(" ").append(sub_pc.getSub_pc_ip());
        }
        stringBuilder.append(" uptime \"");

        Connector connector = new Connector(stringBuilder.toString());
        BufferedReader inputReader = connector.getInputReader();
        BufferedReader errorReader = connector.getErrorReader();
        StringBuilder sb = new StringBuilder();

        String str = null;
        try {
            while ((str = inputReader.readLine()) != null) {
                sb.append(str);
            }
//            while ((str = errorReader.readLine()) != null) {
//                stringBuilder_error.append(str);
//            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        str = sb.toString();
//        System.out.println(stringBuilder_error.toString());

        Map<String, String> info = new HashMap<>();
        System.out.println(str);
        for (int i=0; i<list.size(); i++){
            if (str.contains(list.get(i).getSub_pc_ip() + ": ssh exited with exit code 255")){
                info.put(list.get(i).getSub_pc_ip(), "Выключен");
            } else {
                info.put(list.get(i).getSub_pc_ip(), "Включен");
            }
        }

        return info;
    }



}