package com.yxsd.kanshu.portal.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo on 2018/6/22.
 */
public class DriveType implements Serializable {

    private static final long serialVersionUID = -1;

    private Long id;

    /**
     * 榜单类型
     */
    private Integer type;

    /**
     * 榜单名称
     */
    private String name;

    /**
     * 榜单描述
     */
    private String comment;

    private Date updateDate;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
