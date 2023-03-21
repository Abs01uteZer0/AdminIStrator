package com.andreypshenichnyj.iate.administrator.converter;

import com.andreypshenichnyj.iate.administrator.entity.Roles;
import com.andreypshenichnyj.iate.administrator.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleIdToRoleConverter implements Converter<Object, Roles> {

    @Autowired
    private MasterService masterService;

    @Override
    public Roles convert(Object source) {
        int id = Integer.parseInt((String) source);
        return masterService.getRole(id);
    }
}
