package com.yaodingjiaoyu.datebase.pojo;
// Generated 2016-7-26 22:27:35 by Hibernate Tools 3.5.0.Final

/**
 * ClassHour generated by hbm2java
 */
@SuppressWarnings("serial")
public class ClassHour implements java.io.Serializable {

	private Integer PId;
	private Student student;
	private CourseType courseType;
	private int allHour;
	private int lastHour;

	public ClassHour() {
	}

	public ClassHour(Student student, CourseType courseType, int allHour, int lastHour) {
		this.student = student;
		this.courseType = courseType;
		this.allHour = allHour;
		this.lastHour = lastHour;
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

	public CourseType getCourseType() {
		return this.courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public int getAllHour() {
		return this.allHour;
	}

	public void setAllHour(int allHour) {
		this.allHour = allHour;
	}

	public int getLastHour() {
		return this.lastHour;
	}

	public void setLastHour(int lastHour) {
		this.lastHour = lastHour;
	}

}