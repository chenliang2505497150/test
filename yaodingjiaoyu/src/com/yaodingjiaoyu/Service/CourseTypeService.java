package com.yaodingjiaoyu.Service;

import com.yaodingjiaoyu.datebase.pojo.CourseType;

public interface CourseTypeService {
	public int save(CourseType courseType);
	public CourseType getCourseType(int id);
}
