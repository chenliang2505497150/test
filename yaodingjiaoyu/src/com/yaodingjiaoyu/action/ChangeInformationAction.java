package com.yaodingjiaoyu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class ChangeInformationAction {

	private UserService stuffService;
	private UserService studentService;

	private Stuff stuff;
	private Student student;

	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}

	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
	}

	public Stuff getStuff() {
		return stuff;
	}

	public void setStuff(Stuff stuff) {
		this.stuff = stuff;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String execute() {
		// 通过SESSION获得员工的ID，根据ID查询员工对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			int id = (int) session.getAttribute("ID");
			String power = session.getAttribute("power").toString();
			if ("ss".equals(power)) {
				student = (Student) studentService.getUser(id);
				return "student";
			} else {
				stuff = (Stuff) stuffService.getUser(id);
				return "stuff";
			}

		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:初始化列表失败。参数:" + "ID:" + session.getAttribute("ID"));
			return "error";
		}
	}
}
