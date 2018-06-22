package com.yxsd.kanshu.ucenter.model;

import java.io.Serializable;
import java.util.Date;

public class UserWelfare implements Serializable{

    private static final long serialVersionUID = -6793593226710417281L;

    private Long id;

    /**
     * 新用户福利类型 1：领VIP 2：签到
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