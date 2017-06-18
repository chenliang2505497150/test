package com.yaodingjiaoyu.ajax.cr;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassTableService;
import com.yaodingjiaoyu.Service.DayService;
import com.yaodingjiaoyu.Service.TableCheckService;
import com.yaodingjiaoyu.ServiceImpl.StudentServiceImpl;
import com.yaodingjiaoyu.ServiceImpl.StuffServiceImpl;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Cr_AddClassTable_Ajax {

	private TableCheckService tableCheckService;
	private StudentServiceImpl studentService;
	private StuffServiceImpl stuffService;
	private ClassTableService classTableService;
	private DayService dayService;
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private String stuff;
	private String student;
	private String course_type;
	private Date day_time;
	private String class_time;
	private int delay_week;
	
	



	public void setDayService(DayService dayService) {
		this.dayService = dayService;
	}


	public int getDelay_week() {
		return delay_week;
	}


	public void setDelay_week(int delay_week) {
		this.delay_week = delay_week;
	}


	public void setClassTableService(ClassTableService classTableService) {
		this.classTableService = classTableService;
	}


	public void setStudentService(StudentServiceImpl studentService) {
		this.studentService = studentService;
	}


	public void setStuffService(StuffServiceImpl stuffService) {
		this.stuffService = stuffService;
	}


	public String getStuff() {
		return stuff;
	}


	public void setStuff(String stuff) {
		this.stuff = stuff;
	}


	public String getStudent() {
		return student;
	}


	public void setStudent(String student) {
		this.student = student;
	}


	public String getCourse_type() {
		return course_type;
	}


	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}


	public Date getDay_time() {
		return day_time;
	}


	public void setDay_time(Date day_time) {
		this.day_time = day_time;
	}


	public String getClass_time() {
		return class_time;
	}


	public void setClass_time(String class_time) {
		this.class_time = class_time;
	}


	public void setTableCheckService(TableCheckService tableCheckService) {
		this.tableCheckService = tableCheckService;
	}
	
	public int CheckCanAdd(Date now){
		int code = 400;//默认
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd"); //格式化当前系统日期
		//检查学生课时是否够
		if(tableCheckService.checkStudentClassHour(student, course_type)){
			
			//有没有课可以排判断
			if(tableCheckService.checkStudentPaike(student, course_type)){
				//获得学生在该天的该档是不是已经排其他课程了
				
				if(tableCheckService.checkStudentIsPaike(student, class_time, dateFm.format(now))){
					//判断老师在该天的该档是不是排有其他的课程类型
					if(tableCheckService.checkTeacherCourseType(stuff, dateFm.format(now), class_time, course_type)){
						//获得老师在该档的课有没有超过课程的额度
						if(tableCheckService.checkCourseCount(stuff, class_time, dateFm.format(now))){
							code = 200;//满足排课要求
							resultMap.put("status", code);
							return code;
						}else{
							code = 405;
							resultMap.put("status", code);
							resultMap.put("message", resultMap.get("message")+dateFm.format(now)+":"+"该时间课程人数已经最大、");
							return code;
						}
					}else{
						code = 404;
						resultMap.put("status", code);
						resultMap.put("message", resultMap.get("message")+dateFm.format(now)+":"+"老师已经排有其他的课程、");
						return code;
					}
				}else{
					code = 403;
					resultMap.put("status", code);
					resultMap.put("message", resultMap.get("message")+dateFm.format(now)+":"+"该学生该时间已经排课了、");
					return code;
				}
			}else{
				code = 402;//学生课已经全部排完
				resultMap.put("status", code);
				resultMap.put("message", resultMap.get("message")+dateFm.format(now)+":"+"学生课已经全部排完、");
				return code;
			}
			
		}else{
			code = 401;//学生课时不够用
			resultMap.put("status", code);
			resultMap.put("message", "学生课时不够用");
			return code;
		}
		
	}


	public void paike(Student student,Stuff stuff,Date now,String class_time,String course_type){
		
		if(CheckCanAdd(now) == 200){//成功
			//添加课表
			classTableService.saveClassTable(stuff, student, now, class_time, course_type);
		}
	}
	
	public String execute() {
		
		try {
			resultMap.clear();
			resultMap.put("status", 400);
			resultMap.put("message", "");
			//获得课表的基本信息，调用服务
			Student student_Obj = (Student) studentService.getUser(Integer.parseInt(student)); 
			
			Stuff stuff_Obj = (Stuff) stuffService.getUser(Integer.parseInt(stuff));
			//这里需要进行顺延循环添加	
			for(int i = 0;i <= delay_week;i++){
				paike(student_Obj,stuff_Obj,dayService.getWeeklast(day_time,i),class_time,course_type);
			}
			resultMap.put("message", resultMap.get("message")+"排课成功!");
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:添加课表失败。参数stuff："+stuff+",student:"+student+
					",course_type:"+course_type+",day_time:"+day_time+",class_time:"+class_time+",delay_week:"+delay_week+",MESSAGE:"+e.getMessage());
			return "error";
		}
		return null;
	}
}
