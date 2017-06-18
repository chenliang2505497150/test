package com.yaodingjiaoyu.action.cc;

import java.util.List;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.datebase.pojo.Channel;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Probability;

public class Cc_Examples_Action3 {
	private List<Level> level_list = null;
	private List<Probability> probability_list = null;
	private List<Channel> channel_list = null;
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


	public List<Probability> getProbability_list() {
		return probability_list;
	}


	public void setProbability_list(List<Probability> probability_list) {
		this.probability_list = probability_list;
	}


	public List<Channel> getChannel_list() {
		return channel_list;
	}


	public void setChannel_list(List<Channel> channel_list) {
		this.channel_list = channel_list;
	}





	public String execute(){
		try {
			//初始化列表
			level_list = loadPageListService.getLevel_list();
			probability_list = loadPageListService.getProbability_list();
			channel_list = loadPageListService.getChannel_list();
			return "success";
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:初始化列表失败,MESSAGE:"+e.getMessage());
			return "error";
		}
	}
}
