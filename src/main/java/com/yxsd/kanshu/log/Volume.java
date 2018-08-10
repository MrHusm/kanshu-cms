package com.yxsd.kanshu.log;

import java.io.Serializable;
import java.util.List;

public class Volume implements Serializable{
	private String id;
	private String name;
	private List<Chapter> bookChapters;
	private int buyout;//0 不买断，1 买断

	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public List<Chapter> getBookChapters() {
		return bookChapters;
	}

	public void setBookChapters(List<Chapter> bookChapters) {
		this.bookChapters = bookChapters;
	}

	public int getBuyout() {
		return buyout;
	}

	public void setBuyout(int buyout) {
		this.buyout = buyout;
	}

	@Override
	public String toString() {
		return "Volume [id=" + id + ", name=" + name + ", chapterList="
				+ bookChapters + "]";
	}


}
