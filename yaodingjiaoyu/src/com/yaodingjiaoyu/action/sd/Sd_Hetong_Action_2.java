package com.yaodingjiaoyu.action.sd;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.UnitPriceService;
import com.yaodingjiaoyu.datebase.pojo.CourseType;
import com.yaodingjiaoyu.datebase.pojo.HtProperty;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Stuff;
import com.yaodingjiaoyu.datebase.pojo.Subject;

public class Sd_Hetong_Action_2 {
	private List<Level> level_list = null;
	private List<Stuff> stuff_list = null;
	private List<Subject> subject_list = null;
	private List<HtProperty> ht_property_list = null;
	private List<CourseType> course_type_list = null;
	private int unit_price = 0;
	private UnitPriceService unitPriceService;
	public void setUnitPriceService(UnitPriceService unitPriceService) {
		this.unitPriceService = unitPriceService;
	}
	
	
	public List<Stuff> getStuff_list() {
		return stuff_list;
	}


	public void setStuff_list(List<Stuff> stuff_list) {
		this.stuff_list = stuff_list;
	}


	public int getUnit_price() {
		return unit_price;
	}


	public void setUnit_price(int unit_price) {
		this.unit_price = unit_price;
	}


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


	public List<CourseType> getCourse_type_list() {
		return course_type_list;
	}


	public void setCourse_type_list(List<CourseType> course_type_list) {
		this.course_type_list = course_type_list;
	}







	private PageListService loadPageListService;
	
	
	public PageListService getLoadPageListService() {
		return loadPageListService;
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


	




	public String execute(){
		try {
			//初始化列表
			int campus= (int) ActionContext.getContext().getSession().get("campus");
			
			unit_price = unitPriceService.GetUnitPriceBy(campus, 1, 1);
			level_list = loadPageListService.getLevel_list();
			stuff_list = loadPageListService.getSalerListByCampus(campus+"");
			course_type_list = loadPageListService.getCourseTypeList();
			ht_property_list = loadPageListService.getHtPropertyList();
			subject_list = loadPageListService.getSubjectList();
			return "success";
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:程序运行异常。参数SdId:"
			+ActionContext.getContext().getSession().get("ID")+
					",MESSAGE:"+e.getMessage());
			return "error";
		}
	}
}
