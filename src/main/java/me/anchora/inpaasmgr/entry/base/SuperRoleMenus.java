package me.anchora.inpaasmgr.entry.base;

import me.anchora.inpaasmgr.entry.inpaasmgr.Menus;
import me.anchora.inpaasmgr.entry.inpaasmgr.Roles;

public class SuperRoleMenus extends PageEntry {
    private Roles roles;
    private Menus menus;
    private String roleName;
    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	private String menuName;
    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Menus getMenus() {
        return menus;
    }

    public void setMenus(Menus menus) {
        this.menus = menus;
    }

}
