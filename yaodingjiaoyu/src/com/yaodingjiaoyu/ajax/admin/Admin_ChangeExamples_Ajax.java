package com.yaodingjiaoyu.ajax.admin;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ExamplesService;
import com.yaodingjiaoyu.datebase.pojo.Examples;

public class Admin_ChangeExamples_Ajax {
	
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private String name;
	private String school;
	private int level;
	private int now_class;
	private String phone1;
	private String phone2;
	private String address;
	private int youxiao;
	private int zhuangtai;
	private int probability;
	private int channel;
	private int id;
	private int campus;

	
	
	public String getPhone1() {
		return phone1;
	}


	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}


	public String getPhone2() {
		return phone2;
	}


	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}


	public int getCampus() {
		return campus;
	}


	public void setCampus(int campus) {
		this.campus = campus;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	private ExamplesService examplesService;
	
	public void setExamplesService(ExamplesService examplesService) {
		this.examplesService = examplesService;
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
	
	
	public int getLevel() {
		return level;
	}
	
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	public int getNow_class() {
		return now_class;
	}
	
	
	public void setNow_class(int now_class) {
		this.now_class = now_class;
	}
	
	
	public String getAddress() {
		return address;
	}
	
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public int getYouxiao() {
		return youxiao;
	}
	
	
	public void setYouxiao(int youxiao) {
		this.youxiao = youxiao;
	}
	
	
	public int getZhuangtai() {
		return zhuangtai;
	}
	
	
	public void setZhuangtai(int zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	
	
	public int getProbability() {
		return probability;
	}
	
	
	public void setProbability(int probability) {
		this.probability = probability;
	}
	
	
	public int getChannel() {
		return channel;
	}
	
	
	public void setChannel(int channel) {
		this.channel = channel;
	}
	
	private void Do_Change() {
		
		boolean success = false;
		//通过编号获得原来的例子
		Examples examples = examplesService.findById(id);
		
		if(examples != null){
			//更新例子
			success =  examplesService.AdminUpdateExamples(examples, phone1, phone2, name, school, level, now_class, address, youxiao, zhuangtai, probability, channel, campus);
			//返回信息
			if(success){
				resultMap.put("status", 200);
				resultMap.put("message", "修改成功!");
			}else{
				resultMap.put("status", 400);//失败
				resultMap.put("message", "修改失败!");
			}
		}else{
			resultMap.put("status", 400);//失败
			resultMap.put("message", "没有该学生信息!");
		}
		
	}
	
	
	public String execute() {
		
		try {
			
			resultMap.clear();
			Do_Change();
			
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
			
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:初始化学生列表失败。参数id："+id+",school:"+school+
					",name:"+name+",level:"+level+",now_class:"+now_class+",address:"+
					address+",youxiao:"+youxiao+",zhuangtai:"+zhuangtai+",probability:"+probability
					+",channel:"+channel+",phone1:"+phone1+",phone2:"+phone2+",MESSAGE:"+e.getMessage());
			return "error";
		}
		
		return null;
	}
	
	
}
