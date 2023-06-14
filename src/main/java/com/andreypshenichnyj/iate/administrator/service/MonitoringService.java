package com.andreypshenichnyj.iate.administrator.service;

import com.andreypshenichnyj.iate.administrator.entity.Archives;
import com.andreypshenichnyj.iate.administrator.entity.Logs;
import com.andreypshenichnyj.iate.administrator.entity.Scripts;
import com.andreypshenichnyj.iate.administrator.entity.Thread_scripts;

import java.util.List;

public interface MonitoringService {

    List<Archives> getAllArchives();

    void addArchive(Archives archive);

    Archives getArchive(int id);

    List<Scripts> getAllScripts();

    void addScript(Scripts script);

    Scripts getScript(int id);

    Scripts getScriptByCode(String code);

    List<Thread_scripts> getAllThread_scripts();

    void addThread_script(Thread_scripts thread_script);

    Thread_scripts getThread_script(int id);

    List<Logs> getAllLogs();

    void addLog(Logs log);

    Logs getLog(int id);

    List<Thread_scripts> getAllActiveThreadScripts();

    List<Thread_scripts> getAllNonActiveThreadScripts();

    List<Thread_scripts> getAllStashedThreadScripts();

    List<Archives> getAllActiveArchiveScripts();

    List<Archives> getAllStashedArchiveScripts();

    List<Scripts> getAllActiveScripts();

    List<Scripts> getAllStashedScripts();

    Scripts swapScriptStatus(Scripts script);

    Archives swarArchiveStatus(Archives archive);
}
