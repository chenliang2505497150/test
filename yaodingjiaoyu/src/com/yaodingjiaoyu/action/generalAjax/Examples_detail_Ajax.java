package com.yaodingjiaoyu.action.generalAjax;

import java.util.Map;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.ExamplesService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.Examples;

public class Examples_detail_Ajax {

	private int id;
	
	private ExamplesService examplesService;
	
	private Map<String, Object> examples;
	
	private TransLateService transLateService;
	public Map<String, Object> getExamples() {
		return examples;
	}

	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	public void setExamples(Map<String, Object> examples) {
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
			Examples ex = examplesService.findById(id);
			examples = transLateService.transLateExample(ex);
			return "success";
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:查找课堂跟踪记录失败。参数ID："+id+",MESSAGE:"+e.getMessage());
			return "error";
		}
	}
}
