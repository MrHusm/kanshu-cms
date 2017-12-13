package com.yxsd.kanshu.product.model;

import java.io.Serializable;
import java.util.Date;

public class Channel implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 渠道号
     */
    private Integer channel;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 新增用户数量系数
     */
    private Double dnuRatio;

    /**
     * 日活用户数量系数
     */
    private Double dauRatio;

    /**
     * 收入系数
     */
    private Double moneyRatio;

    /**
     * 渠道类型 1：安卓 2：ios 3：H5
     */
    private Short type;

    private String channels;

    private Date createDate;

    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
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

    public Double getDnuRatio() {
        return dnuRatio;
    }

    public void setDnuRatio(Double dnuRatio) {
        this.dnuRatio = dnuRatio;
    }

    public Double getDauRatio() {
        return dauRatio;
    }

    public void setDauRatio(Double dauRatio) {
        this.dauRatio = dauRatio;
    }

    public Double getMoneyRatio() {
        return moneyRatio;
    }

    public void setMoneyRatio(Double moneyRatio) {
        this.moneyRatio = moneyRatio;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }
}