/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;

import me.anchora.inpaasmgr.entry.inpaasmgr.MenuLanguages;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenuLanguagesCriteria;

import org.apache.ibatis.session.RowBounds;

public interface MenuLanguagesService {
    public void insert(MenuLanguages menuLanguages);

    public void update(MenuLanguages menuLanguages);

    public List<MenuLanguages> queryAll();

    public void delete(Long id);

    public List<MenuLanguages> queryAllMenuLanguages(MenuLanguagesCriteria criteria);

    public List<MenuLanguages> queryMenuLanguagesByPage(MenuLanguagesCriteria criteria, RowBounds rowBounds);

    public Integer queryCount(MenuLanguagesCriteria criteria);
}
