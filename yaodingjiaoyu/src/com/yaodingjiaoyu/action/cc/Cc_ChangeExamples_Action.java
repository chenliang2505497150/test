package com.yaodingjiaoyu.action.cc;

import java.util.List;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.ExamplesService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.Channel;
import com.yaodingjiaoyu.datebase.pojo.Examples;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Probability;

public class Cc_ChangeExamples_Action {
	private List<Level> level_list;
	private List<Probability> probability_list;
	private List<Channel> channel_list;
	private List<Campus> campus_list;
	private PageListService loadPageListService;
	private int id;
	
	private ExamplesService examplesService;
	
	private Examples examples;
	
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

	public List<Campus> getCampus_list() {
		return campus_list;
	}

	public void setCampus_list(List<Campus> campus_list) {
		this.campus_list = campus_list;
	}
	

	public Examples getExamples() {
		return examples;
	}

	public void setExamples(Examples examples) {
		this.examples = examples;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setExamplesService(ExamplesService examplesService) {
		this.examplesService = examplesService;
	}


	public String execute(){
		try {
			//初始化列表
			level_list = loadPageListService.getLevel_list();
			probability_list = loadPageListService.getProbability_list();
			channel_list = loadPageListService.getChannel_list();
			campus_list = loadPageListService.getCampus_list();
					
			examples = examplesService.findById(id);
			return "success";
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:初始化例子失败。参数:"+"ID:"+id);
			return "error";
		}
	}
}
