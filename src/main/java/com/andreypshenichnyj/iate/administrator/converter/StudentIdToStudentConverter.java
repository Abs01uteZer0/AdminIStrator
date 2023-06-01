package com.andreypshenichnyj.iate.administrator.converter;

import com.andreypshenichnyj.iate.administrator.entity.students.Students;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentIdToStudentConverter implements Converter<Object, Students> {

    @Autowired
    private StudentService studentService;

    @Override
    public Students convert(Object source) {
        int id = Integer.parseInt((String)source);
        return studentService.getStudentById(id);
    }
}
