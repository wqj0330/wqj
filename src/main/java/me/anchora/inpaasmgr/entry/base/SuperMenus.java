package me.anchora.inpaasmgr.entry.base;

import me.anchora.inpaasmgr.entry.inpaasmgr.MenuDetails;
import me.anchora.inpaasmgr.entry.inpaasmgr.Menus;


public class SuperMenus extends PageEntry {
    private MenuDetails menuDetails;
    private Menus parentMenu;
    private String parentMenuName;

    public MenuDetails getMenuDetails() {
        return menuDetails;
    }

    public void setMenuDetails(MenuDetails menuDetails) {
        this.menuDetails = menuDetails;
    }

    public Menus getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menus parentMenu) {
        this.parentMenu = parentMenu;
    }

    public String getParentMenuName() {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }
}
