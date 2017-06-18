package com.yaodingjiaoyu.action.admin;

import java.util.List;
import org.apache.log4j.Logger;
import com.yaodingjiaoyu.Service.HetongService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.datebase.pojo.CourseType;
import com.yaodingjiaoyu.datebase.pojo.Hetong;
import com.yaodingjiaoyu.datebase.pojo.HetongType;
import com.yaodingjiaoyu.datebase.pojo.HtProperty;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Subject;

public class Admin_ChangeHetong_Action {
	//获得相关的列表信息:可以修改的部分：学员姓名，合同编号，年级，科目，常规课时，课时单价，合同类型，合同属性，课程类型，pos，cash，以及pos_num,receipt_num
	private List<Level> level_list;
	private List<Subject> subject_list;
	private List<HtProperty> ht_property_list;
	private List<HetongType> hetong_type_list;
	private List<CourseType> course_type_list;
	private PageListService loadPageListService;
	private int id;
	
	private HetongService hetongService;
	
	private Hetong hetong;
	
	

	public List<Subject> getSubject_list() {
		return subject_list;
	}

	public void setSubject_list(List<Subject> subject_list) {
		this.subject_list = subject_list;
	}

	public List<HtProperty> getHt_property_list() {
		return ht_property_list;
	}

	public void setHt_property_list(List<HtProperty> ht_property_list) {
		this.ht_property_list = ht_property_list;
	}

	public List<HetongType> getHetong_type_list() {
		return hetong_type_list;
	}

	public void setHetong_type_list(List<HetongType> hetong_type_list) {
		this.hetong_type_list = hetong_type_list;
	}

	public List<CourseType> getCourse_type_list() {
		return course_type_list;
	}

	public void setCourse_type_list(List<CourseType> course_type_list) {
		this.course_type_list = course_type_list;
	}

	public Hetong getHetong() {
		return hetong;
	}

	public void setHetong(Hetong hetong) {
		this.hetong = hetong;
	}

	public void setHetongService(HetongService hetongService) {
		this.hetongService = hetongService;
	}

	public void setLoadPageListService(PageListService loadPageListService) {
		this.loadPageListService = loadPageListService;
	}

	public List<Level> getLevel_list() {
		return level_list;
	}

	public void setLevel_list(List<Level> level_list) {
		this.level_list = level_list;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String execute() {
		try {
			//初始化列表
			level_list = loadPageListService.getLevel_list();
			subject_list = loadPageListService.getSubjectList();
			ht_property_list = loadPageListService.getHtPropertyList();
			hetong_type_list = loadPageListService.getHetongTypeList();		
			course_type_list = loadPageListService.getCourseTypeList();
			
			hetong = hetongService.findById(id);
			return "success";
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:初始化列表失败。参数:"+"ID:"+id);
			return "error";
		}
	}
	
	
	
}
