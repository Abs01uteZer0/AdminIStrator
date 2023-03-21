package com.andreypshenichnyj.iate.administrator.converter;

import com.andreypshenichnyj.iate.administrator.entity.Departments;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DepartmentIdToDepartmentConverter implements Converter<Object, Departments> {

    @Autowired
    private StudentService studentService;

    @Override
    public Departments convert(Object source) {
        int id = Integer.parseInt((String)source);
        return studentService.getDepartmentById(id);
    }
}
