package me.anchora.inpaasmgr.entry.inpaasmgr;

import java.io.Serializable;
import java.util.Date;
import me.anchora.inpaasmgr.entry.base.PageEntry;

public class MenuLanguages extends PageEntry implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_languages.id
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_languages.lang_name
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private String langName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_languages.remark
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_languages.created_at
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_languages.created_by
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private String createdBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_languages.updated_at
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private Date updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_languages.updated_by
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private String updatedBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table menu_languages
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_languages.id
     *
     * @return the value of menu_languages.id
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_languages.id
     *
     * @param id the value for menu_languages.id
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_languages.lang_name
     *
     * @return the value of menu_languages.lang_name
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public String getLangName() {
        return langName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_languages.lang_name
     *
     * @param langName the value for menu_languages.lang_name
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setLangName(String langName) {
        this.langName = langName == null ? null : langName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_languages.remark
     *
     * @return the value of menu_languages.remark
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_languages.remark
     *
     * @param remark the value for menu_languages.remark
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_languages.created_at
     *
     * @return the value of menu_languages.created_at
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_languages.created_at
     *
     * @param createdAt the value for menu_languages.created_at
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_languages.created_by
     *
     * @return the value of menu_languages.created_by
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_languages.created_by
     *
     * @param createdBy the value for menu_languages.created_by
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_languages.updated_at
     *
     * @return the value of menu_languages.updated_at
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_languages.updated_at
     *
     * @param updatedAt the value for menu_languages.updated_at
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_languages.updated_by
     *
     * @return the value of menu_languages.updated_by
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_languages.updated_by
     *
     * @param updatedBy the value for menu_languages.updated_by
     *
     * @mbggenerated Thu Mar 12 15:15:43 CST 2015
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_languages
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
        sb.append(", langName=").append(langName);
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