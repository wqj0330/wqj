/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service;

import java.util.List;

import me.anchora.inpaasmgr.entry.CommonVo;
import me.anchora.inpaasmgr.entry.ccdb.CcUsers;
import me.anchora.inpaasmgr.entry.ccdb.CcUsersCriteria;

import org.apache.ibatis.session.RowBounds;

public interface CcUsersService {
    public List<CcUsers> queryCcUsersByPage(CcUsersCriteria criteria, RowBounds rowBounds);

    public Integer queryCount(CcUsersCriteria criteria);
    
    public Integer countWithOrgSpace(CommonVo commonVo);
    
    public List<CommonVo> selectWithOrgSpace(CommonVo commonVo, RowBounds rowBounds);
}
