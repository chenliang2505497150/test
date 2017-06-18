package com.yaodingjiaoyu.datebase.pojo;
// Generated 2016-7-26 22:27:35 by Hibernate Tools 3.5.0.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Job generated by hbm2java
 */
@SuppressWarnings("serial")
public class Job implements java.io.Serializable {

	private Integer PId;
	private String name;
	private String key;
	private Set<Stuff> stuffs = new HashSet<Stuff>(0);
	private Set<ClassContent> classContents = new HashSet<ClassContent>(0);

	public Job() {
	}

	public Job(String name, String key) {
		this.name = name;
		this.key = key;
	}

	public Job(String name, String key, Set<Stuff> stuffs, Set<ClassContent> classContents) {
		this.name = name;
		this.key = key;
		this.stuffs = stuffs;
		this.classContents = classContents;
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

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Set<Stuff> getStuffs() {
		return this.stuffs;
	}

	public void setStuffs(Set<Stuff> stuffs) {
		this.stuffs = stuffs;
	}

	public Set<ClassContent> getClassContents() {
		return this.classContents;
	}

	public void setClassContents(Set<ClassContent> classContents) {
		this.classContents = classContents;
	}

}
