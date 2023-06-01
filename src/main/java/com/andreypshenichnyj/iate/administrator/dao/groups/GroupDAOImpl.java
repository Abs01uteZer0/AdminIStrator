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

    @Override
    public List<Groups> getAllGroups() {
        Query query = entityManager.createQuery("from Groups", Groups.class);
        List<Groups> allGroups = query.getResultList();

        return allGroups;
    }

    @Override
    public void addGroup(Groups group) {
        if (group.getGroup_id() == 0){
            entityManager.persist(group);
        } else {
            entityManager.merge(group);
        }
    }

    @Override
    public Groups getGroup(int id) {
        Groups group = entityManager.find(Groups.class, id);
        return group;
    }

    @Override
    public Groups getGroupByName(String name) {
        Groups group = new Groups();
        Query query = entityManager.createQuery("from Groups where group_name = :name", Groups.class);
        query.setParameter("name", name);
        List result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            group = (Groups) result.get(0);
        }
        return group;
    }
}
