package com.andreypshenichnyj.iate.administrator.dao.scripts;

import com.andreypshenichnyj.iate.administrator.entity.Archives;
import com.andreypshenichnyj.iate.administrator.entity.Departments;
import com.andreypshenichnyj.iate.administrator.entity.Scripts;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ScriptDAOImpl implements ScriptDAO{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Scripts> getAllScripts() {
        Query query = entityManager.createQuery("from Scripts", Scripts.class);
        List<Scripts> allScripts = query.getResultList();

        return allScripts;
    }

    @Override
    public void addScript(Scripts script) {
        if (script.getScript_id() == 0){
            entityManager.persist(script);
        } else {
            entityManager.merge(script);
        }
    }

    @Override
    public Scripts getScript(int id) {
        Scripts script = entityManager.find(Scripts.class, id);
        return script;
    }

    @Override
    public Scripts getScriptByCode(String code) {
        Scripts script = new Scripts();
        Query query = entityManager.createQuery("from Scripts where script_code = :code", Scripts.class);
        query.setParameter("code", code);
        List result = query.getResultList();
        if (result.isEmpty()) {           //Если такого отделения не было найдено, то
            return null;
        } else {
            script = (Scripts) result.get(0);    //Однако, если нашли, то присвоим департаменту его значение
        }
        return script;
    }

    @Override
    public List<Scripts> getAllActiveScripts() {
        return getAllScripts().stream().filter((script -> script.isStatus())).collect(Collectors.toList());
    }

    @Override
    public List<Scripts> getAllStashedScripts() {
        return getAllScripts().stream().filter((script -> !script.isStatus())).collect(Collectors.toList());
    }
}
