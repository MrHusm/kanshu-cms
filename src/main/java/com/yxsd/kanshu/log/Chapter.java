package com.yxsd.kanshu.log;

import java.io.Serializable;

/**
 * 章节实体类
 */
public class Chapter implements Serializable {
    private static final long serialVersionUID = 1L;

    // 章节id（因使用了Gson,不能更改）
    private String i;
    // 章节名（因使用了Gson,不能更改）
    private String n;
    private String bookId;
    private String isVip;
    private boolean isFree = false; // 是否免费
    private String payUrl;
    // 价格
    private double price;
    // 余额
    private double balance;
    private String content;
    private Chapter preChapter;
    private Chapter nextChapter;

    public String getId() {
        return i;
    }

    public void setId(String id) {
        this.i = id;
    }

    public String getName() {
        return n;
    }

    public void setName(String name) {
        this.n = name;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    /**
     * @desc 判断是否是Vip章节
     * @author zxing
     * @time 2016-09-02
     * @version V
     */
    public Boolean isVipChapter() {
        return "1".equals(isVip);
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Chapter getPreChapter() {
        return preChapter;
    }

    public void setPreChapter(Chapter preChapter) {
        this.preChapter = preChapter;
    }

    public Chapter getNextChapter() {
        return nextChapter;
    }

    public void setNextChapter(Chapter nextChapter) {
        this.nextChapter = nextChapter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o != null && o instanceof Chapter) {
            Chapter c = (Chapter) o;
            if (null != c.i) {
                return c.i.equals(i);
            }
        }
        return false;
    }
}
