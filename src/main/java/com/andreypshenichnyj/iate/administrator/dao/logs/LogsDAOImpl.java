package com.andreypshenichnyj.iate.administrator.dao.logs;

import com.andreypshenichnyj.iate.administrator.entity.Logs;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogsDAOImpl implements LogsDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Logs> getAllLogs() {
        Query query = entityManager.createQuery("from Logs", Logs.class);
        List<Logs> allLogs = query.getResultList();

        return allLogs;
    }

    @Override
    public void addLog(Logs log) {
        if (log.getLog_id() == 0){
            entityManager.persist(log);
        } else {
            entityManager.merge(log);
        }
    }

    @Override
    public Logs getLog(int id) {
        Logs log = entityManager.find(Logs.class, id);
        return log;
    }
}
