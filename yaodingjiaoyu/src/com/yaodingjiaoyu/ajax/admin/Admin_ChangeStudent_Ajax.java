package com.yaodingjiaoyu.ajax.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.SaveStudentService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class Admin_ChangeStudent_Ajax {
	
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private int id;
	private String name;
	private String sex;
	private Date birthday;
	private String school;
	private int level;
	private int now_class;
	private String phone1;
	private String phone2;
	private String address;
	private String parent_name;
	private int campus;
	
	private SaveStudentService saveStudentService;
	private UserService studentService;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}
	public void setSaveStudentService(SaveStudentService saveStudentService) {
		this.saveStudentService = saveStudentService;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getNow_class() {
		return now_class;
	}
	public void setNow_class(int now_class) {
		this.now_class = now_class;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	public int getCampus() {
		return campus;
	}
	public void setCampus(int campus) {
		this.campus = campus;
	}
	
	private void Do_ChangeStudent() {
		
		boolean success = false;
		//通过编号获得原来的学生
		Student student = (Student) studentService.getUser(id);
		success = saveStudentService.update(student, name, sex, birthday, school, level, now_class, phone1, phone2, address, parent_name, campus);
		//返回信息
		if(success){
			resultMap.put("status", 200);
			resultMap.put("message", "修改成功!");
		}else{
			resultMap.put("status", 400);//失败
			resultMap.put("message", "修改失败!");
		}
		
	}
	
	public String execute(){

		try {
			resultMap.clear();
			Do_ChangeStudent();
			
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
			
		} catch (Exception e) {
			
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:初始化学生列表失败。参数id："+id+",name:"+name+
					",sex:"+sex+",birthday:"+birthday+",school:"+school+",level:"+
					level+",now_class:"+now_class+",phone1:"+phone1+",phone2:"+phone2
					+",address:"+address+",parent_name:"+parent_name+",campus:"+campus);
			
			return "error";
		}
		return null;
	}
}
