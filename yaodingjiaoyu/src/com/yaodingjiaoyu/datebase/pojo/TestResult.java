package com.yaodingjiaoyu.datebase.pojo;
// Generated 2016-7-26 22:27:35 by Hibernate Tools 3.5.0.Final

import java.util.Date;

/**
 * TestResult generated by hbm2java
 */
@SuppressWarnings("serial")
public class TestResult implements java.io.Serializable {

	private Integer PId;
	private Student student;
	private Subject subject;
	private int course;
	private Date testTime;
	private String remark;

	public TestResult() {
	}

	public TestResult(Student student, Subject subject, int course, Date testTime) {
		this.student = student;
		this.subject = subject;
		this.course = course;
		this.testTime = testTime;
	}

	public TestResult(Student student, Subject subject, int course, Date testTime, String remark) {
		this.student = student;
		this.subject = subject;
		this.course = course;
		this.testTime = testTime;
		this.remark = remark;
	}

	public Integer getPId() {
		return this.PId;
	}

	public void setPId(Integer PId) {
		this.PId = PId;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getCourse() {
		return this.course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public Date getTestTime() {
		return this.testTime;
	}

	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
