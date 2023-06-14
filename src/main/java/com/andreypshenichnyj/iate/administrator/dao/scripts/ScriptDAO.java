package com.andreypshenichnyj.iate.administrator.dao.scripts;

import com.andreypshenichnyj.iate.administrator.entity.Archives;
import com.andreypshenichnyj.iate.administrator.entity.Scripts;

import java.util.List;

public interface ScriptDAO {

    List<Scripts> getAllScripts();

    void addScript(Scripts script);

    Scripts getScript(int id);

    Scripts getScriptByCode(String code);

    List<Scripts> getAllActiveScripts();

    List<Scripts> getAllStashedScripts();
}
