package com.yaodingjiaoyu.action.generalAjax;

import java.util.Map;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.HetongService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.Hetong;

public class HetongDetail_Ajax {

	private int id;
	private HetongService hetongService;
	private Map<String, Object> hetong;
	private TransLateService transLateService;
	
	
	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Map<String, Object> getHetong() {
		return hetong;
	}



	public void setHetong(Map<String, Object> hetong) {
		this.hetong = hetong;
	}



	public void setHetongService(HetongService hetongService) {
		this.hetongService = hetongService;
	}



	public String execute(){
		try {
			Hetong ht = hetongService.findById(id);
			hetong = transLateService.transLateHetong(ht);
			return "success";
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:查找合同详情失败。参数ID："+id+
					",MESSAGE:"+e.getMessage());
			return "error";
		}
	}
	
}
