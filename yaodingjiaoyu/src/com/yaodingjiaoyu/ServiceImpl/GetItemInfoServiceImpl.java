package com.yaodingjiaoyu.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.GetItemInfoService;

public class GetItemInfoServiceImpl implements GetItemInfoService {
	private Map<String, Integer> item_info;
	private Logger logger;

	public void setItem_info(Map<String, Integer> item_info) {
		this.item_info = item_info;
	}

	@Override
	public Map<String, Integer> getItemInfo(int start_look, int max_look, List<?> list) {
		try {
			item_info.clear();
			int allItem = 0;
			int firstItem = 0;
			int lastItem = 0;
			int all_page = 0;

			if (list != null && list.size() >= 1) {
				allItem = list.size();
				if ((start_look - 1) * max_look >= list.size()) {
					firstItem = 0;
					lastItem = 0;
				} else if (list.size() <= start_look * max_look) {
					firstItem = (start_look - 1) * max_look + 1;
					lastItem = list.size();
				} else {
					firstItem = (start_look - 1) * max_look + 1;
					lastItem = max_look * start_look;
				}
			}

			// 计算总页数
			if (allItem > 0) {
				if ((allItem % max_look) == 0) {
					all_page = (allItem / max_look);
				} else {
					all_page = (allItem / max_look) + 1;
				}
			} else {
				all_page = 1;
			}

			// 将结果加入MAP中
			item_info.put("all_page", all_page);
			item_info.put("lastItem", lastItem);
			item_info.put("firstItem", firstItem);
			item_info.put("allItem", allItem);

			return item_info;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数start_look:" + start_look + ",max_look:"
					+ max_look + ",list:" + list + ",MESSAGE:" + e.getMessage());
			return null;
		}

	}

}
