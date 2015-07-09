package me.anchora.inpaasmgr.dao.ccdb;

import java.util.List;
import me.anchora.inpaasmgr.entry.ccdb.QuotaDefinitions;
import me.anchora.inpaasmgr.entry.ccdb.QuotaDefinitionsCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface QuotaDefinitionsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    int countByExample(QuotaDefinitionsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    int deleteByExample(QuotaDefinitionsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    int insert(QuotaDefinitions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    int insertSelective(QuotaDefinitions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    List<QuotaDefinitions> selectByExampleWithRowbounds(QuotaDefinitionsCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    List<QuotaDefinitions> selectByExample(QuotaDefinitionsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    QuotaDefinitions selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    int updateByExampleSelective(@Param("record") QuotaDefinitions record, @Param("example") QuotaDefinitionsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    int updateByExample(@Param("record") QuotaDefinitions record, @Param("example") QuotaDefinitionsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    int updateByPrimaryKeySelective(QuotaDefinitions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    int updateByPrimaryKey(QuotaDefinitions record);
}