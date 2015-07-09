/**
 * www.anchora.com Inc.
 * Copyright (c) 2011 All Rights Reserved.
 */
package me.anchora.inpaasmgr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.anchora.inpaasmgr.base.BaseService;
import me.anchora.inpaasmgr.dao.ccdb.CcUsersMapper;
import me.anchora.inpaasmgr.dao.uaadb.UaaUsersMapper;
import me.anchora.inpaasmgr.entry.CommonVo;
import me.anchora.inpaasmgr.entry.ccdb.CcUsers;
import me.anchora.inpaasmgr.entry.ccdb.CcUsersCriteria;
import me.anchora.inpaasmgr.entry.uaadb.UaaUsers;
import me.anchora.inpaasmgr.entry.uaadb.UaaUsersCriteria;
import me.anchora.inpaasmgr.service.CcUsersService;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "ccUsersService")
public class CcUsersServiceImpl extends BaseService implements CcUsersService {

    @Autowired
    private CcUsersMapper ccUsersMapper;

    @Autowired
    private UaaUsersMapper uaaUsersMapper;

    public List<CcUsers> queryCcUsersByPage(CcUsersCriteria criteria, RowBounds rowBounds) {
        return ccUsersMapper.selectByExampleWithRowbounds(criteria, rowBounds);
    }

    public Integer queryCount(CcUsersCriteria criteria) {
        return ccUsersMapper.countByExample(criteria);
    }

    public List<CommonVo> selectWithOrgSpace(CommonVo commonVo, RowBounds rowBounds) {
        List<CommonVo> result = ccUsersMapper.selectWithOrgSpace(commonVo, rowBounds);
        
        List<UaaUsers> uaaUserList = uaaUsersMapper.selectByExample(new UaaUsersCriteria());
        Map<String, String> map = new HashMap<String, String>();
        for(UaaUsers uaausers : uaaUserList) {
            map.put(uaausers.getId(), uaausers.getUsername());
        }
        
        for(CommonVo common: result) {
            if(map.containsKey(common.getGuid())) {
                common.setName(map.get(common.getGuid()));
            }
        }
        
        return result;
    }

    public Integer countWithOrgSpace(CommonVo commonVo) {
        return ccUsersMapper.countWithOrgSpace(commonVo);
    }
}
