package com.yxsd.kanshu.product.model;

import java.io.Serializable;
import java.util.Date;

public class ChannelData implements Serializable{
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
     * 日期
     */
    private String day;

    /**
     * 新增用户数量
     */
    private Integer dnu;

    /**
     * 新增用户数量固定值
     */
    private Integer dnuFixed;

    /**
     * 渠道显示新增用户数量
     */
    private Integer dnuShow;

    /**
     * 日活用户数量
     */
    private Integer dau;

    /**
     * 日活用户数量固定值
     */
    private Integer dauFixed;

    /**
     * 渠道显示日活用户数量
     */
    private Integer dauShow;

    /**
     * 收入（分）
     */
    private Integer money;

    /**
     * 充值次数
     */
    private Integer chargeNum;

    /**
     * 收入固定值
     */
    private Integer moneyFixed;

    /**
     * 渠道显示收入
     */
    private Integer moneyShow;

    /**
     * 新用户次日留存
     */
    private String oneDayRetention;

    /**
     * 发布状态 0：未发布 1：已发布
     */
    private Integer status;

    private String channels;

    private String channelsOne;

    private String dayStart;

    private String dayEnd;

    /**
     * 排序字段
     */
    private String orderColumn;

    /**
     * 0：降序 1：升序
     */
    private Integer orderType;

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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getDnu() {
        return dnu;
    }

    public void setDnu(Integer dnu) {
        this.dnu = dnu;
    }

    public Integer getDnuFixed() {
        return dnuFixed;
    }

    public void setDnuFixed(Integer dnuFixed) {
        this.dnuFixed = dnuFixed;
    }

    public Integer getDau() {
        return dau;
    }

    public void setDau(Integer dau) {
        this.dau = dau;
    }

    public Integer getDauFixed() {
        return dauFixed;
    }

    public void setDauFixed(Integer dauFixed) {
        this.dauFixed = dauFixed;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getMoneyFixed() {
        return moneyFixed;
    }

    public void setMoneyFixed(Integer moneyFixed) {
        this.moneyFixed = moneyFixed;
    }

    public String getOneDayRetention() {
        return oneDayRetention;
    }

    public void setOneDayRetention(String oneDayRetention) {
        this.oneDayRetention = oneDayRetention;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public String getChannelsOne() {
        return channelsOne;
    }

    public void setChannelsOne(String channelsOne) {
        this.channelsOne = channelsOne;
    }

    public String getDayStart() {
        return dayStart;
    }

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }

    public String getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }

    public Integer getDnuShow() {
        return dnuShow;
    }

    public void setDnuShow(Integer dnuShow) {
        this.dnuShow = dnuShow;
    }

    public Integer getDauShow() {
        return dauShow;
    }

    public void setDauShow(Integer dauShow) {
        this.dauShow = dauShow;
    }

    public Integer getMoneyShow() {
        return moneyShow;
    }

    public void setMoneyShow(Integer moneyShow) {
        this.moneyShow = moneyShow;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getChargeNum() {
        return chargeNum;
    }

    public void setChargeNum(Integer chargeNum) {
        this.chargeNum = chargeNum;
    }
}