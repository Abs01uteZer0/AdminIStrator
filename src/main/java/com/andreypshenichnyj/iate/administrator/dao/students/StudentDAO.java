package com.andreypshenichnyj.iate.administrator.dao.students;

import com.andreypshenichnyj.iate.administrator.entity.students.Students;

import java.util.List;

public interface StudentDAO {

    List<Students> getAllStudents();

    void addStudent(Students student);

    Students getStudent(int id);

    void deleteAccessListOfStudents(List<Students> list);

    void deleteAccessOfStudent(Students student);

    void addGroupOfStudents(List<Students> list);

    List<Students> getAllCreatedStudents();

    List<Students> getAllRToDeleteStudents();

    List<Students> getAllRToWorkStudents();

    List<Students> getAllInWorkStudents();

    List<Students> getAllDeletedStudents();

    void recoveryAccessOfStudent(Students student);

    void recoveryAccessListOfStudents(List<Students> list);
}
