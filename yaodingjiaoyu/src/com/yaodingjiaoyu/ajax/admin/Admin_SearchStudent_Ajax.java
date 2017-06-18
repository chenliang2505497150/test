package com.yaodingjiaoyu.ajax.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.SearchStudentService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class Admin_SearchStudent_Ajax {

	
	private List<Student> studentList;
	private String name;
	private String school;
	private String level;
	private String telephone;
	private String stu_class;
	private String stu_status;
	private int start_look;// 本次浏览的起始页
	private String campus;
	private Map<String,Integer> item;//包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private GetResultObjectListService getResultObjectListService;//返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private SearchStudentService searchStudentService;
	private TransLateService transLateService;
	
	
	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public void setGetResultObjectListService(GetResultObjectListService getResultObjectListService) {
		this.getResultObjectListService = getResultObjectListService;
	}

	public void setGetItemInfoService(GetItemInfoService getItemInfoService) {
		this.getItemInfoService = getItemInfoService;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStu_class() {
		return stu_class;
	}

	public void setStu_class(String stu_class) {
		this.stu_class = stu_class;
	}

	public String getStu_status() {
		return stu_status;
	}

	public void setStu_status(String stu_status) {
		this.stu_status = stu_status;
	}

	public int getStart_look() {
		return start_look;
	}

	public void setStart_look(int start_look) {
		this.start_look = start_look;
	}


	public void setSearchStudentService(SearchStudentService searchStudentService) {
		this.searchStudentService = searchStudentService;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	
	


	
	//返回学员信息列表、总页数、当前页的第一条记录号，最后一条，以及总的学员信息数目
	@SuppressWarnings("unchecked")
	public String execute(){
		
		try {
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			resultMap.clear();//先清空数据	
			List<Student> list = searchStudentService.findByAdminRequest(name, school, level, stu_class, telephone, stu_status,campus);
			
			//初始化各个列表默认的信息
			studentList = (List<Student>) getResultObjectListService.getResultObjectList(start_look, PAGE_MAX, list);//显示指定页数据
			item =  getItemInfoService.getItemInfo(start_look, PAGE_MAX, list);
			// 返回数据:先将MAP转成JSON，然后返回数据
			List<Map<String, Object>> stu_resultlist =transLateService.transLateStudentList(studentList);
			
			resultMap.put("studentList", stu_resultlist);	
			resultMap.put("all_page",item.get("all_page"));
			resultMap.put("firstItem", item.get("firstItem"));
			resultMap.put("lastItem", item.get("lastItem"));
			resultMap.put("allItem", item.get("allItem"));
				
			
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:查询学生信息失败。参数start_look："+start_look+",name:"+name+
					",school:"+school+",telephone:"+telephone+",campus:"+campus+",stu_class:"+stu_class+",level:"+level+",stu_status:"+
					stu_status+",campus:"+campus+",MESSAGE:"
					+e.getMessage());
			return "error";
		}
		return null;
		
	}

}
