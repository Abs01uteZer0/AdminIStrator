package com.andreypshenichnyj.iate.administrator.converter;

import com.andreypshenichnyj.iate.administrator.entity.Groups;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GroupNameToGroupOrNullConverter implements Converter<Object, Groups> {

    @Autowired
    private StudentService studentService;

    @Override
    public Groups convert(Object source) {
        return studentService.getGroupByName((String) source);
    }
}
