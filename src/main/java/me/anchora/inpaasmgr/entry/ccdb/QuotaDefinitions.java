package me.anchora.inpaasmgr.entry.ccdb;

import java.io.Serializable;
import java.util.Date;
import me.anchora.inpaasmgr.entry.base.PageEntry;

public class QuotaDefinitions extends PageEntry implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.quota_definitions.id
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.quota_definitions.guid
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    private String guid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.quota_definitions.created_at
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.quota_definitions.updated_at
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    private Date updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.quota_definitions.name
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    private Object name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.quota_definitions.non_basic_services_allowed
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    private Boolean nonBasicServicesAllowed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.quota_definitions.total_services
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    private Integer totalServices;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.quota_definitions.memory_limit
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    private Integer memoryLimit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.quota_definitions.total_routes
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    private Integer totalRoutes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.quota_definitions.id
     *
     * @return the value of public.quota_definitions.id
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.quota_definitions.id
     *
     * @param id the value for public.quota_definitions.id
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.quota_definitions.guid
     *
     * @return the value of public.quota_definitions.guid
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public String getGuid() {
        return guid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.quota_definitions.guid
     *
     * @param guid the value for public.quota_definitions.guid
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.quota_definitions.created_at
     *
     * @return the value of public.quota_definitions.created_at
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.quota_definitions.created_at
     *
     * @param createdAt the value for public.quota_definitions.created_at
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.quota_definitions.updated_at
     *
     * @return the value of public.quota_definitions.updated_at
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.quota_definitions.updated_at
     *
     * @param updatedAt the value for public.quota_definitions.updated_at
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.quota_definitions.name
     *
     * @return the value of public.quota_definitions.name
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public Object getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.quota_definitions.name
     *
     * @param name the value for public.quota_definitions.name
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public void setName(Object name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.quota_definitions.non_basic_services_allowed
     *
     * @return the value of public.quota_definitions.non_basic_services_allowed
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public Boolean getNonBasicServicesAllowed() {
        return nonBasicServicesAllowed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.quota_definitions.non_basic_services_allowed
     *
     * @param nonBasicServicesAllowed the value for public.quota_definitions.non_basic_services_allowed
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public void setNonBasicServicesAllowed(Boolean nonBasicServicesAllowed) {
        this.nonBasicServicesAllowed = nonBasicServicesAllowed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.quota_definitions.total_services
     *
     * @return the value of public.quota_definitions.total_services
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public Integer getTotalServices() {
        return totalServices;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.quota_definitions.total_services
     *
     * @param totalServices the value for public.quota_definitions.total_services
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public void setTotalServices(Integer totalServices) {
        this.totalServices = totalServices;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.quota_definitions.memory_limit
     *
     * @return the value of public.quota_definitions.memory_limit
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.quota_definitions.memory_limit
     *
     * @param memoryLimit the value for public.quota_definitions.memory_limit
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.quota_definitions.total_routes
     *
     * @return the value of public.quota_definitions.total_routes
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public Integer getTotalRoutes() {
        return totalRoutes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.quota_definitions.total_routes
     *
     * @param totalRoutes the value for public.quota_definitions.total_routes
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    public void setTotalRoutes(Integer totalRoutes) {
        this.totalRoutes = totalRoutes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.quota_definitions
     *
     * @mbggenerated Tue May 27 10:15:59 CST 2014
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", guid=").append(guid);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", name=").append(name);
        sb.append(", nonBasicServicesAllowed=").append(nonBasicServicesAllowed);
        sb.append(", totalServices=").append(totalServices);
        sb.append(", memoryLimit=").append(memoryLimit);
        sb.append(", totalRoutes=").append(totalRoutes);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}