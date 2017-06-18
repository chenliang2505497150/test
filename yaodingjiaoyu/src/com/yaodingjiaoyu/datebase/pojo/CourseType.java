package com.yaodingjiaoyu.datebase.pojo;
// Generated 2016-8-14 16:27:35 by Hibernate Tools 3.5.0.Final

import java.util.HashSet;
import java.util.Set;

/**
 * CourseType generated by hbm2java
 */
@SuppressWarnings("serial")
public class CourseType implements java.io.Serializable {

	private Integer PId;
	private String name;
	private int many;
	private String color;
	private Set<CampusPrice> campusPrices = new HashSet<CampusPrice>(0);
	private Set<ClassHour> classHours = new HashSet<ClassHour>(0);
	private Set<ClassTable> classTables = new HashSet<ClassTable>(0);
	private Set<Hetong> hetongs = new HashSet<Hetong>(0);

	public CourseType() {
	}

	public CourseType(String name, int many) {
		this.name = name;
		this.many = many;
	}

	public CourseType(String name, int many, String color, Set<CampusPrice> campusPrices, Set<ClassHour> classHours, Set<ClassTable> classTables,
			Set<Hetong> hetongs) {
		this.name = name;
		this.many = many;
		this.color = color;
		this.campusPrices = campusPrices;
		this.classHours = classHours;
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

	public int getMany() {
		return this.many;
	}

	public void setMany(int many) {
		this.many = many;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Set<CampusPrice> getCampusPrices() {
		return this.campusPrices;
	}

	public void setCampusPrices(Set<CampusPrice> campusPrices) {
		this.campusPrices = campusPrices;
	}

	public Set<ClassHour> getClassHours() {
		return this.classHours;
	}

	public void setClassHours(Set<ClassHour> classHours) {
		this.classHours = classHours;
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
