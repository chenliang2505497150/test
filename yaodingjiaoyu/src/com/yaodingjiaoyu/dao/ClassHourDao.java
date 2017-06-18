package com.yaodingjiaoyu.dao;

import com.yaodingjiaoyu.datebase.pojo.ClassHour;

public interface ClassHourDao {
	public int save(ClassHour ht);
	
	public boolean update(ClassHour ht);
	
	public ClassHour getClassHour(String student,String course_type);
}
