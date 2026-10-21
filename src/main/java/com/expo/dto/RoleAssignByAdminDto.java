package com.expo.dto;

public class RoleAssignByAdminDto {
    private String uname;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RoleAssignByAdminDto{" +
                "msg='" + msg + '\'' +
                ", uname='" + uname + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    private String roleName;
    private String msg;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

}
