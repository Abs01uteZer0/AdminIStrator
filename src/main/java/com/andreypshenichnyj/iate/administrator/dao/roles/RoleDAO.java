package com.andreypshenichnyj.iate.administrator.dao.roles;

import com.andreypshenichnyj.iate.administrator.entity.Roles;

import java.util.List;

public interface RoleDAO {

    public List<Roles> getAllRoles();

    public Roles getRole(int id);

    public void addRole(Roles role);
}
