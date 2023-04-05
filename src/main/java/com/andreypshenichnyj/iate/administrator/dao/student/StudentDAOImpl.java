package com.andreypshenichnyj.iate.administrator.dao.student;

import com.andreypshenichnyj.iate.administrator.entity.Students;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentDAOImpl implements StudentDAO{

    @PersistenceContext
    private EntityManager entityManager;

    //Возвращаем список всех студентов из БД
    @Override
    public List<Students> getAllStudents() {
        Query query = entityManager.createQuery("from Students", Students.class);
        List<Students> allStudents = query.getResultList();

        return allStudents;
    }

    //Если у студента нет id, то это новый студент. А если есть, то мы изменяем данные в таблице
    @Override
    public void addStudent(Students student) {
        if (student.getStudent_id() == 0){
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }
    }

    //Находим студента в базе по id и возвращаем его
    @Override
    public Students getStudent(int id) {
        Students student = entityManager.find(Students.class, id);
        return student;
    }

    @Override
    public void deleteAccessListOfStudents(List<Students> list) {
        for (int i = 0; i < list.size(); i++) {
            deleteAccessOfStudent(list.get(i));
        }
    }

    @Override
    public void deleteAccessOfStudent(Students student) {
        student.setAccess(false);
        entityManager.merge(student);
    }

    @Override
    public void addGroupOfStudents(List<Students> list) {
        for (Students student: list) {
            addStudent(student);
        }
    }

    @Override
    public List<Students> getAllActiveStudents() {
        return getAllStudents().stream().filter((student -> student.isAccess())).collect(Collectors.toList());

    }

    @Override
    public List<Students> getAllNonActiveStudents() {
        return getAllStudents().stream().filter((student -> !student.isAccess())).collect(Collectors.toList());
    }

    @Override
    public void recoveryAccessOfStudent(Students student) {
        student.setAccess(true);
        entityManager.merge(student);
    }

    @Override
    public void recoveryAccessListOfStudents(List<Students> list) {
        for (int i = 0; i < list.size(); i++) {
            recoveryAccessOfStudent(list.get(i));
        }
    }
}
