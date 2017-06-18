package com.yaodingjiaoyu.Service;

import java.util.Date;
import java.util.List;

import com.yaodingjiaoyu.datebase.pojo.ClassContent;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public interface ClassContentService {
	public List<ClassContent> findClassContentByAdmin(String campus, String time1, String time2, String stuff_id,String name);
	public List<ClassContent> findClassContentByCr(String campus,String time1,String time2,String Cr,String stuff_id,String name);
	public List<ClassContent> findCrContentByStudentId(String id,String stuff,String campus);
	public int addContent(int id,Date insert_time,String contents,Stuff stuff);
	public List<ClassContent> findTrContentByStudentId(String id,String stuff,String campus);
}
