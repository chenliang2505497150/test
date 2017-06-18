package com.yaodingjiaoyu.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.EncryptionService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class LoginAction {

	private String username;
	private String password;
	private UserService stuffService;// 在这里有所有员工的服务
	private UserService studentService;// 在这里有所有学生的服务
	private String power;
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private EncryptionService encryptionService;
	private Logger logger; // 日志

	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
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

	public UserService getStuffService() {
		return stuffService;
	}

	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
	}

	public UserService getStudentService() {
		return studentService;
	}

	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}

	// 清除SESSION以及MAP
	public void CleanMap() {
		try {
			power = "fail";// 密码错误，跳转回登录页面
			// 清空SESSION
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.removeAttribute("username");
			session.removeAttribute("power");
			session.removeAttribute("ID");
			session.removeAttribute("campus");

			resultMap.put("status", 204);// 用户名或密码错误
			resultMap.remove("username");
			resultMap.remove("ID");
			resultMap.put("power", power);// 用户名或密码错误
			resultMap.remove("campus");
		} catch (Exception e) {
			// 此处应该记录日志,使用Log4j:清空SESSION失败

			logger.error(this.getClass().getName() + "-->CleanMap:清空SESSION失败,参数：" + "username:" + username
					+ ",password:" + password);
		}

	}

	// 记录SESSION以及MAP
	public boolean Dosave(String power, int ID, int campus) {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();

			// 设置SESSION，并设置SESSION的失效时间
			session.setMaxInactiveInterval(Integer
					.parseInt(ServletActionContext.getServletContext().getInitParameter("SESSION_DESTORY_TIME")));

			session.setAttribute("username", username);
			session.setAttribute("power", power);
			session.setAttribute("ID", ID);
			session.setAttribute("campus", campus);

			resultMap.put("username", username);
			resultMap.put("power", power);
			resultMap.put("ID", ID);
			resultMap.put("campus", campus);
			resultMap.put("status", 200);
			return true;
		} catch (Exception e) {
			// 此处应该记录日志,使用Log4j:保存SESSION失败

			logger.error(this.getClass().getName() + "-->Dosave:保存SESSION失败,参数：" + "username:" + username + ",password:"
					+ password);

			return false;
		}

	}

	// 检查是否是员工
	public boolean CheckStuff(String username, String password) {
		try {
			Stuff stuff = (Stuff) stuffService.CheckLogin(username, password);

			if (stuff != null) {
				if(stuff.getStatus() == 0) {
					Dosave(stuff.getPower(), stuff.getPId(), stuff.getCampus().getPId());
					resultMap.put("message", "login success");
				} else {
					//账户被冻结
					resultMap.put("message", "该账户已被冻结");
					return false;
				}
				
				return true;
			} else {
				resultMap.put("message", "用户名或密码错误");
				return false;
			}
		} catch (Exception e) {
			// 检查账户是否为员工导致异常

			logger.error(this.getClass().getName() + "-->CheckStuff:处理登陆请求失败。参数:" + "username:" + username
					+ ",password:" + password);

			return false;
		}

	}

	// 检查是否是学生
	public boolean CheckStudent(String username, String password) {
		try {

			Student student = (Student) studentService.CheckLogin(username, password);

			if (student != null) {
				resultMap.put("message", "登陆成功");
				return Dosave(student.getPower(), student.getPId(), student.getCampus().getPId());
			}

		} catch (Exception e) {

			logger.error(this.getClass().getName() + "-->CheckStudent:处理登陆请求失败。参数:" + "username:" + username
					+ ",password:" + password);
			return false;
		}

		return false;

	}

	// 用户登录的处理函数
	public String execute() {

		try {
			resultMap.remove("message");
			// 初始化日志
			logger = Logger.getLogger(this.getClass());

			// 先对密码进行加密
			password = encryptionService.My_MD5(username + "+" + password);

			if (!(CheckStudent(username, password) || CheckStuff(username, password))) {
				CleanMap();
			}

			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 处理登陆请求失败,并记录导致改异常的参数

			logger.error(this.getClass().getName() + "-->execute:处理登陆请求失败。参数:" + "username:" + username + ",password:"
					+ password);
		}
		return resultMap.get("power").toString();
	}

}
