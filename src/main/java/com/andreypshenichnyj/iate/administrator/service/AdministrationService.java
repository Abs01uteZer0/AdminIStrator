package com.andreypshenichnyj.iate.administrator.service;

import com.andreypshenichnyj.iate.administrator.entity.Sub_pcs;

import java.util.List;
import java.util.Map;

public interface AdministrationService {
    Map<String, Boolean> getIpWithInfo(List<Sub_pcs> list);
}
