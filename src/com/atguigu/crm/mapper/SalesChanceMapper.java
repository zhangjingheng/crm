package com.atguigu.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.orm.Page;

public interface SalesChanceMapper {
	@Select("select count(id) from sales_chances")
	int getTotalCount();
	//编号	客户名称	概要	联系人	联系人电话	创建时间
	@Select("SELECT * FROM "
			+ "(SELECT rownum rn, id, cust_name, title, contact, contact_tel, create_date "
			+ "FROM sales_chances "
			+ "ORDER BY id) "
			+ "WHERE rn >= #{firstIndex} AND rn < #{endIndex}")
	List<SalesChance> getSalesChanceList(@Param("firstIndex") Integer firstIndex,@Param("endIndex") Integer endIndex);
	@Insert("INSERT INTO sales_chances(id, source, cust_name, rate, title, contact, contact_tel, description, created_user_id, create_date, status) "
			+ "VALUES(crm_seq.nextval, #{source}, #{custName}, #{rate}, #{title}, #{contact}, #{contactTel}, #{description}, #{createBy.id}, #{createDate}, 1)")
	void save(SalesChance chance);
	@Delete("delete from sales_chances where id=#{id}")
	void delete(@Param("id") Integer id);
}
