package com.yaodingjiaoyu.ajax.sd;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.AccessManagerService;
import com.yaodingjiaoyu.Service.SaveStuffService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

/**
 * 这个类用于处理修改员工信息的请求
 * 
 * @author chenliang
 *
 */
public class Sd_ChangeStuff_Ajax {

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private SaveStuffService saveStuffService;
	private UserService stuffService;

	private AccessManagerService accessManagerService;

	public void setAccessManagerService(AccessManagerService accessManagerService) {
		this.accessManagerService = accessManagerService;
	}

	private int id;
	private String name;
	private String sex;
	private String id_Card;
	private String phone;
	private String address;
	private int job;
	private int status;
	private int part_time;
	private String jingji_phone;
	private String username;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getId_Card() {
		return id_Card;
	}

	public void setId_Card(String id_Card) {
		this.id_Card = id_Card;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPart_time() {
		return part_time;
	}

	public void setPart_time(int part_time) {
		this.part_time = part_time;
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

	public void setSaveStuffService(SaveStuffService saveStuffService) {
		this.saveStuffService = saveStuffService;
	}

	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
	}

	public void changeStuff() {
		boolean success = false;
		// 通过编号获得原来的例子
		Stuff stuff = (Stuff) stuffService.getUser(id);
		// 获得存储在SESSION中的校区编号
		int campus = (int) ActionContext.getContext().getSession().get("campus");
		String power = ActionContext.getContext().getSession().get("power").toString();

		if (null != stuff) {
			if (!accessManagerService.SdToStuff(power, campus, stuff)) {
				resultMap.put("status", 400);// 失败
				resultMap.put("message", "没有权限!");
			} else {
				success = saveStuffService.updateBySd(stuff, name, sex, id_Card, phone, address, jingji_phone, job,
						username, status, part_time);
				// 返回信息
				if (success) {
					resultMap.put("status", 200);
					resultMap.put("message", "修改成功!");
				} else {
					resultMap.put("status", 400);// 失败
					resultMap.put("message", "修改失败!");
				}
			}

		} else {
			resultMap.put("status", 400);// 失败
			resultMap.put("message", "没有权限!");
		}

	}

	public String execute() {

		try {
			resultMap.clear();
			changeStuff();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:修改员工信息失败。参数id：" + id + ",name:" + name + ",sex:" + sex
					+ ",id_Card:" + id_Card + ",phone:" + phone + ",address:" + address + ",job:" + job + ",status:"
					+ status + ",part_time:" + part_time + ",jingji_phone:" + jingji_phone + ",username:" + username
					+ ",SdId:" + ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;
	}
}
