package com.yaodingjiaoyu.dao;

import com.yaodingjiaoyu.datebase.pojo.CourseType;

public interface CourseTypeDao {
	
	public int save(CourseType courseType);
	
	public CourseType getCourseType(int id);
}
