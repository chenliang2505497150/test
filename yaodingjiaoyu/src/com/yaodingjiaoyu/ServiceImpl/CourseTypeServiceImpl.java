package com.yaodingjiaoyu.ServiceImpl;

import com.yaodingjiaoyu.Service.CourseTypeService;
import com.yaodingjiaoyu.dao.CourseTypeDao;
import com.yaodingjiaoyu.datebase.pojo.CourseType;

public class CourseTypeServiceImpl implements CourseTypeService{
private CourseTypeDao courseTypeDao;

	public void setCourseTypeDao(CourseTypeDao courseTypeDao) {
	this.courseTypeDao = courseTypeDao;
}

	@Override
	public int save(CourseType courseType) {

		return courseTypeDao.save(courseType);
	}

	@Override
	public CourseType getCourseType(int id) {

		return courseTypeDao.getCourseType(id);
	}

}
