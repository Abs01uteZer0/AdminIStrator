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
            map.put(ip, new Connector("ping -w 1 -c 1 " + ip).getInfo());
        }
        return map;
    }
}
