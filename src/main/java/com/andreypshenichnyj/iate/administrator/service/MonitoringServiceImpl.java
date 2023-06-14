package com.andreypshenichnyj.iate.administrator.service;

import com.andreypshenichnyj.iate.administrator.dao.archives.ArchiveDAO;
import com.andreypshenichnyj.iate.administrator.dao.logs.LogsDAO;
import com.andreypshenichnyj.iate.administrator.dao.scripts.ScriptDAO;
import com.andreypshenichnyj.iate.administrator.dao.thread_scripts.Thread_scriptDAO;
import com.andreypshenichnyj.iate.administrator.entity.Archives;
import com.andreypshenichnyj.iate.administrator.entity.Logs;
import com.andreypshenichnyj.iate.administrator.entity.Scripts;
import com.andreypshenichnyj.iate.administrator.entity.Thread_scripts;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}