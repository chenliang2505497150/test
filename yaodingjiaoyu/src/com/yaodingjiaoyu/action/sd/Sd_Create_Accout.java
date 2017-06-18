package com.yaodingjiaoyu.action.sd;

import java.util.List;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.datebase.pojo.Job;

public class Sd_Create_Accout {

	private List<Job> job_list = null;
	private PageListService loadPageListService;

	public List<Job> getJob_list() {
		return job_list;
	}

	public void setJob_list(List<Job> job_list) {
		this.job_list = job_list;
	}

	public void setLoadPageListService(PageListService loadPageListService) {
		this.loadPageListService = loadPageListService;
	}

	// 查询员工信息
	public String execute() {
		try {
			job_list = loadPageListService.getJob_listForSd();// 获得岗位表，但是要去除管理员和校长
			return "success";
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:程序运行异常。参数SdId:"
					+ ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}

	}
}
