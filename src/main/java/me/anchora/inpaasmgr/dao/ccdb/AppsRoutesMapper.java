package me.anchora.inpaasmgr.dao.ccdb;

import java.util.List;
import me.anchora.inpaasmgr.entry.ccdb.AppsRoutes;
import me.anchora.inpaasmgr.entry.ccdb.AppsRoutesCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AppsRoutesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.apps_routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int countByExample(AppsRoutesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.apps_routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int deleteByExample(AppsRoutesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.apps_routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int insert(AppsRoutes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.apps_routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int insertSelective(AppsRoutes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.apps_routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    List<AppsRoutes> selectByExampleWithRowbounds(AppsRoutesCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.apps_routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    List<AppsRoutes> selectByExample(AppsRoutesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.apps_routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int updateByExampleSelective(@Param("record") AppsRoutes record, @Param("example") AppsRoutesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.apps_routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    int updateByExample(@Param("record") AppsRoutes record, @Param("example") AppsRoutesCriteria example);
}