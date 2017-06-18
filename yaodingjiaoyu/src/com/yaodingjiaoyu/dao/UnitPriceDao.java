package com.yaodingjiaoyu.dao;

import com.yaodingjiaoyu.datebase.pojo.CampusPrice;

public interface UnitPriceDao {
	public CampusPrice GetUnitPriceBy(int campus,int course_type);
}
