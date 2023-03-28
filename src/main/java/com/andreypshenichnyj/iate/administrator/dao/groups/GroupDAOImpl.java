package com.andreypshenichnyj.iate.administrator.dao.groups;

import com.andreypshenichnyj.iate.administrator.entity.Groups;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDAOImpl implements GroupDAO {

    @PersistenceContext
    EntityManager entityManager;

    //Получаем все группы
    @Override
    public List<Groups> getAllGroups() {
        Query query = entityManager.createQuery("from Groups", Groups.class);
        List<Groups> allGroups = query.getResultList();

        return allGroups;
    }

    //Добавляем или изменяем группу
    @Override
    public void addGroup(Groups group) {
        if (group.getGroup_id() == 0){
            entityManager.persist(group);
        } else {
            entityManager.merge(group);
        }
    }

    //Получаем группу по id
    @Override
    public Groups getGroup(int id) {
        Groups group = entityManager.find(Groups.class, id);
        return group;
    }

    @Override
    public Groups getGroupByName(String name) {
        Groups group = new Groups();
        Query query = entityManager.createQuery("from Groups where group_name = :name", Groups.class);    //Попытка достать группу по имени
        query.setParameter("name", name);
        List result = query.getResultList();
        if (result.isEmpty()) {           //Если такая группа не было найдено, то
            return null;
        } else {
            group = (Groups) result.get(0);    //Однако, если нашли, то присвоим группе ее значение
        }
        return group;
    }
}
