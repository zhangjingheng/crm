package com.atguigu.crm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.mapper.SalesChanceMapper;
import com.atguigu.crm.orm.Page;

@Service
public class SalesChanceService {
	@Autowired
	private SalesChanceMapper salesChanceMapper;
	@Transactional(readOnly=true)
	public Page<SalesChance> getChanceList(int pageNo){
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		
		int totalCount = salesChanceMapper.getTotalCount();
		page.setTotalCount(totalCount);
		
		
		int firstIndex = (pageNo-1)*page.getPageSize()+1;
		int endIndex = firstIndex+page.getPageSize();
		 List<SalesChance> list = salesChanceMapper.getSalesChanceList(firstIndex, endIndex);
		 page.setContent(list);
		 return page;
	}
	public void save(SalesChance chance) {
		salesChanceMapper.save(chance);
		
	}
	public void delete(Integer id) {
		salesChanceMapper.delete(id);
		
	}
}
