package com.andreypshenichnyj.iate.administrator.service;

import com.andreypshenichnyj.iate.administrator.entity.Sub_pcs;
import com.andreypshenichnyj.iate.administrator.terminal.Connector;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdministrationServiceImpl implements AdministrationService{

    @Override
    public Map<String, Boolean> getIpWithInfo(List<Sub_pcs> list) {
        Map<String, Boolean> map = new HashMap<>();
        for (Sub_pcs sub: list) {
            String ip = sub.getSub_pc_ip();
            Connector connector = new Connector("ping -w 1 " + ip);         //Для Линукс: "ping -w 1 -c 1 " + ip
            boolean b = connector.getBoolean();
            map.put(ip, b);
        }
        return map;
    }

    public Map<String, Boolean> fakeFunction(){
        Map<String, Boolean> map = new HashMap<>();
        map.put("vk.com", true);
        map.put("google.com", true);
        map.put("192.168.0.1", false);
        map.put("1.1.1.1", false);
        map.put("yandex.ru", true);
        return map;
    }
}
