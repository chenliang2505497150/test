package com.yaodingjiaoyu.ServiceImpl;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.ClassHourService;
import com.yaodingjiaoyu.Service.CourseTypeService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.dao.ClassHourDao;
import com.yaodingjiaoyu.datebase.pojo.ClassHour;
import com.yaodingjiaoyu.datebase.pojo.CourseType;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class ClassHourServiceImpl implements ClassHourService {
	private ClassHourDao classHourDao;
	private UserService userService;
	private CourseTypeService courseTypeService;
	private Logger logger;

	public void setCourseTypeService(CourseTypeService courseTypeService) {
		this.courseTypeService = courseTypeService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setClassHourDao(ClassHourDao classHourDao) {
		this.classHourDao = classHourDao;
	}

	@Override
	public int saveBy(int student, int course_type, int all_hour, int last_hour) {

		try {
			ClassHour classHour = new ClassHour();
			Student studentTemp = (Student) userService.getUser(student);
			CourseType courseTypeTemp = courseTypeService.getCourseType(course_type);
			classHour.setStudent(studentTemp);
			classHour.setCourseType(courseTypeTemp);
			classHour.setAllHour(all_hour);
			classHour.setLastHour(last_hour);
			return classHourDao.save(classHour);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数student:" + student + ",course_type:"
					+ course_type + ",all_hour:" + all_hour + ",last_hour:" + last_hour + ",MESSAGE:" + e.getMessage());
			return 0;
		}
	}

	@Override
	public int save(ClassHour classhour) {
		return classHourDao.save(classhour);
	}

	@Override
	public ClassHour getClassHour(String student, String course_type) {

		return classHourDao.getClassHour(student, course_type);
	}

	@Override
	public boolean update(ClassHour classhour) {

		return classHourDao.update(classhour);
	}

}
