package me.anchora.inpaasmgr.entry.ccdb;

import java.io.Serializable;
import me.anchora.inpaasmgr.entry.base.PageEntry;

public class OrganizationsManagers extends PageEntry implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.organizations_managers.organization_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Integer organizationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.organizations_managers.user_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table public.organizations_managers
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.organizations_managers.organization_id
     *
     * @return the value of public.organizations_managers.organization_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Integer getOrganizationId() {
        return organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.organizations_managers.organization_id
     *
     * @param organizationId the value for public.organizations_managers.organization_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.organizations_managers.user_id
     *
     * @return the value of public.organizations_managers.user_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.organizations_managers.user_id
     *
     * @param userId the value for public.organizations_managers.user_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.organizations_managers
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", organizationId=").append(organizationId);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}