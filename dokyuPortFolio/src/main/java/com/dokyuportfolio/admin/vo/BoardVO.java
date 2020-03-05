package com.dokyuportfolio.admin.vo;

import org.springframework.web.multipart.MultipartFile;

import com.dokyuportfolio.common.BasePaging;

public class BoardVO extends BasePaging{
	
	private String boardSeq;
	private String title;
	private String explanation;
	private String content;
	private String mainTopView;
	private String categoryId;
	private String instId;
	private String instDate;
	private String updtId;
	private String updtDate;
	private String categoryName;
	
	private MultipartFile thumbnail;
	private MultipartFile[] files;
	
	private String thumbnailUploadPath;
	private String fileUploadPath;
	private String baseUrl;
	
	
	
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getThumbnailUploadPath() {
		return thumbnailUploadPath;
	}
	public void setThumbnailUploadPath(String thumbnailUploadPath) {
		this.thumbnailUploadPath = thumbnailUploadPath;
	}
	public String getFileUploadPath() {
		return fileUploadPath;
	}
	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public MultipartFile getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(MultipartFile thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getMainTopView() {
		return mainTopView;
	}
	public void setMainTopView(String mainTopView) {
		this.mainTopView = mainTopView;
	}
	public String getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(String boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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
	public String getUpdtId() {
		return updtId;
	}
	public void setUpdtId(String updtId) {
		this.updtId = updtId;
	}
	public String getUpdtDate() {
		return updtDate;
	}
	public void setUpdtDate(String updtDate) {
		this.updtDate = updtDate;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
