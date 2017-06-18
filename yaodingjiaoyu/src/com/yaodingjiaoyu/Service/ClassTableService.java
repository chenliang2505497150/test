package com.yaodingjiaoyu.Service;

import java.util.Date;
import java.util.List;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public interface ClassTableService {

	public List<ClassTable> findClassTableByCr(String time1, String time2, String campus, List<Stuff> stuff);
	
	public List<ClassTable> findClassTable(String subject,List<Stuff> tr,String cr,String student,String time1,String time2,String status,String class_time,String campus,String course_type,String level);
	
	public int getCount(String student, String course_type);
	
	public int getCountByDay(String student, String class_time, String day_time);
	
	//判断老师在该天的该档是不是排有其他的课程类型
	public ClassTable getTeacherTable(String stuff,String day_time,String class_time);
		
	//获得老师在该档的课有没有超过课程的额度
	public int getCountByTeacher(String stuff,String class_time,String day_time);
	
	public int saveClassTable(Stuff stuff,Student student,Date day_time,String class_time,String course_type);
	
	//删除课程
	public boolean deleteTable(int id);
	
	public ClassTable geTable(int id);
	
	public boolean update(ClassTable classTable);
	
	public List<ClassTable> findClassTableByTr(String time1, String time2, String campus, String stuff);
	
	public List<ClassTable> findTrClassTable(String subject,String stuff,String student,String time1,String time2,String status,String class_time,String campus,String course_type,String level);
}
