package com.yaodingjiaoyu.Service;

import com.yaodingjiaoyu.datebase.pojo.ClassHour;

public interface ClassHourService {
	public int saveBy(int student,int course_type,int all_hour,int last_hour);
	public int save(ClassHour classhour);
	public ClassHour getClassHour(String student,String course_type);
	public boolean update(ClassHour classhour);
}
