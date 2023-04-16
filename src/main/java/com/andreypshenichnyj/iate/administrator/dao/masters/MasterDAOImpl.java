package com.andreypshenichnyj.iate.administrator.dao.masters;

import com.andreypshenichnyj.iate.administrator.entity.masters.Masters;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MasterDAOImpl implements MasterDAO {

    @PersistenceContext
    EntityManager entityManager;

    //Получаем всех преподавателей/администраторов
    @Override
    public List<Masters> getAllMasters() {
        Query query = entityManager.createQuery("from Masters", Masters.class);
        List<Masters> allMasters = query.getResultList();

        return allMasters;
    }

    @Override
    public List<Masters> getTeachers() {
        return getAllMasters().stream().filter((master -> master.getRole().name().equals("TEACHER"))).collect(Collectors.toList());
    }

    @Override
    public List<Masters> getAdmins() {
        return getAllMasters().stream().filter((master -> master.getRole().name().equals("ADMIN"))).collect(Collectors.toList());
    }

    //Добавляем или изменяем преподавателя/администратора
    @Override
    public void addMaster(Masters master) {
        if (master.getMaster_id() == 0) {
            entityManager.persist(master);
        } else {
            entityManager.merge(master);
        }
    }

    //Получаем преподавателя/администратора по id
    @Override
    public Masters getMaster(int id) {
        Masters master = entityManager.find(Masters.class, id);
        return master;
    }

    @Override
    public Masters getMasterByLogin(String login) {
        Masters master = new Masters();
        Query query = entityManager.createQuery("from Masters where login = :login", Masters.class);
        query.setParameter("login", login);
        List result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            return (Masters) result.get(0);
        }
    }
}

