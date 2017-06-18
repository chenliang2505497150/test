package com.yaodingjiaoyu.ajax.sd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ExamplesService;
import com.yaodingjiaoyu.Service.GetItemInfoService;
import com.yaodingjiaoyu.Service.GetResultObjectListService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.Channel;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Probability;

public class Sd_SeachExamples_Ajax {
	private String name;
	private String school;
	private String address;
	private String telephone;
	private String now_class;
	private String level;
	private String youxiao;
	private String zhuangtai;
	private String probability;
	private String channel;
	private String cc_total;
	private String stuff;
	private String status;

	private List<Level> level_list;
	private List<Probability> probability_list;
	private List<Channel> channel_list;
	private List<Campus> campus_list;

	private int start_look;// 本次浏览的起始页

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private List<Map<String, Object>> examples_list;
	private Map<String, Integer> item;// 包涵页面的第一个元素序号，最后元素序号，以及总页数的相关信息

	private GetResultObjectListService getResultObjectListService;// 返回对应页面的数据
	private GetItemInfoService getItemInfoService;// 获得第一页，最后一页，总页数
	private ExamplesService searchExamplesService;// 获得例子
	private PageListService loadPageListService;// 获得相关的年级列表，校区列表等
	private TransLateService transLateService; // 用于对MAP进行翻译

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStuff() {
		return stuff;
	}

	public void setStuff(String stuff) {
		this.stuff = stuff;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNow_class() {
		return now_class;
	}

	public void setNow_class(String now_class) {
		this.now_class = now_class;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getYouxiao() {
		return youxiao;
	}

	public void setYouxiao(String youxiao) {
		this.youxiao = youxiao;
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

	public String getProbability() {
		return probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCc_total() {
		return cc_total;
	}

	public void setCc_total(String cc_total) {
		this.cc_total = cc_total;
	}

	public List<Campus> getCampus_list() {
		return campus_list;
	}

	public void setCampus_list(List<Campus> campus_list) {
		this.campus_list = campus_list;
	}

	public int getStart_look() {
		return start_look;
	}

	public void setStart_look(int start_look) {
		this.start_look = start_look;
	}

	public void setGetResultObjectListService(GetResultObjectListService getResultObjectListService) {
		this.getResultObjectListService = getResultObjectListService;
	}

	public void setGetItemInfoService(GetItemInfoService getItemInfoService) {
		this.getItemInfoService = getItemInfoService;
	}

	public void setSearchExamplesService(ExamplesService searchExamplesService) {
		this.searchExamplesService = searchExamplesService;
	}

	public void setLoadPageListService(PageListService loadPageListService) {
		this.loadPageListService = loadPageListService;
	}

	public void setTransLateService(TransLateService transLateService) {
		this.transLateService = transLateService;
	}

	@SuppressWarnings("unchecked")
	private void doSearchExamples() {
		int PAGE_MAX = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("PAGE_MAX"));
		int campus = (int) ActionContext.getContext().getSession().get("campus");
		// 初始化列表
		level_list = loadPageListService.getLevel_list();
		probability_list = loadPageListService.getProbability_list();
		channel_list = loadPageListService.getChannel_list();
		campus_list = loadPageListService.getCampus_list();

		// 未经处理，例如年级还是数字
		List<Map<String, Object>> list = searchExamplesService.findBySdRequest_MAP(name, school, level, now_class,
				telephone, address, youxiao, zhuangtai, probability, channel, campus + "", stuff, cc_total, status);
		// 处理，将部分的内容进行翻译
		list = transLateService.transLateExamples(level_list, probability_list, channel_list, campus_list, list);

		examples_list = (List<Map<String, Object>>) getResultObjectListService.getResultObjectList(start_look, PAGE_MAX,
				list);// 显示要求行数据
		item = getItemInfoService.getItemInfo(start_look, PAGE_MAX, list);
		// 返回数据:先将MAP转成JSON，然后返回数据

		resultMap.put("examplesList", examples_list);
		resultMap.put("all_page", item.get("all_page"));
		resultMap.put("firstItem", item.get("firstItem"));
		resultMap.put("lastItem", item.get("lastItem"));
		resultMap.put("allItem", item.get("allItem"));
	}

	public String execute() {

		try {
			resultMap.clear();// 先清空数据
			doSearchExamples();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:查询例子失败。参数name：" + name + ",stuff:" + stuff
					+ ",cc_total:" + cc_total + ",school:" + school + ",level:" + level + ",now_class:" + now_class
					+ ",telephone:" + telephone + ",youxiao:" + youxiao + ",zhuangtai:" + zhuangtai + ",address:"
					+ address + ",probability:" + probability + ",status:" + status + ",channel:" + channel + ",SdId:"
					+ ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;
	}
}
