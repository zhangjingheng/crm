package com.atguigu.crm.orm;

import java.util.List;

public class Page<T> {
	private Integer pageSize=2;
	private Integer pageNo;
	private Integer totalCount;
	private List<T> content;
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		if(this.pageNo > this.getTotalPageNo()){
			this.pageNo = this.getTotalPageNo();
		}
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		if(pageNo < 1){
			this.pageNo = 1;
			return;
		}
		
		this.pageNo = pageNo;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	
	public int getTotalPageNo() {
		return (totalCount+pageSize-1)/pageSize;

	}
	
	public boolean isHasNext(){
		return this.getPageNo()<this.getTotalPageNo();
	}
	public int getNextPage(){
		if(this.isHasNext()){
			return (this.getPageNo()+1);
		}
		return this.getPageNo();
	}
	public boolean isHasPrev(){
		return this.getPageNo()>1;
	}
	public int getPrevPage(){
		if(this.isHasNext()){
			return (this.getPageNo()-1);
		}
		return this.getPageNo();
	}
	
}
