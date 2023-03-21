package com.andreypshenichnyj.iate.administrator.service;

import com.andreypshenichnyj.iate.administrator.dao.departments.DepartmentDAO;
import com.andreypshenichnyj.iate.administrator.dao.groups.GroupDAO;
import com.andreypshenichnyj.iate.administrator.dao.student.StudentDAO;
import com.andreypshenichnyj.iate.administrator.entity.Departments;
import com.andreypshenichnyj.iate.administrator.entity.Groups;
import com.andreypshenichnyj.iate.administrator.entity.Students;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Override
    public void saveStudent(Students students) {
        studentDAO.addStudent(students);
    }

    @Override
    public List<Students> getStudents() {
        return studentDAO.getAllStudents();
    }

    @Override
    public Groups getGroupByName(String name) {
        return groupDAO.getGroupByName(name);
    }

    @Override
    public Departments getDepartmentByName(String name) {
        return departmentDAO.getDepartmentByName(name);
    }

    @Override
    public List<Groups> getGroups() {
        return groupDAO.getAllGroups();
    }

    @Override
    public List<Departments> getDepartments() {
        return departmentDAO.getAllDepartments();
    }

    @Override
    public Groups getGroupById(int id) {
        return groupDAO.getGroup(id);
    }

    @Override
    public Departments getDepartmentById(int id) {
        return departmentDAO.getDepartment(id);
    }

    @Override
    public void saveGroup(Groups group) {
        groupDAO.addGroup(group);
    }

    @Override
    public void saveDepartment(Departments department) {
        departmentDAO.addDepartment(department);
    }

    @Override
    public Students getStudentById(int id) {
        return studentDAO.getStudent(id);
    }

    @Override
    public void deleteAccessGroupOfStudents(int id) {
        Groups group = groupDAO.getGroup(id);
        studentDAO.deleteAccessListOfStudents(group.getStudents());
    }

    @Override
    public void deleteAccessDepartmentOfStudents(int id) {
        Departments department = departmentDAO.getDepartment(id);
        List<Groups> list = department.getGroups();
        for (Groups group : list) {
            studentDAO.deleteAccessListOfStudents(group.getStudents());
        }
    }

    @Override
    public void deleteAccessOfStudent(Students student) {
        studentDAO.deleteAccessOfStudent(student);
    }
}
