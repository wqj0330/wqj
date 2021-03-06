package me.anchora.inpaasmgr.dao.inpaasmgr;

import java.util.List;
import me.anchora.inpaasmgr.entry.inpaasmgr.Login;
import me.anchora.inpaasmgr.entry.inpaasmgr.LoginCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LoginMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login
     *
     * @mbggenerated Mon Mar 09 15:18:43 CST 2015
     */
    int countByExample(LoginCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login
     *
     * @mbggenerated Mon Mar 09 15:18:43 CST 2015
     */
    int deleteByExample(LoginCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login
     *
     * @mbggenerated Mon Mar 09 15:18:43 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login
     *
     * @mbggenerated Mon Mar 09 15:18:43 CST 2015
     */
    int insert(Login record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login
     *
     * @mbggenerated Mon Mar 09 15:18:43 CST 2015
     */
    int insertSelective(Login record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login
     *
     * @mbggenerated Mon Mar 09 15:18:43 CST 2015
     */
    List<Login> selectByExampleWithRowbounds(LoginCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login
     *
     * @mbggenerated Mon Mar 09 15:18:43 CST 2015
     */
    List<Login> selectByExample(LoginCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login
     *
     * @mbggenerated Mon Mar 09 15:18:43 CST 2015
     */
    Login selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login
     *
     * @mbggenerated Mon Mar 09 15:18:43 CST 2015
     */
    int updateByExampleSelective(@Param("record") Login record, @Param("example") LoginCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login
     *
     * @mbggenerated Mon Mar 09 15:18:43 CST 2015
     */
    int updateByExample(@Param("record") Login record, @Param("example") LoginCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login
     *
     * @mbggenerated Mon Mar 09 15:18:43 CST 2015
     */
    int updateByPrimaryKeySelective(Login record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login
     *
     * @mbggenerated Mon Mar 09 15:18:43 CST 2015
     */
    int updateByPrimaryKey(Login record);
}