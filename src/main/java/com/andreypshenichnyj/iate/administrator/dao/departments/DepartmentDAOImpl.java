package com.andreypshenichnyj.iate.administrator.dao.departments;

import com.andreypshenichnyj.iate.administrator.entity.Departments;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

    @PersistenceContext
    EntityManager entityManager;

    //Получаем все отделения
    @Override
    public List<Departments> getAllDepartments() {
        Query query = entityManager.createQuery("from Departments", Departments.class);
        List<Departments> allDepartments = query.getResultList();

        return allDepartments;
    }

    //Добавляем или изменяем отделение
    @Override
    public void addDepartment(Departments department) {
        if (department.getDepartment_id() == 0){
            entityManager.persist(department);
        } else {
            entityManager.merge(department);
        }
    }

    //Получаем отделение по id
    @Override
    public Departments getDepartment(int id) {
        Departments department = entityManager.find(Departments.class, id);
        return department;
    }

    @Override
    public Departments getDepartmentByName(String name) {
        Departments deps = new Departments();
        Query query = entityManager.createQuery("from Departments where department_name = :name", Departments.class);    //Попытка достать отделение по имени
        query.setParameter("name", name);
        List result = query.getResultList();
        if (result.isEmpty()) {           //Если такого отделения не было найдено, то
            return null;
        } else {
            deps = (Departments) result.get(0);    //Однако, если нашли, то присвоим департаменту его значение
        }
        return deps;
    }
}
