package com.andreypshenichnyj.iate.administrator.service;

import com.andreypshenichnyj.iate.administrator.configuration.WebSecurityConfig;
import com.andreypshenichnyj.iate.administrator.dao.master.MasterDAO;
import com.andreypshenichnyj.iate.administrator.entity.Masters.Masters;
import com.andreypshenichnyj.iate.administrator.entity.Masters.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MasterServiceImpl implements MasterService{

    @Autowired
    PasswordEncoder passwordEncoder;

    private final List<Role> roles = List.of(Role.TEACHER, Role.ADMIN);

    @Autowired
    private MasterDAO masterDAO;

    @Override
    public boolean saveMaster(Masters master){      //!!!
        Masters masterFromDb = masterDAO.getMasterByLogin(master.getLogin());

        if (masterFromDb != null){
            if (master.getPassword().equals(masterFromDb.getPassword())){
                //Если пароли равны, то их не нужно перекодировать
                masterDAO.addMaster(master);
                return true;
            }
        }

        master.setPassword(passwordEncoder.encode(master.getPassword())); //Перекриптуем пароль, чтобы в он не был виден в базе
        masterDAO.addMaster(master);
        return true;
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public List<Masters> getMasters() {
        return masterDAO.getAllMasters();
    }

    @Override
    public Masters getMasterById(int id) {
        return masterDAO.getMaster(id);
    }

}
