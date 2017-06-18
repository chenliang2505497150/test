package com.yaodingjiaoyu.datebase.pojo;
// Generated 2016-7-26 22:27:35 by Hibernate Tools 3.5.0.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Examples generated by hbm2java
 */
@SuppressWarnings("serial")
public class Examples implements java.io.Serializable {

	private Integer PId;
	private Stuff stuff;
	private Channel channel;
	private Campus campus;
	private Level level;
	private Probability probability;
	private String name;
	private String school;
	private Integer nowClass;
	private String phone1;
	private String phone2;
	private String address;
	private int youxiao;
	private int zhuangtai;
	private Date creatTime;
	private int status;
	private Date lastTime;
	private Set<CcContent> ccContents = new HashSet<CcContent>(0);
	private Set<CcPlan> ccPlans = new HashSet<CcPlan>(0);

	public Examples() {
	}

	public Examples(Channel channel, Campus campus, Level level, Probability probability, String name, int youxiao,
			int zhuangtai, Date creatTime, int status) {
		this.channel = channel;
		this.campus = campus;
		this.level = level;
		this.probability = probability;
		this.name = name;
		this.youxiao = youxiao;
		this.zhuangtai = zhuangtai;
		this.creatTime = creatTime;
		this.status = status;
	}

	public Examples(Stuff stuff, Channel channel, Campus campus, Level level, Probability probability, String name,
			String school, Integer nowClass, String phone1, String phone2, String address, int youxiao, int zhuangtai,
			Date creatTime, int status, Date lastTime, Set<CcContent> ccContents, Set<CcPlan> ccPlans) {
		this.stuff = stuff;
		this.channel = channel;
		this.campus = campus;
		this.level = level;
		this.probability = probability;
		this.name = name;
		this.school = school;
		this.nowClass = nowClass;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.address = address;
		this.youxiao = youxiao;
		this.zhuangtai = zhuangtai;
		this.creatTime = creatTime;
		this.status = status;
		this.lastTime = lastTime;
		this.ccContents = ccContents;
		this.ccPlans = ccPlans;
	}

	public Integer getPId() {
		return this.PId;
	}

	public void setPId(Integer PId) {
		this.PId = PId;
	}

	public Stuff getStuff() {
		return this.stuff;
	}

	public void setStuff(Stuff stuff) {
		this.stuff = stuff;
	}

	public Channel getChannel() {
		return this.channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
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

	public Probability getProbability() {
		return this.probability;
	}

	public void setProbability(Probability probability) {
		this.probability = probability;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Integer getNowClass() {
		return this.nowClass;
	}

	public void setNowClass(Integer nowClass) {
		this.nowClass = nowClass;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getYouxiao() {
		return this.youxiao;
	}

	public void setYouxiao(int youxiao) {
		this.youxiao = youxiao;
	}

	public int getZhuangtai() {
		return this.zhuangtai;
	}

	public void setZhuangtai(int zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

	public Date getCreatTime() {
		return this.creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Set<CcContent> getCcContents() {
		return this.ccContents;
	}

	public void setCcContents(Set<CcContent> ccContents) {
		this.ccContents = ccContents;
	}

	public Set<CcPlan> getCcPlans() {
		return this.ccPlans;
	}

	public void setCcPlans(Set<CcPlan> ccPlans) {
		this.ccPlans = ccPlans;
	}

}
