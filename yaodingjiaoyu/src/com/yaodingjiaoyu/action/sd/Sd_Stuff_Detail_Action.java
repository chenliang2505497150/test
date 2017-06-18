package com.yaodingjiaoyu.action.sd;

import java.util.List;

import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.AccessManagerService;
import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.Job;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

/**
 * 该类用于查看员工详细信息,能访问到该页面的只有校长和管理员 需要岗位列表
 * 
 * @author chenliang
 *
 */
public class Sd_Stuff_Detail_Action {

	private Stuff stuff;
	private int id;
	private UserService stuffService;
	private List<Job> job_list = null;
	private PageListService loadPageListService;
	private AccessManagerService accessManagerService;

	public void setAccessManagerService(AccessManagerService accessManagerService) {
		this.accessManagerService = accessManagerService;
	}

	public List<Job> getJob_list() {
		return job_list;
	}

	public void setJob_list(List<Job> job_list) {
		this.job_list = job_list;
	}

	public void setLoadPageListService(PageListService loadPageListService) {
		this.loadPageListService = loadPageListService;
	}

	public Stuff getStuff() {
		return stuff;
	}

	public void setStuff(Stuff stuff) {
		this.stuff = stuff;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStuffService(UserService stuffService) {
		this.stuffService = stuffService;
	}

	/**
	 * 查询对应id的员工信息,在此不需要判断请求者的权限，因为sd以下权限不可能访问到这个页面
	 */
	private void getStuffDetail() {
		// 获得存储在SESSION中的校区和职工编号
		int campus = (int) ActionContext.getContext().getSession().get("campus");
		String power = ActionContext.getContext().getSession().get("power").toString();
		stuff = (Stuff) stuffService.getUser(id);

		if (null != stuff) {
			if (!accessManagerService.SdToStuff(power, campus, stuff)) {
				stuff = null;
			}
		} else {
			stuff = null;
		}
	}

	public String execute() {

		try {
			// 先获取岗位列表信息
			job_list = loadPageListService.getJob_listForSd();// 获得岗位表，但是要去除管理员和校长
			getStuffDetail();
			return "success";
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:查找员工详情失败。参数ID：" + id + ",MESSAGE:" + e.getMessage());
			return "error";
		}
	}
}
