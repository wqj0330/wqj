package me.anchora.inpaasmgr.service;

import java.util.List;

import me.anchora.inpaasmgr.entry.ccdb.Spaces;

import org.apache.ibatis.session.RowBounds;

public interface SpacesService {
    public List<Spaces> querySpacesByPage(Spaces spaces, RowBounds rowBounds);

    public Integer queryCount(Spaces spaces);
    
    public void deleteSpace(Spaces spaces);
}
