package me.anchora.inpaasmgr.service.impl;

import java.util.List;

import me.anchora.inpaasmgr.base.BaseService;
import me.anchora.inpaasmgr.dao.ccdb.OrganizationsMapper;
import me.anchora.inpaasmgr.dao.ccdb.SpacesMapper;
import me.anchora.inpaasmgr.entry.ccdb.Spaces;
import me.anchora.inpaasmgr.msg.MsgEnum;
import me.anchora.inpaasmgr.service.SpacesService;
import me.anchora.inpaasmgr.utils.CloudFoundryClientUtil;
import me.anchora.inpaasmgr.utils.SystemUtil;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "spaceService")
public class SpacesServiceImpl extends BaseService implements SpacesService {

    private static Logger logger = Logger.getLogger(SpacesServiceImpl.class);

    @Autowired
    private SpacesMapper spacesMapper;

    @Autowired
    private OrganizationsMapper organizationsMapper;

    public List<Spaces> querySpacesByPage(Spaces space, RowBounds rowBounds) {
        List<Spaces> result = spacesMapper.selectWithOrganizationByExampleWithRowbounds(space, rowBounds);
        return result;

    }

    public Integer queryCount(Spaces spaces) {
        return spacesMapper.countWithOrg(spaces);
    }

    public void deleteSpace(Spaces spaces) {
        if (spaces == null || spaces.getGuid() == null || spaces.getGuid().length() == 0) {
            SystemUtil.throwException(MsgEnum.SPACES_10002.getCode());
        }

        CloudFoundryClientUtil.setToken(cloudFoundryClient, getAccesstokens());
        try {
            cloudFoundryClient.deleteSpace(spaces.getGuid());
        } catch (Exception e) {
            logger.info(e);
            CloudFoundryClientUtil.setToken(cloudFoundryClient, getAccesstokens(true));
            cloudFoundryClient.deleteSpace(spaces.getGuid());
        }
    }
}
