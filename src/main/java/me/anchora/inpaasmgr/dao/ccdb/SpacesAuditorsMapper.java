package me.anchora.inpaasmgr.dao.ccdb;

import java.util.List;
import me.anchora.inpaasmgr.entry.ccdb.SpacesAuditors;
import me.anchora.inpaasmgr.entry.ccdb.SpacesAuditorsCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SpacesAuditorsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.spaces_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int countByExample(SpacesAuditorsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.spaces_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int deleteByExample(SpacesAuditorsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.spaces_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int insert(SpacesAuditors record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.spaces_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int insertSelective(SpacesAuditors record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.spaces_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    List<SpacesAuditors> selectByExampleWithRowbounds(SpacesAuditorsCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.spaces_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    List<SpacesAuditors> selectByExample(SpacesAuditorsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.spaces_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int updateByExampleSelective(@Param("record") SpacesAuditors record, @Param("example") SpacesAuditorsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.spaces_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int updateByExample(@Param("record") SpacesAuditors record, @Param("example") SpacesAuditorsCriteria example);
}