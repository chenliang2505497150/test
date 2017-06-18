package com.yaodingjiaoyu.ajax.cr;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassTableService;
import com.yaodingjiaoyu.Service.DayService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;
import com.yaodingjiaoyu.datebase.pojo.ClassTime;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Cr_SearchClassTable_Ajax {

	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private TransLateService transLateService;
	private DayService dayService;
	private ClassTableService classTableService;
	private Map<String, String> day = null;
	private Map<String,Map<String, Object>> table = null;
	private List<Map<String, Object>> class_time = null;
	private PageListService pageListService;
	private List<Map<String, Object>> tr_list = null;
	
	private Date time;
	
	private String name;
	
	
	
	public Date getTime() {
		return time;
	}



	public void setTime(Date time) {
		this.time = time;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}



	public void setDayService(DayService dayService) {
		this.dayService = dayService;
	}



	public void setClassTableService(ClassTableService classTableService) {
		this.classTableService = classTableService;
	}



	public void setPageListService(PageListService pageListService) {
		this.pageListService = pageListService;
	}

	private void searchClassTable() {
		if(time == null){
			time = new Date();
		}
		//获得存储在SESSION中的校区和职工编号
		String campus= ActionContext.getContext().getSession().get("campus").toString();
		
		
		List<Stuff> tr_list_tmp = pageListService.getTrListByCampusAndInput(campus, name);
		tr_list =  transLateService.transLateStuffList(tr_list_tmp);
		
		List<ClassTime> class_time_tmp = pageListService.getClassTimeList();
		class_time = transLateService.transLateClassTimeList(class_time_tmp);
		
		//可以获得指定日期的周一和周日
		day =  dayService.getMondayAndSundayOfWeek(time);
		
		//获得课表
		List<ClassTable> list = classTableService.findClassTableByCr(day.get("one"), day.get("seven"), campus, tr_list_tmp);
		table = transLateService.transLateClassTableList(list);
		
		//根据日期和员工的编号以及校区获得课表的列表
		
		//需要返回的数据:tr_list,table,day,class_time
		resultMap.put("tr_list", tr_list);	
		resultMap.put("table",table);
		resultMap.put("day", day);
		resultMap.put("class_time", class_time);
	}

	public String execute(){
		
		try {
			resultMap.clear();
			searchClassTable();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
			
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:搜索课表信息失败。参数time："+time.toString()+",name:"+name+",MESSAGE:"
					+e.getMessage());
			return "error";
		}
		return null;
	}
}
