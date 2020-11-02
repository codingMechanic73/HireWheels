package com.upgrad.hirewheels.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private int roleId;

    @Column(length = 50, unique = true)
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Users> users;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
