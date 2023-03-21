package com.andreypshenichnyj.iate.administrator.dao.roles;

import com.andreypshenichnyj.iate.administrator.entity.Roles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    //Возвращаем список всех ролей
    @Override
    public List<Roles> getAllRoles() {
        Query query = entityManager.createQuery("from Roles", Roles.class);
        List<Roles> allRoles = query.getResultList();

        return allRoles;
    }

    //Находим роль по id
    @Override
    public Roles getRole(int id) {
        Roles role = entityManager.find(Roles.class, id);
        return role;
    }

    //Добавляем роль в БД, если такой роли еще нет
    @Override
    public void addRole(Roles role) {
        Roles newRole = entityManager.merge(role);
        role.setRole_id(newRole.getRole_id());
    }
}
