package me.anchora.inpaasmgr.dao.inpaasmgr;

import java.util.List;
import me.anchora.inpaasmgr.entry.inpaasmgr.AppConfigs;
import me.anchora.inpaasmgr.entry.inpaasmgr.AppConfigsCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AppConfigsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_configs
     *
     * @mbggenerated Wed Dec 24 15:19:46 CST 2014
     */
    int countByExample(AppConfigsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_configs
     *
     * @mbggenerated Wed Dec 24 15:19:46 CST 2014
     */
    int deleteByExample(AppConfigsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_configs
     *
     * @mbggenerated Wed Dec 24 15:19:46 CST 2014
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_configs
     *
     * @mbggenerated Wed Dec 24 15:19:46 CST 2014
     */
    int insert(AppConfigs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_configs
     *
     * @mbggenerated Wed Dec 24 15:19:46 CST 2014
     */
    int insertSelective(AppConfigs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_configs
     *
     * @mbggenerated Wed Dec 24 15:19:46 CST 2014
     */
    List<AppConfigs> selectByExampleWithRowbounds(AppConfigsCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_configs
     *
     * @mbggenerated Wed Dec 24 15:19:46 CST 2014
     */
    List<AppConfigs> selectByExample(AppConfigsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_configs
     *
     * @mbggenerated Wed Dec 24 15:19:46 CST 2014
     */
    AppConfigs selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_configs
     *
     * @mbggenerated Wed Dec 24 15:19:46 CST 2014
     */
    int updateByExampleSelective(@Param("record") AppConfigs record, @Param("example") AppConfigsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_configs
     *
     * @mbggenerated Wed Dec 24 15:19:46 CST 2014
     */
    int updateByExample(@Param("record") AppConfigs record, @Param("example") AppConfigsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_configs
     *
     * @mbggenerated Wed Dec 24 15:19:46 CST 2014
     */
    int updateByPrimaryKeySelective(AppConfigs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_configs
     *
     * @mbggenerated Wed Dec 24 15:19:46 CST 2014
     */
    int updateByPrimaryKey(AppConfigs record);

	void createTable();
}