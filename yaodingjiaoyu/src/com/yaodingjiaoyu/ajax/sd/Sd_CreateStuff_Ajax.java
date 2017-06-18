package com.yaodingjiaoyu.ajax.sd;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.EncryptionService;
import com.yaodingjiaoyu.Service.JobService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.Job;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class Sd_CreateStuff_Ajax {
	private String sex;
	private String ID_card;
	private String phone;
	private int job;
	private String jingji_phone;
	private String username;
	private String password;
	private int part_time;
	private String address;
	private String name;
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private UserService stuffService;
	private EncryptionService encryptionService;
	private JobService jobService;

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getID_card() {
		return ID_card;
	}

	public void setID_card(String iD_card) {
		ID_card = iD_card;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}

	public String getJingji_phone() {
		return jingji_phone;
	}

	public void setJingji_phone(String jingji_phone) {
		this.jingji_phone = jingji_phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPart_time() {
		return part_time;
	}

	public void setPart_time(int part_time) {
		this.part_time = part_time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private void doAddStuff(int campus) {
		// 需要先检查该账号是否已经被注册
		if (stuffService.checkSignIn(username)) {
			// 创建账号
			Stuff stuff = new Stuff();
			stuff.setName(name);
			stuff.setSex(sex);
			stuff.setIdCard(ID_card);
			stuff.setPhone(phone);

			Job job_tmp = jobService.getJobById(job);

			stuff.setJob(job_tmp);
			stuff.setJingjiPhone(jingji_phone);
			stuff.setUsername(username);
			stuff.setPassword(encryptionService.My_MD5(username + "+" + password));

			Campus campus_tmp = new Campus();
			campus_tmp.setPId(campus);
			stuff.setCampus(campus_tmp);
			stuff.setPartTime(part_time);
			stuff.setAddress(address);
			stuff.setPower(job_tmp.getKey());
			stuff.setStatus(0);
			stuffService.save(stuff);
			
			resultMap.put("message", "注册成功!");
			resultMap.put("status", 200);
		} else {
			resultMap.put("message", "该用户名已经被创建!");
			resultMap.put("status", 201);
		}
	}

	public String execute() {

		try {
			int campus = (int) ActionContext.getContext().getSession().get("campus");
			resultMap.clear();
			doAddStuff(campus);
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);

		} catch (Exception e) {
			resultMap.put("message", "注册时发生错误!");
			resultMap.put("status", 501);
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:创建员工账号。参数ID_card：" + ID_card + ",name:" + name
					+ ",sex:" + sex + ",phone:" + phone + ",job:" + job + ",jingji_phone:" + jingji_phone + ",username:"
					+ username + ",password:" + password  + ",address:" + address + ",part_time:"
					+ part_time + ",address:" + address);

			return "error";
		}
		return null;
	}
}
