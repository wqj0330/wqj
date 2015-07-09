/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import java.util.List;

import me.anchora.inpaasmgr.dao.inpaasmgr.MenuDetailsMapper;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenuDetails;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenuDetailsCriteria;
import me.anchora.inpaasmgr.service.MenuDetailsService;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "menuDetailsService")
public class MenuDetailsServiceImpl implements MenuDetailsService {

    @Autowired
    private MenuDetailsMapper menuDetailsMapper;

    public void insert(MenuDetails menuDetails) {
        menuDetailsMapper.insert(menuDetails);
    }

    public void update(MenuDetails menuDetails) {
        menuDetailsMapper.updateByPrimaryKey(menuDetails);
    }

    public List<MenuDetails> queryAll() {
        return menuDetailsMapper.selectByExample(new MenuDetailsCriteria());
    }

    public void delete(Long id) {
        menuDetailsMapper.deleteByPrimaryKey(id);
    }

    public List<MenuDetails> queryMenuDetailsWithOtherByPage(MenuDetails menuDetails, RowBounds rowBounds) {
        List<MenuDetails> result = menuDetailsMapper.selectByExampleWithOtherWithRowbounds(menuDetails, rowBounds);
        return result;
    }

    public Integer queryCount(MenuDetails menuDetails) {
        Integer result = menuDetailsMapper.countByExampleWithOther(menuDetails);
        return result;
    }

    public List<MenuDetails> queryAllMenuDetails(MenuDetailsCriteria criteria) {
        List<MenuDetails> result = menuDetailsMapper.selectByExample(criteria);
        return result;
    }
}
