package com.yaodingjiaoyu.datebase.pojo;
// Generated 2016-7-26 22:27:35 by Hibernate Tools 3.5.0.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ClassTable generated by hbm2java
 */
@SuppressWarnings("serial")
public class ClassTable implements java.io.Serializable {

	private Integer PId;
	private Student student;
	private Stuff stuff;
	private CourseType courseType;
	private Campus campus;
	private Level level;
	private ClassTime classTime;
	private Subject subject;
	private Date dayTime;
	private String remarks;
	private int status;
	private Set<Suggest> suggests = new HashSet<Suggest>(0);

	public ClassTable() {
	}

	public ClassTable(Student student, Stuff stuff, CourseType courseType, Campus campus, Level level,
			ClassTime classTime, Subject subject, Date dayTime, int status) {
		this.student = student;
		this.stuff = stuff;
		this.courseType = courseType;
		this.campus = campus;
		this.level = level;
		this.classTime = classTime;
		this.subject = subject;
		this.dayTime = dayTime;
		this.status = status;
	}

	public ClassTable(Student student, Stuff stuff, CourseType courseType, Campus campus, Level level,
			ClassTime classTime, Subject subject, Date dayTime, String remarks, int status, Set<Suggest> suggests) {
		this.student = student;
		this.stuff = stuff;
		this.courseType = courseType;
		this.campus = campus;
		this.level = level;
		this.classTime = classTime;
		this.subject = subject;
		this.dayTime = dayTime;
		this.remarks = remarks;
		this.status = status;
		this.suggests = suggests;
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

	public Stuff getStuff() {
		return this.stuff;
	}

	public void setStuff(Stuff stuff) {
		this.stuff = stuff;
	}

	public CourseType getCourseType() {
		return this.courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public Campus getCampus() {
		return this.campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public Level getLevel() {
		return this.level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public ClassTime getClassTime() {
		return this.classTime;
	}

	public void setClassTime(ClassTime classTime) {
		this.classTime = classTime;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Date getDayTime() {
		return this.dayTime;
	}

	public void setDayTime(Date dayTime) {
		this.dayTime = dayTime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<Suggest> getSuggests() {
		return this.suggests;
	}

	public void setSuggests(Set<Suggest> suggests) {
		this.suggests = suggests;
	}

}
