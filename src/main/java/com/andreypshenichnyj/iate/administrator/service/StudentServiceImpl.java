package com.andreypshenichnyj.iate.administrator.service;

import com.andreypshenichnyj.iate.administrator.dao.departments.DepartmentDAO;
import com.andreypshenichnyj.iate.administrator.dao.groups.GroupDAO;
import com.andreypshenichnyj.iate.administrator.dao.journals.JournalsDAO;
import com.andreypshenichnyj.iate.administrator.dao.students.StudentDAO;
import com.andreypshenichnyj.iate.administrator.dao.work_rooms.Work_roomDAO;
import com.andreypshenichnyj.iate.administrator.entity.Departments;
import com.andreypshenichnyj.iate.administrator.entity.Groups;
import com.andreypshenichnyj.iate.administrator.entity.Journals;
import com.andreypshenichnyj.iate.administrator.entity.Work_rooms;
import com.andreypshenichnyj.iate.administrator.entity.students.State;
import com.andreypshenichnyj.iate.administrator.entity.students.Students;
import com.andreypshenichnyj.iate.administrator.service.generators.LogAndPassGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
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

    @Autowired
    private JournalsDAO journalsDAO;

    private LogAndPassGenerator logAndPassGenerator;

    {
        logAndPassGenerator = new LogAndPassGenerator();
    }

    @Override
    public void saveStudent(Students students) {
        students.setLogin(logAndPassGenerator.getLogin(students.getName(), students.getSurname(), students.getMiddle_name()));
        students.setPassword(logAndPassGenerator.getRandomPassword());
        students.setState(State.CREATED);

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
        for (int i=0; i<list.size(); i++){
            list.get(i).setLogin(logAndPassGenerator.getLogin(list.get(i).getName(), list.get(i).getSurname(), list.get(i).getMiddle_name()));
            list.get(i).setPassword(logAndPassGenerator.getRandomPassword());
        }
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

    @Override
    public List<Journals> getAllJournals() {
        List<Journals> list = journalsDAO.getAllJournals();
        Collections.sort(list, new Comparator<Journals>() {
            @Override
            public int compare(Journals o1, Journals o2) {
                return o2.getJournal_id()-o1.getJournal_id();
            }
        });
        return list;
    }

    @Override
    public Journals getJournal(int id) {
        return journalsDAO.getJournal(id);
    }

    @Override
    public void addJournal(Journals journal) {
        journalsDAO.addJournal(journal);
    }

    @Override
    public void changeState(List<Students> students) {
        if (students != null && !students.isEmpty()){
            State state = students.get(0).getState();
            State toState = null;
            if (state.equals(State.CREATED)){
                toState = State.R_TO_WORK;
            } else if (state.equals(State.IN_WORK)){
                toState = State.R_TO_DELETE;
            } else if (state.equals(State.R_TO_WORK)){
                toState = State.IN_WORK;
            } else if (state.equals(State.R_TO_DELETE)){
                toState = State.DELETED;
            } else if (state.equals(State.DELETED)){
                toState = State.CREATED;
            }

            for (int i=0; i<students.size(); i++){
                Students student_1 = students.get(i);
                student_1.setState(toState);
                studentDAO.addStudent(student_1);
            }
            addGroupOfStudents(students);
        }
    }
}
