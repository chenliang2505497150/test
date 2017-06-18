package com.yaodingjiaoyu.ajax.sd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ExamplesService;
import com.yaodingjiaoyu.datebase.pojo.Examples;
import net.sf.json.JSONObject;

public class Sd_DistributionExamples_Ajax {
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private int cc;
	private String examples_list_json;
	private ExamplesService examplesService;

	public void setExamplesService(ExamplesService examplesService) {
		this.examplesService = examplesService;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

	public String getExamples_list_json() {
		return examples_list_json;
	}

	public void setExamples_list_json(String examples_list_json) {
		this.examples_list_json = examples_list_json;
	}

	// 分配例子的方法
	@SuppressWarnings("rawtypes")
	private void doDistributionExamples() {
		// 首先需要把JSON转成数组
		JSONObject jsonObject = JSONObject.fromObject(examples_list_json);
		Iterator it = jsonObject.keys();

		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			String value = (String) jsonObject.get(key);
			// 将这些学生分配给指定的老师
			Examples examples = examplesService.findById(Integer.parseInt(value));
			if (examples != null) {
				examplesService.SdDistributionExamples(examples, cc);
			}

		}

		resultMap.put("status", 200);
		resultMap.put("message", "分配完成");
	}

	public String execute() {

		try {
			resultMap.clear();
			doDistributionExamples();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:初始化学生列表失败。参数cc：" + cc + ",examples_list_json:"
					+ examples_list_json + ",SdId:" + ActionContext.getContext().getSession().get("ID") + ",MESSAGE:"
					+ e.getMessage());
			return "error";
		}

		return null;
	}
}
