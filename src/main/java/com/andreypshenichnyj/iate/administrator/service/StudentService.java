package com.andreypshenichnyj.iate.administrator.service;

import com.andreypshenichnyj.iate.administrator.entity.Departments;
import com.andreypshenichnyj.iate.administrator.entity.Groups;
import com.andreypshenichnyj.iate.administrator.entity.Masters;
import com.andreypshenichnyj.iate.administrator.entity.Students;

import java.util.List;

public interface StudentService {

    void saveStudent(Students students);

    List<Students> getStudents();

    Groups getGroupByName(String name);

    Departments getDepartmentByName(String name);

    List<Groups> getGroups();

    List<Departments> getDepartments();

    Groups getGroupById(int id);

    Departments getDepartmentById(int id);

    void saveGroup(Groups group);

    void saveDepartment(Departments department);

    Students getStudentById(int id);

    void deleteAccessGroupOfStudents(int id);

    void deleteAccessDepartmentOfStudents(int id);

    void deleteAccessOfStudent(Students student);

}
