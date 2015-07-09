/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;

import me.anchora.inpaasmgr.entry.inpaasmgr.MenuDetails;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenuDetailsCriteria;

import org.apache.ibatis.session.RowBounds;

public interface MenuDetailsService {
    public void insert(MenuDetails menuDetails);

    public void update(MenuDetails menuDetails);

    public List<MenuDetails> queryAll();

    public void delete(Long id);

    public List<MenuDetails> queryMenuDetailsWithOtherByPage(MenuDetails menuDetails, RowBounds rowBounds);

    public Integer queryCount(MenuDetails menuDetails);
    public List<MenuDetails> queryAllMenuDetails(MenuDetailsCriteria criteria);
}
