package com.andreypshenichnyj.iate.administrator.service;

//import com.andreypshenichnyj.iate.administrator.configuration.WebSecurityConfig;
import com.andreypshenichnyj.iate.administrator.configuration.WebSecurityConfig;
import com.andreypshenichnyj.iate.administrator.dao.master.MasterDAO;
import com.andreypshenichnyj.iate.administrator.entity.Masters;
import com.andreypshenichnyj.iate.administrator.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import com.andreypshenichnyj.iate.administrator.dao.roles.RoleDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterServiceImpl implements MasterService, UserDetailsService {

    @Autowired
    private MasterDAO masterDAO;

    @Autowired
    private RoleDAO roleDAO;



    @Override
    public boolean saveMaster(Masters master){
        Masters masterFromDb = masterDAO.getMasterByLogin(master.getLogin());

        if (masterFromDb != null){
            return false;
        }

        master.setPassword(WebSecurityConfig.passwordEncoder().encode(master.getPassword())); //Перекриптуем пароль, чтобы в он не был виден в базе
        masterDAO.addMaster(master);
        return true;
    }

    @Override
    public List<Roles> getRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public Roles getRole(int id) {
        return roleDAO.getRole(id);
    }

    @Override
    public List<Masters> getMasters() {
        return masterDAO.getAllMasters();
    }

    @Override
    public Masters getMasterById(int id) {
        return masterDAO.getMaster(id);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Masters master = masterDAO.getMasterByLogin(login);
        if (master == null){
            throw new UsernameNotFoundException("User not found");
        }
        return master;
    }
}
