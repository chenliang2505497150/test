package com.yaodingjiaoyu.Service;

import java.util.List;
import java.util.Map;

import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.CcContent;
import com.yaodingjiaoyu.datebase.pojo.Channel;
import com.yaodingjiaoyu.datebase.pojo.ClassContent;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;
import com.yaodingjiaoyu.datebase.pojo.ClassTime;
import com.yaodingjiaoyu.datebase.pojo.Examples;
import com.yaodingjiaoyu.datebase.pojo.Hetong;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Probability;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public interface TransLateService {
	//翻译例子库的方法
	public List<Map<String, Object>> transLateExamples(List<Level> level_list,List<Probability> probability_list,List<Channel> channel_list,List<Campus> campus_list,List<Map<String, Object>> list);
	//翻译跟踪记录的时间，校区，跟踪人等
	public List<Map<String, Object>>transLateCcContent(List<CcContent> ccContentList);
	//将hibernate查询的对象封装到Map中,翻译学生列表
	public List<Map<String, Object>> transLateStudentList(List<Student> studentList);
	//返回员工列表
	public List<Map<String, Object>> transLateStuffList(List<Stuff> stuffs);
	
	//返回员工详细信息列表
	public List<Map<String, Object>> transLateStuffDetailList(List<Stuff> stuffs);
	
	//返回上课时间段
	public List<Map<String, Object>> transLateClassTimeList(List<ClassTime> classTimes);
	
	//返回班主任以及老师列表
	public List<Map<String, Object>> transLateCrAndCcList(List<Stuff> stuffs);
	//翻译课堂跟踪记录的时间，校区，跟踪人等
	public List<Map<String, Object>>transLateClassContent(List<ClassContent> ContentList);
	//将hibernate查询的对象封装到Map中,翻译合同列表
	public List<Map<String, Object>> transLateHetongList(List<Hetong> List);
	//返回简略的合同信息
	public List<Map<String, Object>> transLateSimpleHetongList(List<Hetong> List);
	
	//翻译学生信息的方法
	public List<Map<String, Object>> transLateStudent(List<Level> level_list,List<Map<String, Object>> list);
	
	//翻译单个例子
	public Map<String, Object> transLateExample(Examples examples);
	//翻译课程表数据翻译成课表形式
	public Map<String,Map<String, Object>> transLateClassTableList(List<ClassTable> List);
	
	//适用老师的单张课表
	public Map<String,Object> transLateClassTableListForTr(List<ClassTable> List);
	//将课程表数据翻译程列表
	public List<Map<String, Object>> transLateClassTableListToMap(List<ClassTable> List);
	
	//翻译单个学生
	public Map<String, Object> transLateStudent(Student student);
	
	//翻译单个合同
	public Map<String, Object> transLateHetong(Hetong hetong);
}
