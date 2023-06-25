package com.andreypshenichnyj.iate.administrator.service.threads;

import com.andreypshenichnyj.iate.administrator.entity.Thread_scripts;

public interface RunningThreadAdministrator {

    void addScript(Thread_scripts thread_script);

    void deleteScript(Thread_scripts thread_script);
}
