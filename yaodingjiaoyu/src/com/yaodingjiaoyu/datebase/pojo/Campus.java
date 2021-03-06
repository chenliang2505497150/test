package com.yaodingjiaoyu.datebase.pojo;
// Generated 2016-7-26 22:27:35 by Hibernate Tools 3.5.0.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Campus generated by hbm2java
 */
@SuppressWarnings("serial")
public class Campus implements java.io.Serializable {

	private Integer PId;
	private String name;
	private Set<Stuff> stuffs = new HashSet<Stuff>(0);
	private Set<CampusPrice> campusPrices = new HashSet<CampusPrice>(0);
	private Set<Student> students = new HashSet<Student>(0);
	private Set<ClassContent> classContents = new HashSet<ClassContent>(0);
	private Set<Examples> exampleses = new HashSet<Examples>(0);
	private Set<CcContent> ccContents = new HashSet<CcContent>(0);
	private Set<ClassTable> classTables = new HashSet<ClassTable>(0);
	private Set<CrPlan> crPlans = new HashSet<CrPlan>(0);
	private Set<CcPlan> ccPlans = new HashSet<CcPlan>(0);
	private Set<Suggest> suggests = new HashSet<Suggest>(0);
	private Set<Hetong> hetongs = new HashSet<Hetong>(0);

	public Campus() {
	}

	public Campus(String name) {
		this.name = name;
	}

	public Campus(String name, Set<Stuff> stuffs, Set<CampusPrice> campusPrices, Set<Student> students, Set<ClassContent> classContents, Set<Examples> exampleses,
			Set<CcContent> ccContents, Set<ClassTable> classTables, Set<CrPlan> crPlans, Set<CcPlan> ccPlans, Set<Suggest> suggests, Set<Hetong> hetongs) {
		this.name = name;
		this.stuffs = stuffs;
		this.campusPrices = campusPrices;
		this.students = students;
		this.classContents = classContents;
		this.exampleses = exampleses;
		this.ccContents = ccContents;
		this.classTables = classTables;
		this.crPlans = crPlans;
		this.ccPlans = ccPlans;
		this.suggests = suggests;
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

	public Set<Stuff> getStuffs() {
		return this.stuffs;
	}

	public void setStuffs(Set<Stuff> stuffs) {
		this.stuffs = stuffs;
	}

	public Set<CampusPrice> getCampusPrices() {
		return this.campusPrices;
	}

	public void setCampusPrices(Set<CampusPrice> campusPrices) {
		this.campusPrices = campusPrices;
	}

	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<ClassContent> getClassContents() {
		return this.classContents;
	}

	public void setClassContents(Set<ClassContent> classContents) {
		this.classContents = classContents;
	}

	public Set<Examples> getExampleses() {
		return this.exampleses;
	}

	public void setExampleses(Set<Examples> exampleses) {
		this.exampleses = exampleses;
	}

	public Set<CcContent> getCcContents() {
		return this.ccContents;
	}

	public void setCcContents(Set<CcContent> ccContents) {
		this.ccContents = ccContents;
	}

	public Set<ClassTable> getClassTables() {
		return this.classTables;
	}

	public void setClassTables(Set<ClassTable> classTables) {
		this.classTables = classTables;
	}

	public Set<CrPlan> getCrPlans() {
		return this.crPlans;
	}

	public void setCrPlans(Set<CrPlan> crPlans) {
		this.crPlans = crPlans;
	}

	public Set<CcPlan> getCcPlans() {
		return this.ccPlans;
	}

	public void setCcPlans(Set<CcPlan> ccPlans) {
		this.ccPlans = ccPlans;
	}

	public Set<Suggest> getSuggests() {
		return this.suggests;
	}

	public void setSuggests(Set<Suggest> suggests) {
		this.suggests = suggests;
	}

	public Set<Hetong> getHetongs() {
		return this.hetongs;
	}

	public void setHetongs(Set<Hetong> hetongs) {
		this.hetongs = hetongs;
	}

}
