package me.anchora.inpaasmgr.entry.inpaasmgr;

import java.io.Serializable;
import java.util.Date;
import me.anchora.inpaasmgr.entry.base.SuperMenuDetails;

public class MenuDetails extends SuperMenuDetails implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_details.id
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_details.menu_id
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private Long menuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_details.lang_id
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private Long langId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_details.menu_value
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private String menuValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_details.remark
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_details.created_at
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_details.created_by
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private String createdBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_details.updated_at
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private Date updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_details.updated_by
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private String updatedBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_details.id
     *
     * @return the value of menu_details.id
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_details.id
     *
     * @param id the value for menu_details.id
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_details.menu_id
     *
     * @return the value of menu_details.menu_id
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_details.menu_id
     *
     * @param menuId the value for menu_details.menu_id
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_details.lang_id
     *
     * @return the value of menu_details.lang_id
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public Long getLangId() {
        return langId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_details.lang_id
     *
     * @param langId the value for menu_details.lang_id
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setLangId(Long langId) {
        this.langId = langId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_details.menu_value
     *
     * @return the value of menu_details.menu_value
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public String getMenuValue() {
        return menuValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_details.menu_value
     *
     * @param menuValue the value for menu_details.menu_value
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setMenuValue(String menuValue) {
        this.menuValue = menuValue == null ? null : menuValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_details.remark
     *
     * @return the value of menu_details.remark
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_details.remark
     *
     * @param remark the value for menu_details.remark
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_details.created_at
     *
     * @return the value of menu_details.created_at
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_details.created_at
     *
     * @param createdAt the value for menu_details.created_at
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_details.created_by
     *
     * @return the value of menu_details.created_by
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_details.created_by
     *
     * @param createdBy the value for menu_details.created_by
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_details.updated_at
     *
     * @return the value of menu_details.updated_at
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_details.updated_at
     *
     * @param updatedAt the value for menu_details.updated_at
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_details.updated_by
     *
     * @return the value of menu_details.updated_by
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_details.updated_by
     *
     * @param updatedBy the value for menu_details.updated_by
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_details
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", menuId=").append(menuId);
        sb.append(", langId=").append(langId);
        sb.append(", menuValue=").append(menuValue);
        sb.append(", remark=").append(remark);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}