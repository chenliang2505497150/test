package com.yaodingjiaoyu.ajax.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ExamplesService;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.Channel;
import com.yaodingjiaoyu.datebase.pojo.Examples;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Probability;

public class Admin_SaveExamples_Aajx {

	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private String name;
	private String school;
	private int level;
	private int now_class;
	private String phone1;
	private String phone2;
	private String address;
	private int zhuangtai;
	private int probability;
	private int channel;
	private int campus;
	
	private ExamplesService saveExamplesService;
	
	public ExamplesService getSaveExamplesService() {
		return saveExamplesService;
	}
	public void setSaveExamplesService(ExamplesService saveExamplesService) {
		this.saveExamplesService = saveExamplesService;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public int getCampus() {
		return campus;
	}
	public void setCampus(int campus) {
		this.campus = campus;
	}
	public Examples InitExamples(){
		//-------------------------------
		Level level_temp = new Level();
		level_temp.setPId(level);
		
		Probability probability_temp = new Probability();
		probability_temp.setPId(probability);
		
		Channel channel_temp = new Channel();
		channel_temp.setPId(channel);
		
		Campus campus_temp = new Campus();
		campus_temp.setPId(campus);	
		//-------------------------------
		
		Examples examples = new Examples();
		examples.setName(name);
		examples.setSchool(school);
		examples.setLevel(level_temp);
		examples.setNowClass(now_class);
		examples.setPhone1(phone1);
		examples.setPhone2(phone2);
		examples.setAddress(address);
		examples.setYouxiao(1);
		examples.setZhuangtai(zhuangtai);
		examples.setCreatTime(new Date());
		examples.setProbability(probability_temp);
		examples.setCampus(campus_temp);
		examples.setChannel(channel_temp);
		
		return examples;
	}
	public String execute(){
		
		try {
			resultMap.clear();
			saveExamplesService.SaveExamples(InitExamples());	
			resultMap.put("status", 200);
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);

		} catch (Exception e) {
		
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:初始化学生列表失败。参数school："+school+",level:"+level+
					",name:"+name+",now_class:"+now_class+",phone1:"+phone1+",phone2:"+
					phone2+",address:"+address+",zhuangtai:"+zhuangtai+",probability:"+probability
					+",channel:"+channel+",campus:"+campus+",MESSAGE:"+e.getMessage());
			return "error";
		}
		return null;
	}
	
}
