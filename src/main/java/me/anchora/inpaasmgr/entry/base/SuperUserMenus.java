package me.anchora.inpaasmgr.entry.base;

import me.anchora.inpaasmgr.entry.inpaasmgr.Login;
import me.anchora.inpaasmgr.entry.inpaasmgr.Menus;

public class SuperUserMenus extends PageEntry {
    private Menus menus;
    private Login users;
    private String menuName;
    public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String userName;
    public Menus getMenus() {
        return menus;
    }

    public void setMenus(Menus menus) {
        this.menus = menus;
    }

    public Login getUsers() {
        return users;
    }

    public void setUsers(Login users) {
        this.users = users;
    }
}
