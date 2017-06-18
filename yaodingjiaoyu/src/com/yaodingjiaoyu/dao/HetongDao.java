package com.yaodingjiaoyu.dao;

import java.util.List;
import com.yaodingjiaoyu.datebase.pojo.Hetong;

public interface HetongDao {
	public int save(Hetong ht);
	public List<Hetong> findHetongByAdmin(String hetong_num,String name,String school,String level,String hetong_type,String campus,String stuff,String course_type,String time1,String time2);
	public Hetong findById(int id);
	public boolean UpdateHetong(Hetong hetong); 
	public boolean DeleteHetong(Hetong hetong);
	public double findHetong_Sum_Money(String hetong_num,String name,String school,String level,String hetong_type,String campus,String stuff,String course_type,String time1,String time2);
	
}
