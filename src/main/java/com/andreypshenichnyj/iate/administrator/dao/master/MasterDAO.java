package com.andreypshenichnyj.iate.administrator.dao.master;

import com.andreypshenichnyj.iate.administrator.entity.Masters.Masters;

import java.util.List;

public interface MasterDAO {

    List<Masters> getAllMasters();

    List<Masters> getTeachers();

    List<Masters> getAdmins();

    void addMaster(Masters master);

    Masters getMaster(int id);

    Masters getMasterByLogin(String login);
}
