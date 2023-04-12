package com.andreypshenichnyj.iate.administrator.entity.Masters;

public enum Permission {
    ADMINS_WRITE("admins:write"),
    ADMINS_READ("admins:read"),
    TEACHERS_WRITE("teachers:write"),
    TEACHERS_READ("teachers:read"),
    SUPERUSER_READ("super:read"),
    SUPERUSER_WRITE("super:write");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
