package com.andreypshenichnyj.iate.administrator.entity.Masters;

public enum Permission {
    ADMINS_WRITE("admins:write"),
    ADMINS_READ("admins:read"),
    TEACHERS_WRITE("teachers:write"),
    TEACHERS_READ("teachers:read");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
