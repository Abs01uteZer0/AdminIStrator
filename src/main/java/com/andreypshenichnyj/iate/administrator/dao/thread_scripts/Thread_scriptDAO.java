package com.andreypshenichnyj.iate.administrator.dao.thread_scripts;


import com.andreypshenichnyj.iate.administrator.entity.Thread_scripts;

import java.util.List;

public interface Thread_scriptDAO {

    List<Thread_scripts> getAllThread_scripts();

    void addThread_script(Thread_scripts thread_script);

    Thread_scripts getThread_script(int id);

    List<Thread_scripts> getAllActiveThreadScripts();

    List<Thread_scripts> getAllNonActiveThreadScripts();

    List<Thread_scripts> getAllStashedThreadScripts();
}
