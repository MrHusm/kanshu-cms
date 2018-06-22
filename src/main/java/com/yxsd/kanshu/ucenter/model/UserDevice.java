package com.yxsd.kanshu.ucenter.model;

import java.io.Serializable;
import java.util.Date;

public class UserDevice implements Serializable{

    private static final long serialVersionUID = -4942396397263947708L;

    private Long id;

    private Long userId;

    /**
     * 设备唯一标识
     */
    private String registrationId;

    /**
     * 类型 1:安卓 2:IOS
     */
    private Short type;

    private Date createDate;

    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}