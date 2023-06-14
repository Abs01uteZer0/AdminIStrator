package com.andreypshenichnyj.iate.administrator.dao.logs;

import com.andreypshenichnyj.iate.administrator.entity.Logs;
import com.andreypshenichnyj.iate.administrator.entity.Thread_scripts;

import java.util.List;

public interface LogsDAO {

    List<Logs> getAllLogs();

    void addLog(Logs log);

    Logs getLog(int id);
}
