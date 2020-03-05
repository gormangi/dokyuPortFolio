package com.dokyuportfolio.admin.vo;

import com.dokyuportfolio.common.BasePaging;

public class CategoryVO extends BasePaging{
	
	private String categorySeq;
	private String categoryId;
	private String categoryName;
	private String mainViewArea;
	private String instId;
	private String instDate;
	
	
	public String getMainViewArea() {
		return mainViewArea;
	}
	public void setMainViewArea(String mainViewArea) {
		this.mainViewArea = mainViewArea;
	}
	public String getCategorySeq() {
		return categorySeq;
	}
	public void setCategorySeq(String categorySeq) {
		this.categorySeq = categorySeq;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public String getInstDate() {
		return instDate;
	}
	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}
	
	
	
}
