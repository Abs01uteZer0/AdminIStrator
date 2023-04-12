package com.andreypshenichnyj.iate.administrator.entity.Masters;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    TEACHER(Set.of(Permission.TEACHERS_WRITE, Permission.TEACHERS_READ)),
    ADMIN(Set.of(Permission.ADMINS_WRITE, Permission.ADMINS_READ)),
    SUPERUSER(Set.of(Permission.SUPERUSER_WRITE, Permission.SUPERUSER_READ, Permission.ADMINS_WRITE, Permission.ADMINS_READ));

    Role(Set<Permission> permissions){
        this.permissions = permissions;
    }

    private final Set<Permission> permissions;


    public Set<Permission> getPermissions(){
        return permissions;
    }


    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
