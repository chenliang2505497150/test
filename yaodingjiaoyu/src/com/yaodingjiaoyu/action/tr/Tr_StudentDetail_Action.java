package com.yaodingjiaoyu.action.tr;

import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.SearchStudentService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class Tr_StudentDetail_Action {

	// 判断有没有该学生的课，有就查询，没有就返回空
	private int id;

	private UserService studentService;

	private Map<String, Object> student;

	private TransLateService transLateService;
	private SearchStudentService searchStudentService;

	public void setSearchStudentService(SearchStudentService searchStudentService) {
		this.searchStudentService = searchStudentService;
	}

	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	public Map<String, Object> getStudent() {
		return student;
	}

	public void setStudent(Map<String, Object> student) {
		this.student = student;
	}

	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String doStudentDetail() {
		// 获得存储在SESSION中的校区和职工编号
		String stuff = ActionContext.getContext().getSession().get("ID").toString();
		String campus = ActionContext.getContext().getSession().get("campus").toString();

		boolean success = searchStudentService.TrHaveStudent(id + "", stuff, campus);

		if (success) {
			Student tmp = (Student) studentService.getUser(id);

			if (tmp != null) {
				student = transLateService.transLateStudent(tmp);
			} else {
				return "error";
			}

		} else {
			return "error";
		}

		return "success";
	}

	public String execute() {
		try {
			return doStudentDetail();
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:操作失败。参数TrId:"
					+ ActionContext.getContext().getSession().get("ID") + ",ID:" + id + ",MESSAGE:" + e.getMessage());
			return "error";
		}
	}
}
