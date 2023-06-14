package com.andreypshenichnyj.iate.administrator.converter;

import com.andreypshenichnyj.iate.administrator.entity.Scripts;
import com.andreypshenichnyj.iate.administrator.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScriptIdToScriptConverter implements Converter<Object, Scripts> {

    @Autowired
    private MonitoringService monitoringService;

    @Override
    public Scripts convert(Object source) {
        int id = Integer.parseInt((String)source);
        return monitoringService.getScript(id);
    }
}
