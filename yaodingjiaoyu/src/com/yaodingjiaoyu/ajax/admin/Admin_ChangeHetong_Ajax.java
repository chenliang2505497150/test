package com.yaodingjiaoyu.ajax.admin;


import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassHourService;
import com.yaodingjiaoyu.Service.HetongService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.ClassHour;
import com.yaodingjiaoyu.datebase.pojo.Hetong;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class Admin_ChangeHetong_Ajax {

	
	//获得上传的参数
	private int id;
	private int unit_price;
	private String name;
	private int level;
	private String hetong_num;
	private int subject;
	private int normal_hour;
	private int hetong_type;
	private int ht_property;
	private int course_type;
	private double pos;
	private double cash;
	private String pos_num;
	private String receipt_num;
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private HetongService hetongService;
	private UserService studentService;
	private ClassHourService classHourService;
	
	
	
	public void setClassHourService(ClassHourService classHourService) {
		this.classHourService = classHourService;
	}


	public void setHetongService(HetongService hetongService) {
		this.hetongService = hetongService;
	}


	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUnit_price() {
		return unit_price;
	}


	public void setUnit_price(int unit_price) {
		this.unit_price = unit_price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public String getHetong_num() {
		return hetong_num;
	}


	public void setHetong_num(String hetong_num) {
		this.hetong_num = hetong_num;
	}


	public int getSubject() {
		return subject;
	}


	public void setSubject(int subject) {
		this.subject = subject;
	}


	public int getNormal_hour() {
		return normal_hour;
	}


	public void setNormal_hour(int normal_hour) {
		this.normal_hour = normal_hour;
	}


	public int getHetong_type() {
		return hetong_type;
	}


	public void setHetong_type(int hetong_type) {
		this.hetong_type = hetong_type;
	}


	public int getHt_property() {
		return ht_property;
	}


	public void setHt_property(int ht_property) {
		this.ht_property = ht_property;
	}


	public int getCourse_type() {
		return course_type;
	}


	public void setCourse_type(int course_type) {
		this.course_type = course_type;
	}


	public double getPos() {
		return pos;
	}


	public void setPos(double pos) {
		this.pos = pos;
	}


	public double getCash() {
		return cash;
	}


	public void setCash(double cash) {
		this.cash = cash;
	}


	public String getPos_num() {
		return pos_num;
	}


	public void setPos_num(String pos_num) {
		this.pos_num = pos_num;
	}


	public String getReceipt_num() {
		return receipt_num;
	}


	public void setReceipt_num(String receipt_num) {
		this.receipt_num = receipt_num;
	}

	private boolean Change_Student(Student student,int hour) {
		student.setName(name);
		student.setLastHour(student.getLastHour()+hour);
		student.setAllHour(student.getAllHour()+hour);
		return studentService.update(student);
	}

	//保存课时信息
	private boolean Change_ClassHour(Hetong hetong,int hour) {
		
		if(course_type ==  hetong.getCourseType().getPId()){
			ClassHour classHour =  classHourService.getClassHour(hetong.getStudent().getPId().toString(),course_type+"");
			classHour.setAllHour(classHour.getAllHour()+hour);
			classHour.setLastHour(classHour.getLastHour()+hour);
			return classHourService.update(classHour);
			
		}else{
			//课时表也要变更
			ClassHour classHour_bef =  classHourService.getClassHour(hetong.getStudent().getPId().toString(), hetong.getCourseType().getPId().toString());
			ClassHour classHour_now =  classHourService.getClassHour(hetong.getStudent().getPId().toString(), course_type+"");
			
			classHour_bef.setAllHour(classHour_bef.getAllHour()-hetong.getNormalHour());
			classHour_bef.setLastHour(classHour_bef.getLastHour()-hetong.getNormalHour());
			
			classHour_now.setAllHour(classHour_now.getAllHour()+normal_hour);
			classHour_now.setLastHour(classHour_now.getLastHour()+normal_hour);
			
			//保存课时表
			return (classHourService.update(classHour_now))&&(classHourService.update(classHour_bef));
		}
		
	}
	
	//保存合同
	private void Change_Hetong(Hetong hetong) {
		
		boolean success = hetongService.updateHetongBy(hetong, level, unit_price, hetong_num, subject, normal_hour, hetong_type, ht_property, course_type, pos, cash, pos_num, receipt_num);
		if(success) {
			resultMap.put("status", 200);
			resultMap.put("message", "修改成功!");
		}else{
			resultMap.put("status", 400);
			resultMap.put("message", "修改合同失败!");
		}
	}
	
	//处理请求
	public void Do_Change(Student student,Hetong hetong) {
		
		int hour = normal_hour-hetong.getNormalHour();
		
		if(Change_Student(student,hour)) {
			
			if(Change_ClassHour(hetong,hour)) {
				Change_Hetong(hetong);
			}
			else {
				resultMap.put("status", 400);
				resultMap.put("message", "修改课时信息失败!");
			}
			
			
			
		}else {
			resultMap.put("status", 400);
			resultMap.put("message", "修改学生信息失败!");
		}
		
	}
	
	public String execute(){
		
		try {
			resultMap.clear();
			//获得合同，以及学生
			Hetong hetong = hetongService.findById(id);
			Student student = (Student) studentService.getUser(hetong.getStudent().getPId());
			Do_Change(student,hetong);
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
		
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:初始化学生列表失败。参数id："+id+",unit_price:"+unit_price+
					",name:"+name+",level:"+level+",hetong_num:"+hetong_num+",subject:"+
					subject+",normal_hour:"+normal_hour+",hetong_type:"+hetong_type+",ht_property:"+ht_property
					+",course_type:"+course_type+",pos:"+pos+",cash:"+cash+",pos_num:"+
					pos_num+",receipt_num:"+receipt_num+",MESSAGE:"+e.getMessage());
			
			return "error";
		}
		return null;
	}
}
