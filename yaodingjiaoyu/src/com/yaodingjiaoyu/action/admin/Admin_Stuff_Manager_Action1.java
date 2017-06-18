package com.yaodingjiaoyu.action.admin;

import java.util.List;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.Job;

public class Admin_Stuff_Manager_Action1 {

	private List<Job> job_list = null;

	public List<Job> getJob_list() {
		return job_list;
	}

	public void setJob_list(List<Job> job_list) {
		this.job_list = job_list;
	}

	public List<Campus> getCampus_list() {
		return campus_list;
	}

	public void setCampus_list(List<Campus> campus_list) {
		this.campus_list = campus_list;
	}

	public void setLoadPageListService(PageListService loadPageListService) {
		this.loadPageListService = loadPageListService;
	}

	private List<Campus> campus_list = null;
	private PageListService loadPageListService;

	/**
	 * 创建职工账号的模块
	 * 
	 * @return
	 */
	public String execute() {
		try {
			//初始化列表
			job_list = loadPageListService.getJob_list();
			campus_list = loadPageListService.getCampus_list();
			return "success";
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:初始化列表失败。");
			return "error";
		}
	}
}
