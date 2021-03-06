package me.anchora.inpaasmgr.dao.inpaasmgr;

import java.util.List;
import me.anchora.inpaasmgr.entry.inpaasmgr.RoleMenus;
import me.anchora.inpaasmgr.entry.inpaasmgr.RoleMenusCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoleMenusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menus
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int countByExample(RoleMenusCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menus
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int deleteByExample(RoleMenusCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menus
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menus
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int insert(RoleMenus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menus
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int insertSelective(RoleMenus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menus
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    List<RoleMenus> selectByExampleWithRowbounds(RoleMenusCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menus
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    List<RoleMenus> selectByExample(RoleMenusCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menus
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    RoleMenus selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menus
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int updateByExampleSelective(@Param("record") RoleMenus record, @Param("example") RoleMenusCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menus
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int updateByExample(@Param("record") RoleMenus record, @Param("example") RoleMenusCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menus
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int updateByPrimaryKeySelective(RoleMenus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menus
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    int updateByPrimaryKey(RoleMenus record);

	List<RoleMenus> selectByExampleWithOtherWithRowbounds(RoleMenus roleMenus,
			RowBounds rowBounds);

	Integer countByExampleWithOther(RoleMenus roleMenus);
}