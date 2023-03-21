package com.andreypshenichnyj.iate.administrator.dao.departments;

import com.andreypshenichnyj.iate.administrator.entity.Departments;

import java.util.List;

public interface DepartmentDAO {

    public List<Departments> getAllDepartments();

    public void addDepartment(Departments department);

    public Departments getDepartment(int id);

    public Departments getDepartmentByName(String name);
}
