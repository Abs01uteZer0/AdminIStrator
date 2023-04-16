package com.andreypshenichnyj.iate.administrator.security;

import com.andreypshenichnyj.iate.administrator.entity.masters.Masters;
import com.andreypshenichnyj.iate.administrator.entity.masters.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromMaster(Masters master){
        if (master == null){
            return new User("NoName", "NoPassword", false, false, false, false, new ArrayList<>(){});
        }
        return new User(master.getLogin(), master.getPassword(),
                master.getStatus().equals(Status.ACTIVE),
                master.getStatus().equals(Status.ACTIVE),
                master.getStatus().equals(Status.ACTIVE),
                master.getStatus().equals(Status.ACTIVE),
                master.getRole().getAuthorities());
    }
}
