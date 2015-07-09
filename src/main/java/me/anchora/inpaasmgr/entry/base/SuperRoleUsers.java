package me.anchora.inpaasmgr.entry.base;

import me.anchora.inpaasmgr.entry.inpaasmgr.Login;
import me.anchora.inpaasmgr.entry.inpaasmgr.Roles;

public class SuperRoleUsers extends PageEntry {
    private Login users;
    private Roles roles;

	private String roleName;
	private String userName;
    public Login getUsers() {
        return users;
    }

    public void setUsers(Login users) {
        this.users = users;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
