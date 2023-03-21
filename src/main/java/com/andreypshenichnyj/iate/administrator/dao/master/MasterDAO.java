package com.andreypshenichnyj.iate.administrator.dao.master;

import com.andreypshenichnyj.iate.administrator.entity.Masters;

import java.util.List;

public interface MasterDAO {

    public List<Masters> getAllMasters();

    public void addMaster(Masters master);

    public Masters getMaster(int id);

    Masters getMasterByLogin(String login);
}
