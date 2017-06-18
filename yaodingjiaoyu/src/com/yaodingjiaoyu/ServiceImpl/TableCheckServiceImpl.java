package com.yaodingjiaoyu.ServiceImpl;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.ClassHourService;
import com.yaodingjiaoyu.Service.ClassTableService;
import com.yaodingjiaoyu.Service.TableCheckService;
import com.yaodingjiaoyu.datebase.pojo.ClassHour;

public class TableCheckServiceImpl implements TableCheckService {

	private ClassHourService classHourService;
	private ClassTableService classTableService;
	private Logger logger;

	public void setClassHourService(ClassHourService classHourService) {
		this.classHourService = classHourService;
	}

	public void setClassTableService(ClassTableService classTableService) {
		this.classTableService = classTableService;
	}

	@Override
	public boolean checkStudentClassHour(String student, String course_type) {

		try {
			ClassHour classHour = classHourService.getClassHour(student, course_type);
			if (classHour != null) {

				int last_hour = classHour.getLastHour();
				if (last_hour > 0) {
					return true;
				} else {
					return false;
				}

			} else {
				return false;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数studentId:" + student + ",course_type:"
					+ course_type + ",MESSAGE:" + e.getMessage());
			return false;
		}

	}

	@Override
	public boolean checkStudentPaike(String student, String course_type) {
		try {
			int all_hour = classHourService.getClassHour(student, course_type).getAllHour();
			// 总次数
			int times = all_hour / 3;
			// 已经排课的次数
			int paike = classTableService.getCount(student, course_type);
			if (times > paike) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数studentId:" + student + ",course_type:"
					+ course_type + ",MESSAGE:" + e.getMessage());
			return false;
		}

	}

	@Override
	public boolean checkStudentIsPaike(String student, String class_time, String day_time) {

		try {
			int tmp = classTableService.getCountByDay(student, class_time, day_time);
			if (tmp != 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数studentId:" + student + ",class_time:"
					+ class_time + ",day_time:" + day_time + ",MESSAGE:" + e.getMessage());
			return false;
		}

	}

	@Override
	public boolean checkTeacherCourseType(String stuff, String day_time, String class_time, String course_type) {

		try {
			// 这个时间档，老师的上课类型
			if (classTableService.getTeacherTable(stuff, day_time, class_time) == null) {
				return true;
			} else {
				int teacher_type = classTableService.getTeacherTable(stuff, day_time, class_time).getCourseType()
						.getPId();
				if (teacher_type == Integer.parseInt(course_type)) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数stuffId:" + stuff + ",class_time:" + class_time
					+ ",day_time:" + day_time + ",course_type:" + course_type + ",MESSAGE:" + e.getMessage());
			return false;
		}

	}

	@Override
	public boolean checkCourseCount(String stuff, String class_time, String day_time) {

		try {
			// 检查该课程类型已经排课多少次
			int count = classTableService.getCountByTeacher(stuff, class_time, day_time);

			if (classTableService.getTeacherTable(stuff, day_time, class_time) == null) {
				return true;
			} else {
				int many = classTableService.getTeacherTable(stuff, day_time, class_time).getCourseType().getMany();

				if (many > count) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数stuffId:" + stuff + ",class_time:" + class_time
					+ ",day_time:" + day_time + ",MESSAGE:" + e.getMessage());
			return false;
		}

	}

}
