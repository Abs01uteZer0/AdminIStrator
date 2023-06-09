package com.andreypshenichnyj.iate.administrator.entity.masters;

public enum Permission {
    ADMINS_WRITE("admins:write"),
    ADMINS_READ("admins:read"),
    TEACHERS_WRITE("teachers:write"),
    TEACHERS_READ("teachers:read"),
    SUPERUSERS_READ("supers:read"),
    SUPERUSERS_WRITE("supers:write");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
