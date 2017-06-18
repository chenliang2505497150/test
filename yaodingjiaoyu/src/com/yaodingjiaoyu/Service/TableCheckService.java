package com.yaodingjiaoyu.Service;

public interface TableCheckService {
	
	//检查学生的课时是否符合要求
	public boolean checkStudentClassHour(String student,String course_type);
	
	//检查有没有课可以排
	public boolean checkStudentPaike(String student,String course_type);
	
	//获得学生在该天的该档是不是已经排其他课程了
	public boolean checkStudentIsPaike(String student,String class_time,String day_time);
	
	//判断老师在该天的该档是不是排有其他的课程类型
	public boolean checkTeacherCourseType(String stuff,String day_time,String class_time,String course_type);
	
	//获得老师在该档的课有没有超过课程的额度
	public boolean checkCourseCount(String stuff,String class_time,String day_time);
}
