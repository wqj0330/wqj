package me.anchora.inpaasmgr.dao.ccdb;

import java.util.List;
import me.anchora.inpaasmgr.entry.ccdb.OrganizationsAuditors;
import me.anchora.inpaasmgr.entry.ccdb.OrganizationsAuditorsCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OrganizationsAuditorsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.organizations_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int countByExample(OrganizationsAuditorsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.organizations_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int deleteByExample(OrganizationsAuditorsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.organizations_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int insert(OrganizationsAuditors record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.organizations_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int insertSelective(OrganizationsAuditors record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.organizations_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    List<OrganizationsAuditors> selectByExampleWithRowbounds(OrganizationsAuditorsCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.organizations_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    List<OrganizationsAuditors> selectByExample(OrganizationsAuditorsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.organizations_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int updateByExampleSelective(@Param("record") OrganizationsAuditors record, @Param("example") OrganizationsAuditorsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.organizations_auditors
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int updateByExample(@Param("record") OrganizationsAuditors record, @Param("example") OrganizationsAuditorsCriteria example);
}