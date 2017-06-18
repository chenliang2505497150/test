package com.yaodingjiaoyu.ajax.cc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.CcContentService;
import com.yaodingjiaoyu.Service.ExamplesService;
import com.yaodingjiaoyu.datebase.pojo.Examples;

public class Cc_AddContent_Ajax {

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private int id;
	private String youxiao;
	private String probability;
	private String contents;
	private String zhuangtai;

	private CcContentService ccContentService;
	private ExamplesService examplesService;

	public void setCcContentService(CcContentService ccContentService) {
		this.ccContentService = ccContentService;
	}

	public void setExamplesService(ExamplesService examplesService) {
		this.examplesService = examplesService;
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYouxiao() {
		return youxiao;
	}

	public void setYouxiao(String youxiao) {
		this.youxiao = youxiao;
	}

	public String getProbability() {
		return probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}

	private void doAddContent() {
		int success = 0;
		// 获得存储在SESSION中的校区和职工编号
		int stuff = (int) ActionContext.getContext().getSession().get("ID");
		int campus = (int) ActionContext.getContext().getSession().get("campus");

		Examples examples = examplesService.findById(id);
		Date now = new Date();
		// 并跟新例子的最新一次跟踪时间
		examplesService.CcAddContentUpdateExamples(examples, youxiao, zhuangtai, probability, now);
		// 添加回访
		success = ccContentService.addContent(id, now, contents, campus, stuff);

		if (success != 0) {// 成功
			resultMap.put("status", 200);
			resultMap.put("message", "添加成功!");
		} else {
			resultMap.put("status", 400);
			resultMap.put("message", "添加失败!");
		}
	}

	public String execute() {
		try {
			resultMap.clear();
			doAddContent();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);

		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:初始化学生列表失败。参数id：" + id + ",youxiao:" + youxiao
					+ ",probability:" + probability + ",contents:" + contents);
			return "error";
		}

		return null;
	}
}
