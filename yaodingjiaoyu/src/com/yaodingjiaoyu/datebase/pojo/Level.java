package com.yaodingjiaoyu.datebase.pojo;
// Generated 2016-7-27 18:10:42 by Hibernate Tools 3.5.0.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Level generated by hbm2java
 */
@SuppressWarnings("serial")
public class Level implements java.io.Serializable {

	private Integer PId;
	private String name;
	private Set<Examples> exampleses = new HashSet<Examples>(0);
	private Set<Student> students = new HashSet<Student>(0);
	private Set<ClassTable> classTables = new HashSet<ClassTable>(0);
	private Set<Hetong> hetongs = new HashSet<Hetong>(0);

	public Level() {
	}

	public Level(String name) {
		this.name = name;
	}

	public Level(String name, Set<Examples> exampleses, Set<Student> students, Set<ClassTable> classTables, Set<Hetong> hetongs) {
		this.name = name;
		this.exampleses = exampleses;
		this.students = students;
		this.classTables = classTables;
		this.hetongs = hetongs;
	}

	public Integer getPId() {
		return this.PId;
	}

	public void setPId(Integer PId) {
		this.PId = PId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Examples> getExampleses() {
		return this.exampleses;
	}

	public void setExampleses(Set<Examples> exampleses) {
		this.exampleses = exampleses;
	}

	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<ClassTable> getClassTables() {
		return this.classTables;
	}

	public void setClassTables(Set<ClassTable> classTables) {
		this.classTables = classTables;
	}

	public Set<Hetong> getHetongs() {
		return this.hetongs;
	}

	public void setHetongs(Set<Hetong> hetongs) {
		this.hetongs = hetongs;
	}

}