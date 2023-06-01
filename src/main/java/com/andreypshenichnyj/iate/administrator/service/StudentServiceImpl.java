package com.andreypshenichnyj.iate.administrator.service;

import com.andreypshenichnyj.iate.administrator.dao.departments.DepartmentDAO;
import com.andreypshenichnyj.iate.administrator.dao.groups.GroupDAO;
import com.andreypshenichnyj.iate.administrator.dao.students.StudentDAO;
import com.andreypshenichnyj.iate.administrator.dao.work_rooms.Work_roomDAO;
import com.andreypshenichnyj.iate.administrator.entity.Departments;
import com.andreypshenichnyj.iate.administrator.entity.Groups;
import com.andreypshenichnyj.iate.administrator.entity.Work_rooms;
import com.andreypshenichnyj.iate.administrator.entity.students.State;
import com.andreypshenichnyj.iate.administrator.entity.students.Students;
import com.andreypshenichnyj.iate.administrator.service.generators.LoginGenerator;
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
    private Work_roomDAO work_roomDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    private LoginGenerator loginGenerator;

    {
        loginGenerator = new LoginGenerator();
    }

    @Override
    public void saveStudent(Students students) {
        students.setLogin(loginGenerator.getLogin(students.getName(), students.getSurname(), students.getMiddle_name()));
        students.setState(State.CREATED);
        students.setPassword("12345");
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
        for (int i = 0; i < list.size(); i++) {
            studentDAO.deleteAccessListOfStudents(list.get(i).getStudents());
        }
    }

    @Override
    public void deleteAccessOfStudent(int id) {
        studentDAO.deleteAccessOfStudent(studentDAO.getStudent(id));
    }

    @Override
    public void addGroupOfStudents(List<Students> list) {
        studentDAO.addGroupOfStudents(list);
    }

    @Override
    public List<Students> getAllCreatedStudents() {
        return studentDAO.getAllCreatedStudents();
    }

    @Override
    public List<Students> getAllRToDeleteStudents() {
        return studentDAO.getAllRToDeleteStudents();
    }

    @Override
    public List<Students> getAllRToWorkStudents() {
        return studentDAO.getAllRToWorkStudents();
    }

    @Override
    public List<Students> getAllInWorkStudents() {
        return studentDAO.getAllInWorkStudents();
    }

    @Override
    public List<Students> getAllDeletedStudents() {
        return studentDAO.getAllDeletedStudents();
    }

    @Override
    public void recoveryAccessGroupOfStudents(int id) {
        Groups group = groupDAO.getGroup(id);
        studentDAO.recoveryAccessListOfStudents(group.getStudents());
    }

    @Override
    public void recoveryAccessDepartmentOfStudents(int id) {
        Departments department = departmentDAO.getDepartment(id);
        List<Groups> list = department.getGroups();
        for (int i = 0; i < list.size(); i++) {
            studentDAO.recoveryAccessListOfStudents(list.get(i).getStudents());
        }
    }

    @Override
    public void recoveryAccessOfStudent(int id) {
        studentDAO.recoveryAccessOfStudent(studentDAO.getStudent(id));
    }

    @Override
    public List<Work_rooms> getAllWorkRooms() {
        return work_roomDAO.getAllWorkRooms();
    }

    @Override
    public Work_rooms getWorkRoomById(int id) {
        return work_roomDAO.getWorkRoomById(id);
    }
}
