package com.yaodingjiaoyu.Service;

import java.util.List;
import com.yaodingjiaoyu.datebase.pojo.Hetong;

public interface HetongService {
	public int saveHetong(int hetong_type,int student,String hetong_num,int level,int subject,int normal_hour,double unitPrice,int ht_property,int course_type,double pos,double cash,String pos_num,String receipt_num,int stuff,int campus,String remarks);
	public List<Hetong> findHetongByAdmin(String hetong_num,String name,String school,String level,String hetong_type,String campus,String stuff,String course_type,String time1,String time2);
	public Hetong findById(int id);
	public boolean UpdateHetong(Hetong hetong); 
	public boolean updateHetongBy(Hetong hetong,int level,int unit_price,String hetong_num,int subject,int normal_hour,int hetong_type,int ht_property,int course_type,double pos,double cash,String pos_num, String receipt_num);
	public boolean DeleteHetong(Hetong hetong);
	public double findHetong_Sum_Money(String hetong_num,String name,String school,String level,String hetong_type,String campus,String stuff,String course_type,String time1,String time2);
}
