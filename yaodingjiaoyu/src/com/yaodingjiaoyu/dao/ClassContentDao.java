package com.yaodingjiaoyu.dao;

import java.util.List;
import com.yaodingjiaoyu.datebase.pojo.ClassContent;

public interface ClassContentDao {
	//查找课堂跟踪记录
	public List<ClassContent> findClassContentByAdmin(String campus,String time1,String time2,String stuff_id,String name);
	
	//查找课堂跟踪记录
	public List<ClassContent> findClassContentByCr(String campus,String time1,String time2,String Cr,String stuff_id,String name);
	
	public List<ClassContent> findCrContentByStudentId(String id,String stuff,String campus);
	
	public int addContent(ClassContent classContent);
	
	public List<ClassContent> findTrContentByStudentId(String id,String stuff,String campus);
}
