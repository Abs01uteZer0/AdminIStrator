package com.andreypshenichnyj.iate.administrator.service;

import com.andreypshenichnyj.iate.administrator.entity.Masters.Masters;
import com.andreypshenichnyj.iate.administrator.entity.Masters.Role;

import java.util.List;

public interface MasterService {

    boolean saveMaster(Masters master);

    List<Role> getRoles();

    List<Masters> getMasters();

    Masters getMasterById(int id);
}
