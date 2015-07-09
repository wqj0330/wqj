package me.anchora.inpaasmgr.entry.ccdb;

import java.io.Serializable;
import java.util.Date;
import me.anchora.inpaasmgr.entry.base.PageEntry;

public class CcUsers extends PageEntry implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.guid
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private String guid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.created_at
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.updated_at
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Date updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.default_space_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Integer defaultSpaceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.admin
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Boolean admin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.active
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private Boolean active;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table public.users
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.id
     *
     * @return the value of public.users.id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.id
     *
     * @param id the value for public.users.id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.guid
     *
     * @return the value of public.users.guid
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public String getGuid() {
        return guid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.guid
     *
     * @param guid the value for public.users.guid
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.created_at
     *
     * @return the value of public.users.created_at
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.created_at
     *
     * @param createdAt the value for public.users.created_at
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.updated_at
     *
     * @return the value of public.users.updated_at
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.updated_at
     *
     * @param updatedAt the value for public.users.updated_at
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.default_space_id
     *
     * @return the value of public.users.default_space_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Integer getDefaultSpaceId() {
        return defaultSpaceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.default_space_id
     *
     * @param defaultSpaceId the value for public.users.default_space_id
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setDefaultSpaceId(Integer defaultSpaceId) {
        this.defaultSpaceId = defaultSpaceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.admin
     *
     * @return the value of public.users.admin
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Boolean getAdmin() {
        return admin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.admin
     *
     * @param admin the value for public.users.admin
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.active
     *
     * @return the value of public.users.active
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.active
     *
     * @param active the value for public.users.active
     *
     * @mbggenerated Tue Mar 18 16:01:46 CST 2014
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.users
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
        sb.append(", defaultSpaceId=").append(defaultSpaceId);
        sb.append(", admin=").append(admin);
        sb.append(", active=").append(active);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}