package com.andreypshenichnyj.iate.administrator.service;

import com.andreypshenichnyj.iate.administrator.entity.Departments;
import com.andreypshenichnyj.iate.administrator.entity.Groups;
import com.andreypshenichnyj.iate.administrator.entity.Work_rooms;
import com.andreypshenichnyj.iate.administrator.entity.students.Students;

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

    void deleteAccessOfStudent(int id);

    void addGroupOfStudents(List<Students> list);

    List<Students> getAllActiveStudents();

    List<Students> getAllNonActiveStudents();

    void recoveryAccessGroupOfStudents(int id);

    void recoveryAccessDepartmentOfStudents(int id);

    void recoveryAccessOfStudent(int id);

    List<Work_rooms> getAllWorkRooms();

    Work_rooms getWorkRoomById(int id);

}
