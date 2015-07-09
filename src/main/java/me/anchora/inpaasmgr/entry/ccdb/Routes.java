package me.anchora.inpaasmgr.entry.ccdb;

import java.io.Serializable;
import java.util.Date;
import me.anchora.inpaasmgr.entry.base.PageEntry;

public class Routes extends PageEntry implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.routes.id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.routes.guid
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private String guid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.routes.created_at
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.routes.updated_at
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Date updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.routes.host
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Object host;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.routes.domain_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Integer domainId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.routes.space_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Integer spaceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.routes.id
     *
     * @return the value of public.routes.id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.routes.id
     *
     * @param id the value for public.routes.id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.routes.guid
     *
     * @return the value of public.routes.guid
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public String getGuid() {
        return guid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.routes.guid
     *
     * @param guid the value for public.routes.guid
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.routes.created_at
     *
     * @return the value of public.routes.created_at
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.routes.created_at
     *
     * @param createdAt the value for public.routes.created_at
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.routes.updated_at
     *
     * @return the value of public.routes.updated_at
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.routes.updated_at
     *
     * @param updatedAt the value for public.routes.updated_at
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.routes.host
     *
     * @return the value of public.routes.host
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Object getHost() {
        return host;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.routes.host
     *
     * @param host the value for public.routes.host
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setHost(Object host) {
        this.host = host;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.routes.domain_id
     *
     * @return the value of public.routes.domain_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Integer getDomainId() {
        return domainId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.routes.domain_id
     *
     * @param domainId the value for public.routes.domain_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.routes.space_id
     *
     * @return the value of public.routes.space_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Integer getSpaceId() {
        return spaceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.routes.space_id
     *
     * @param spaceId the value for public.routes.space_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.routes
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
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
        sb.append(", host=").append(host);
        sb.append(", domainId=").append(domainId);
        sb.append(", spaceId=").append(spaceId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}