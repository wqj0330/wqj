package me.anchora.inpaasmgr.entry.base;

import me.anchora.inpaasmgr.entry.inpaasmgr.MenuLanguages;
import me.anchora.inpaasmgr.entry.inpaasmgr.Menus;

public class SuperMenuDetails extends PageEntry {
    private MenuLanguages menuLanguages;
    private Menus menus;
    
    private String menuName;
    private String langName;

    public MenuLanguages getMenuLanguages() {
        return menuLanguages;
    }

    public void setMenuLanguages(MenuLanguages menuLanguages) {
        this.menuLanguages = menuLanguages;
    }

    public Menus getMenus() {
        return menus;
    }

    public void setMenus(Menus menus) {
        this.menus = menus;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

}
