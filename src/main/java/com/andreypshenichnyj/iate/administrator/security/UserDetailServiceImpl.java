package com.andreypshenichnyj.iate.administrator.security;

import com.andreypshenichnyj.iate.administrator.dao.master.MasterDAO;
import com.andreypshenichnyj.iate.administrator.entity.Masters.Masters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    private final MasterDAO masterDAO;

    @Autowired
    public UserDetailServiceImpl(MasterDAO masterDAO) {
        this.masterDAO = masterDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Masters master = masterDAO.getMasterByLogin(login);

        return SecurityUser.fromMaster(master);
    }
}
