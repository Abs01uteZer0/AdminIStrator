package com.andreypshenichnyj.iate.administrator.service;

import com.andreypshenichnyj.iate.administrator.entity.Masters;
import com.andreypshenichnyj.iate.administrator.entity.Roles;

import java.util.List;

public interface MasterService {

    boolean saveMaster(Masters master);

    List<Roles> getRoles();

    Roles getRole(int id);

    List<Masters> getMasters();

    Masters getMasterById(int id);
}
