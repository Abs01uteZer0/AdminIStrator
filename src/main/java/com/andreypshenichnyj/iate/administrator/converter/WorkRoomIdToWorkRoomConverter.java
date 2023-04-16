package com.andreypshenichnyj.iate.administrator.converter;

import com.andreypshenichnyj.iate.administrator.entity.Departments;
import com.andreypshenichnyj.iate.administrator.entity.Work_rooms;
import com.andreypshenichnyj.iate.administrator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WorkRoomIdToWorkRoomConverter implements Converter<Object, Work_rooms> {
    @Autowired
    private StudentService studentService;


    @Override
    public Work_rooms convert(Object source) {
        int id = Integer.parseInt((String)source);
        return studentService.getWorkRoomById(id);
    }
}
