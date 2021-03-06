package com.yaodingjiaoyu.datebase.pojo;
// Generated 2016-7-26 22:27:35 by Hibernate Tools 3.5.0.Final

import java.util.HashSet;
import java.util.Set;

/**
 * HtProperty generated by hbm2java
 */
@SuppressWarnings("serial")
public class HtProperty implements java.io.Serializable {

	private Integer PId;
	private String name;
	private Set<Hetong> hetongs = new HashSet<Hetong>(0);

	public HtProperty() {
	}

	public HtProperty(String name) {
		this.name = name;
	}

	public HtProperty(String name, Set<Hetong> hetongs) {
		this.name = name;
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

	public Set<Hetong> getHetongs() {
		return this.hetongs;
	}

	public void setHetongs(Set<Hetong> hetongs) {
		this.hetongs = hetongs;
	}

}
