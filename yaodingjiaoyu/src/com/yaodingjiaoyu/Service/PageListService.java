package com.yaodingjiaoyu.Service;

import java.util.List;

import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.Channel;
import com.yaodingjiaoyu.datebase.pojo.ClassTime;
import com.yaodingjiaoyu.datebase.pojo.CourseType;
import com.yaodingjiaoyu.datebase.pojo.HetongType;
import com.yaodingjiaoyu.datebase.pojo.HtProperty;
import com.yaodingjiaoyu.datebase.pojo.Job;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Probability;
import com.yaodingjiaoyu.datebase.pojo.Stuff;
import com.yaodingjiaoyu.datebase.pojo.Subject;

public interface PageListService {
	public List<Job> getJob_listForSd();
	public List<Job> getJob_list();
	public List<Level> getLevel_list();
	public List<Probability> getProbability_list();
	public List<Channel> getChannel_list();
	public List<Campus> getCampus_list();
	public List<Stuff> getSalerListByCampus(String campus);//获得该校区所有的销售
	public List<Stuff> getcr_trListByCampus(String campus);//获得该校区所有的老师和班主任
	public List<Stuff> getcrListByCampus(String campus);//获得该校区班主任
	public List<Stuff> getTrListByCampus(String campus);
	public List<HetongType> getHetongTypeList();//获得合同的类型：新签，续费
	public List<CourseType> getCourseTypeList();//获得课程类型
	public List<HtProperty> getHtPropertyList();//获得合同属性
	public List<Subject> getSubjectList();//获得课程
	public List<Stuff> getcr_ccListByCampus(String campus);
	public List<ClassTime> getClassTimeList();
	public List<Stuff> getTrListByCampusAndInput(String campus ,String input);//获得该校区指定的老师
}
