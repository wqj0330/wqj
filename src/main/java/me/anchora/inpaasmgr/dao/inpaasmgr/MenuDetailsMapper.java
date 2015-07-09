package me.anchora.inpaasmgr.dao.inpaasmgr;

import java.util.List;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenuDetails;
import me.anchora.inpaasmgr.entry.inpaasmgr.MenuDetailsCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MenuDetailsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int countByExample(MenuDetailsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int deleteByExample(MenuDetailsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int insert(MenuDetails record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int insertSelective(MenuDetails record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    List<MenuDetails> selectByExampleWithRowbounds(MenuDetailsCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    List<MenuDetails> selectByExample(MenuDetailsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    MenuDetails selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int updateByExampleSelective(@Param("record") MenuDetails record, @Param("example") MenuDetailsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int updateByExample(@Param("record") MenuDetails record, @Param("example") MenuDetailsCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int updateByPrimaryKeySelective(MenuDetails record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int updateByPrimaryKey(MenuDetails record);

	List<MenuDetails> selectByExampleWithOtherWithRowbounds(
			MenuDetails menuDetails, RowBounds rowBounds);

	Integer countByExampleWithOther(MenuDetails menuDetails);
}