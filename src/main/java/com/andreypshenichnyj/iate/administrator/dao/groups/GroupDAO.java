package com.andreypshenichnyj.iate.administrator.dao.groups;

import com.andreypshenichnyj.iate.administrator.entity.Groups;

import java.util.List;

public interface GroupDAO {

    public List<Groups> getAllGroups();

    public void addGroup(Groups group);

    public Groups getGroup(int id);

    public Groups getGroupByName(String name);
}
