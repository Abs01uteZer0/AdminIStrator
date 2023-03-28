package com.andreypshenichnyj.iate.administrator.converter;

import com.andreypshenichnyj.iate.administrator.entity.Departments;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class DepartmentNameToDepartmentOrNullConverter implements Converter<Object, Departments> {

    @Autowired
    StudentService studentService;

    @Override
    public Departments convert(Object source) {
        return studentService.getDepartmentByName((String) source);
    }
}
