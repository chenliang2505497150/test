package com.yaodingjiaoyu.ajax.sd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.SearchStuffService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

/**
 * 从客户端传入员工的姓名、岗位、冻结状态、是否兼职等信息，通过该类处理并返回数据
 * 
 * @author chenliang
 * @desctiption 用来处理查询员工列表的Ajax请求
 */
public class Sd_SearchStuff_Ajax {
	// 服务器传入参数
	private String name;
	private String job;
	private String status;
	private String part_time;
	private int start_look;// 本次浏览的起始页
	// 服务器穿出参数
	private List<Map<String, Object>> stuffList = null;
	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	// 注入的服务
	private SearchStuffService searchStuffService;
	private GetResultObjectListService getResultObjectListService;// 返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private TransLateService transLateService;

	// 方法

	public String getName() {
		return name;
	}

	public int getStart_look() {
		return start_look;
	}

	public void setStart_look(int start_look) {
		this.start_look = start_look;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPart_time() {
		return part_time;
	}

	public void setPart_time(String part_time) {
		this.part_time = part_time;
	}

	public void setSearchStuffService(SearchStuffService searchStuffService) {
		this.searchStuffService = searchStuffService;
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
	public void doSearchStuff() {
		int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
		String campus = ActionContext.getContext().getSession().get("campus").toString();
		// 查询该校区的员工
		List<Stuff> list = searchStuffService.findBySdRequest(name, job, status, part_time, campus);
		// 取上一个查询的第一页数据
		List<Stuff> stuff_list_tmp = (List<Stuff>) getResultObjectListService.getResultObjectList(start_look, PAGE_MAX, list);// 显示前15行数据
		// 最后还需要对员工列表进行翻译,因为一些状态字段值为0和1
		stuffList = transLateService.transLateStuffDetailList(stuff_list_tmp);
		item = getItemInfoService.getItemInfo(start_look, PAGE_MAX, list);

		resultMap.put("stuffList", stuffList);
		resultMap.put("all_page", item.get("all_page"));
		resultMap.put("firstItem", item.get("firstItem"));
		resultMap.put("lastItem", item.get("lastItem"));
		resultMap.put("allItem", item.get("allItem"));
	}

	// 处理方法
	public String execute() {
		try {
			resultMap.clear();// 先清空数据
			doSearchStuff();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:查询例子失败。参数start_look：" + start_look + ",name:" + name
					+ ",name:" + name + ",job:" + job + ",status:" + status + ",part_time:" + part_time + ",SdId:"
					+ ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;

	}

}
