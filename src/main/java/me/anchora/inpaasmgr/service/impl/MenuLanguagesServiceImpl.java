/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import java.util.List;

import me.anchora.inpaasmgr.dao.inpaasmgr.MenuLanguagesMapper;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenuLanguages;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenuLanguagesCriteria;
import me.anchora.inpaasmgr.service.MenuLanguagesService;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "menuLanguagesService")
public class MenuLanguagesServiceImpl implements MenuLanguagesService {

    @Autowired
    private MenuLanguagesMapper menuLanguagesMapper;

    public void insert(MenuLanguages menuLanguages) {
        menuLanguagesMapper.insert(menuLanguages);
    }

    public void update(MenuLanguages menuLanguages) {
        menuLanguagesMapper.updateByPrimaryKey(menuLanguages);
    }

    public List<MenuLanguages> queryAll() {
        return menuLanguagesMapper.selectByExample(new MenuLanguagesCriteria());
    }

    public void delete(Long id) {
        menuLanguagesMapper.deleteByPrimaryKey(id);
    }

    public List<MenuLanguages> queryMenuLanguagesByPage(MenuLanguagesCriteria criteria, RowBounds rowBounds) {
        List<MenuLanguages> result = menuLanguagesMapper.selectByExampleWithRowbounds(criteria, rowBounds);
        return result;
    }

    public Integer queryCount(MenuLanguagesCriteria criteria) {
        Integer result = menuLanguagesMapper.countByExample(criteria);
        return result;
    }

    public List<MenuLanguages> queryAllMenuLanguages(MenuLanguagesCriteria criteria) {
        List<MenuLanguages> result = menuLanguagesMapper.selectByExample(criteria);
        return result;
    }
}
