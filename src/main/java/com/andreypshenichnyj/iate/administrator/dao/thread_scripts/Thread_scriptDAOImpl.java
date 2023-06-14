package com.andreypshenichnyj.iate.administrator.dao.thread_scripts;

import com.andreypshenichnyj.iate.administrator.entity.Scripts;
import com.andreypshenichnyj.iate.administrator.entity.Thread_scripts;
import com.andreypshenichnyj.iate.administrator.entity.students.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class Thread_scriptDAOImpl implements Thread_scriptDAO{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Thread_scripts> getAllThread_scripts() {
        Query query = entityManager.createQuery("from Thread_scripts", Thread_scripts.class);
        List<Thread_scripts> allScripts = query.getResultList();

        return allScripts;
    }

    @Override
    public void addThread_script(Thread_scripts thread_script) {
        if (thread_script.getThread_script_id() == 0){
            entityManager.persist(thread_script);
        } else {
            entityManager.merge(thread_script);
        }
    }

    @Override
    public Thread_scripts getThread_script(int id) {
        Thread_scripts thread_script = entityManager.find(Thread_scripts.class, id);
        return thread_script;
    }

    @Override
    public List<Thread_scripts> getAllActiveThreadScripts() {
        return getAllThread_scripts().stream().filter((th -> th.getDate() != null)).collect(Collectors.toList());
    }

    @Override
    public List<Thread_scripts> getAllNonActiveThreadScripts() {
        return getAllThread_scripts().stream().filter((th -> th.getDate() == null)).collect(Collectors.toList());
    }

    @Override
    public List<Thread_scripts> getAllStashedThreadScripts() {
        return getAllThread_scripts().stream().filter((th -> !th.isStatus())).collect(Collectors.toList());
    }
}
