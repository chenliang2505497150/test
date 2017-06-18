package com.yaodingjiaoyu.action.cr;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassContentService;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.ClassContent;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Cr_StuInfo_Action2 {

	// 这里是学生回访查看:序号，姓名，添加人岗位,添加人，时间，内容，校区，更多
	// 筛选框：校区，添加人，起始时间，结束时间,学生
	private List<Map<String, Object>> ContentList;// JSP页面需要的记录列表
	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
	private List<Map<String, Object>> tr_list;
	private ClassContentService searchClassContentService;// 核心服务
	private GetResultObjectListService getResultObjectListService;// 返回对应页面的数据
	private GetItemInfoService getItemInfoService;
	private PageListService loadPageListService;
	private TransLateService transLateService;
	private UserService stuffService;

	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
	}

	public List<Map<String, Object>> getTr_list() {
		return tr_list;
	}

	public void setTr_list(List<Map<String, Object>> tr_list) {
		this.tr_list = tr_list;
	}

	public List<Map<String, Object>> getContentList() {
		return ContentList;
	}

	public void setContentList(List<Map<String, Object>> contentList) {
		ContentList = contentList;
	}

	public Map<String, Integer> getItem() {
		return item;
	}

	public void setItem(Map<String, Integer> item) {
		this.item = item;
	}

	public void setSearchClassContentService(ClassContentService searchClassContentService) {
		this.searchClassContentService = searchClassContentService;
	}

	public void setGetResultObjectListService(GetResultObjectListService getResultObjectListService) {
		this.getResultObjectListService = getResultObjectListService;
	}

	public void setGetItemInfoService(GetItemInfoService getItemInfoService) {
		this.getItemInfoService = getItemInfoService;
	}

	public void setLoadPageListService(PageListService loadPageListService) {
		this.loadPageListService = loadPageListService;
	}

	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	@SuppressWarnings("unchecked")
	public String execute() {

		try {
			int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
			// 获得存储在SESSION中的校区和职工编号
			String stuff = ActionContext.getContext().getSession().get("ID").toString();
			String campus = ActionContext.getContext().getSession().get("campus").toString();

			Stuff cr = (Stuff) stuffService.getUser(Integer.parseInt(stuff));
			List<Stuff> tr_list_temp = loadPageListService.getTrListByCampus(campus);// 列表未经翻译
			tr_list_temp.add(cr);// 将班主任本人也加入筛选队列

			tr_list = transLateService.transLateCrAndCcList(tr_list_temp);

			// 由于时间格式需要改变，因此该数据需要转码
			List<ClassContent> list = searchClassContentService.findClassContentByCr(campus, "", "", stuff, "no", "");

			if (null != list) {
				List<ClassContent> temp = (List<ClassContent>) getResultObjectListService.getResultObjectList(1,
						PAGE_MAX, list);// 显示前15行数据
				ContentList = transLateService.transLateClassContent(temp);
			} else {
				ContentList = null;
			}

			item = getItemInfoService.getItemInfo(1, PAGE_MAX, list);
			return "success";
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:加载学生信息失败,MESSAGE:" + e.getMessage());
			return "error";
		}
	}
}
