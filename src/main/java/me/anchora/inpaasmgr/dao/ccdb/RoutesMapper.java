package me.anchora.inpaasmgr.dao.ccdb;

import java.util.List;
import me.anchora.inpaasmgr.entry.ccdb.Routes;
import me.anchora.inpaasmgr.entry.ccdb.RoutesCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoutesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int countByExample(RoutesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int deleteByExample(RoutesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int insert(Routes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int insertSelective(Routes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    List<Routes> selectByExampleWithRowbounds(RoutesCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    List<Routes> selectByExample(RoutesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    Routes selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int updateByExampleSelective(@Param("record") Routes record, @Param("example") RoutesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int updateByExample(@Param("record") Routes record, @Param("example") RoutesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int updateByPrimaryKeySelective(Routes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int updateByPrimaryKey(Routes record);
}