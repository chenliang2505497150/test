package com.yaodingjiaoyu.action;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.EncryptionService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class ChangePassword_Ajax {

	private String old_pass;
	private String new_pass;
	private UserService stuffService;
	private UserService studentService;
	private EncryptionService encryptionService;

	private Map<String, Object> resultMap = new HashMap<String, Object>();

	public void setOld_pass(String old_pass) {
		this.old_pass = old_pass;
	}

	public void setNew_pass(String new_pass) {
		this.new_pass = new_pass;
	}

	public String getOld_pass() {
		return old_pass;
	}

	public String getNew_pass() {
		return new_pass;
	}

	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
	}

	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}

	private boolean studentCheckPassword(Student student) {

		// 处理学生的修改密码
		String username = student.getUsername();
		// 先对密码进行加密
		String password = encryptionService.My_MD5(username + "+" + old_pass);
		if (null != studentService.CheckLogin(username, password)) {
			return true;
		}
		return false;

	}

	private boolean stuffCheckPassword(Stuff stuff) {

		String username = stuff.getUsername();
		// 先对密码进行加密
		String password = encryptionService.My_MD5(username + "+" + old_pass);
		if (null != stuffService.CheckLogin(username, password)) {
			return true;
		}
		return false;

	}

	private void studentChangePassword(Student student) {

		// 处理学生的修改密码
		String username = student.getUsername();
		// 先对密码进行加密
		String password = encryptionService.My_MD5(username + "+" + new_pass);
		student.setPassword(password);
		if (studentService.update(student)) {
			resultMap.put("status", 200);
			resultMap.put("message", "修改密码成功!");
		} else {
			resultMap.put("status", 201);
			resultMap.put("message", "修改密码失败!");
		}

	}

	private void stuffChangePassword(Stuff stuff) {

		String username = stuff.getUsername();
		// 先对密码进行加密
		String password =encryptionService.My_MD5(username + "+" + new_pass);
		stuff.setPassword(password);

		if (stuffService.update(stuff)) {
			resultMap.put("status", 200);
			resultMap.put("message", "修改密码成功!");
		} else {
			resultMap.put("status", 201);
			resultMap.put("message", "修改密码失败!");
		}

	}

	private void doAction(String power, int id) {
		if ("ss".equals(power)) {
			Student student = (Student) studentService.getUser(id);
			if (studentCheckPassword(student)) {
				studentChangePassword(student);
			} else {
				resultMap.put("status", 202);
				resultMap.put("message", "密码错误!");
			}
		} else {
			Stuff stuff = (Stuff) stuffService.getUser(id);
			if (stuffCheckPassword(stuff)) {
				stuffChangePassword(stuff);
			} else {
				resultMap.put("status", 202);
				resultMap.put("message", "密码错误!");
			}
		}
	}

	public String execute() {

		try {
			resultMap.clear();
			int id = (int) ActionContext.getContext().getSession().get("ID");
			String power = ActionContext.getContext().getSession().get("power").toString();
			doAction(power, id);

			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);

		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:处理修改密码请求失败。参数:" + "ID:"
					+ ActionContext.getContext().getSession().get("ID") + ",old_pass:" + old_pass + ",new_pass:"
					+ new_pass + ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;
	}

}
