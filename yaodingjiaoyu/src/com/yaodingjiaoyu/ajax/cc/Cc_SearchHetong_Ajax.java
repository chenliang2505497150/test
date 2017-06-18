package com.yaodingjiaoyu.ajax.cc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.HetongService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.Hetong;

public class Cc_SearchHetong_Ajax {
	private int start_look;// 本次浏览的起始页
	private String name;
	private String school;
	private String level;
	private String hetong_type;
	private String hetong_num;
	private String course_type;
	private String time1 = "";
	private String time2 = "";
	private String stuff;// 签约人员的编号
	private String campus;// 校区编号

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private List<Map<String, Object>> Contennt_list;
	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private HetongService searchHetongService;// 核心服务
	private GetResultObjectListService getResultObjectListService;// 返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private TransLateService transLateService;

	public int getStart_look() {
		return start_look;
	}

	public void setStart_look(int start_look) {
		this.start_look = start_look;
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

	public String getHetong_type() {
		return hetong_type;
	}

	public void setHetong_type(String hetong_type) {
		this.hetong_type = hetong_type;
	}

	public String getHetong_num() {
		return hetong_num;
	}

	public void setHetong_num(String hetong_num) {
		this.hetong_num = hetong_num;
	}

	public String getCourse_type() {
		return course_type;
	}

	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public void setSearchHetongService(HetongService searchHetongService) {
		this.searchHetongService = searchHetongService;
	}

	public void setGetResultObjectListService(GetResultObjectListService getResultObjectListService) {
		this.getResultObjectListService = getResultObjectListService;
	}

	public void setGetItemInfoService(GetItemInfoService getItemInfoService) {
		this.getItemInfoService = getItemInfoService;
	}

	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	@SuppressWarnings("unchecked")
	public String execute() {

		try {
			resultMap.clear();// 先清空数据
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			// 获得存储在SESSION中的校区和职工编号
			stuff = ActionContext.getContext().getSession().get("ID").toString();
			campus = ActionContext.getContext().getSession().get("campus").toString();

			// 未经处理，例如年级还是数字
			List<Hetong> list = searchHetongService.findHetongByAdmin(hetong_num, name, school, level, hetong_type,
					campus, stuff, course_type, time1, time2);
			List<Hetong> tmp = (List<Hetong>) getResultObjectListService.getResultObjectList(start_look, PAGE_MAX,
					list);// 显示前15行数据
			Contennt_list = transLateService.transLateSimpleHetongList(tmp);

			item = getItemInfoService.getItemInfo(start_look, PAGE_MAX, list);
			// 返回数据:先将MAP转成JSON，然后返回数据

			resultMap.put("Contennt_list", Contennt_list);
			resultMap.put("all_page", item.get("all_page"));
			resultMap.put("firstItem", item.get("firstItem"));
			resultMap.put("lastItem", item.get("lastItem"));
			resultMap.put("allItem", item.get("allItem"));

			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:查询合同失败。参数hetong_type：" + hetong_type + ",school:"
					+ school + ",name:" + name + ",level:" + level + ",hetong_num:" + hetong_num + ",course_type:"
					+ course_type + ",time1:" + time1 + ",time2:" + time2 + ",stuff:" + stuff + ",campus:" + campus
					+ ",start_look:" + start_look + ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;
	}
}
