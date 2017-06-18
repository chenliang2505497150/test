package com.yaodingjiaoyu.dao;

import java.util.List;

import com.yaodingjiaoyu.datebase.pojo.Stuff;

public interface SearchStuffDao {
	public List<Stuff> findBySdRequest(String name, String job, String status, String part_time, String campus);
}
