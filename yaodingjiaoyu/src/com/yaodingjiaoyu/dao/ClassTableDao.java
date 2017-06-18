package com.yaodingjiaoyu.dao;

import java.util.List;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public interface ClassTableDao {
	
	public List<ClassTable> findClassTableByCr(String time1,String time2,String campus,List<Stuff> stuff);
	
	public List<ClassTable> findClassTableByTr(String time1,String time2,String campus,String stuff);
	
	//获得学生已经排课的次数
	public int getCount(String student,String course_type);
	
	//获得学生在该天的该档是不是已经排其他课程了
	public int getCountByDay(String student,String class_time,String day_time);
	
	//判断老师在该天的该档是不是排有其他的课程类型
	public ClassTable getTeacherTable(String stuff,String day_time,String class_time);
	
	//获得老师在该档的课有没有超过课程的额度
	public int getCountByTeacher(String stuff,String class_time,String day_time);
	
	//添加课表
	public int saveClassTable(ClassTable classTable);
	
	//ba班主任查看学生课表的列表形式：科目，老师，班主任，学生，上课时间，上课时段，是否结课，校区，课程类型，年级
	public List<ClassTable> findClassTable(String subject,List<Stuff> tr,String cr,String student,String time1,String time2,String status,String class_time,String campus,String course_type,String level);
	//老师查看课表
	public List<ClassTable> findTrClassTable(String subject,String stuff,String student,String time1,String time2,String status,String class_time,String campus,String course_type,String level);
	
	//删除课程
	public boolean deleteTable(ClassTable classTable);
	
	//获得课程
	public ClassTable geTable(int id);
	
	public boolean update(ClassTable ht);
}
