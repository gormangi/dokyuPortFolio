package com.dokyuportfolio.common;

public class BasePaging{

	public int nowPageNumber = 0;			//현재페이지번호
	public int blockPostCnt = 10;			//보여줄 게시물 수
	public int blockPageCnt = 10;			//보여줄 페이지 수
	public int totCnt = 0;					//총 게시물 수
	public int startPageNumber = 0;			//시작페이지
	public int endPageNumber = 0;			//끝페이지
	public int totPageNumber = 0;			//총 페이지 수
	public int startPostNumber = 0;			//조회시작number
	
	public int getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}
	public int getBlockPageCnt() {
		return blockPageCnt;
	}
	public void setBlockPageCnt(int blockPageCnt) {
		this.blockPageCnt = blockPageCnt;
	}
	public int getNowPageNumber() {
		
		if(getTotPageNumber() < nowPageNumber) {
			nowPageNumber = getTotPageNumber();
		}
		if(nowPageNumber < 1) {
			nowPageNumber = 1;
		}
		
		return nowPageNumber;
	}
	public void setNowPageNumber(int nowPageNumber) {
		this.nowPageNumber = nowPageNumber;
	}
	public int getBlockPostCnt() {
		return blockPostCnt;
	}
	public void setBlockPostCnt(int blockPostCnt) {
		this.blockPostCnt = blockPostCnt;
	}
	public int getStartPageNumber() {
		
		int a = getNowPageNumber() - 1;
		int b = a / getBlockPageCnt();
		int c = b * getBlockPageCnt() + 1;
		startPageNumber = c;
		
		return startPageNumber;
	}
	public void setStartPageNumber(int startPageNumber) {
		this.startPageNumber = startPageNumber;
	}
	public int getEndPageNumber() {
		
		int a = getStartPageNumber() + getBlockPageCnt() - 1;
		if(a > getTotPageNumber()) {
			int overPageCnt = endPageNumber - getTotPageNumber();
			int b = endPageNumber - overPageCnt;
			endPageNumber = b;
		}
		
		return endPageNumber;
	}
	public void setEndPageNumber(int endPageNumber) {
		this.endPageNumber = endPageNumber;
	}
	public int getTotPageNumber() {
		
		int a = getTotCnt() / getBlockPostCnt();
		if(getTotCnt() % getBlockPostCnt() > 0) {
			a++;
		}
		if(a < 1) {
			a = 1;
		}
		
		totPageNumber = a;
		
		return totPageNumber;
	}
	public void setTotPageNumber(int totPageNumber) {
		this.totPageNumber = totPageNumber;
	}
	
	public int getStartPostNumber() {
		
		startPostNumber = (getNowPageNumber() - 1) * getBlockPostCnt();
		
		return startPostNumber;
	}
	public void setStartPostNumber(int startPostNumber) {
		this.startPostNumber = startPostNumber;
	}
	
}
