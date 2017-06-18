package com.yaodingjiaoyu.action.sd;

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
import com.yaodingjiaoyu.datebase.pojo.ClassContent;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Sd_Student_Action2 {
	//这里是学生回访查看:序号，姓名，添加人岗位,添加人，时间，内容，校区，更多
		//筛选框：校区，添加人，起始时间，结束时间,学生
		private List<Map<String, Object>> ContentList;//JSP页面需要的记录列表
		private Map<String,Integer> item;//包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息
		private ClassContentService searchClassContentService;//核心服务
		private GetResultObjectListService getResultObjectListService;//返回对应页面的数据
		private GetItemInfoService getItemInfoService;
		private PageListService loadPageListService;
		private TransLateService transLateService;
		private List<Stuff> stuff_list = null;
		
		
		
		public List<Stuff> getStuff_list() {
			return stuff_list;
		}




		public void setStuff_list(List<Stuff> stuff_list) {
			this.stuff_list = stuff_list;
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
		public String execute(){
			try {
				int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
				//由于时间格式需要改变，因此该数据需要转码
				int campus= (int) ActionContext.getContext().getSession().get("campus");
				List<ClassContent> list = searchClassContentService.findClassContentByAdmin(campus+"", "", "", "no","");
				List<ClassContent> temp = (List<ClassContent>) getResultObjectListService.getResultObjectList(1, PAGE_MAX, list);//显示前15行数据
				ContentList =  transLateService.transLateClassContent(temp);
				item =  getItemInfoService.getItemInfo(1, PAGE_MAX, list);
				stuff_list = loadPageListService.getcr_trListByCampus(campus+"");
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
