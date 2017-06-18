package com.yaodingjiaoyu.ServiceImpl;

import java.util.List;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.GetResultObjectListService;

public class GetResultObjectListServiceImpl implements GetResultObjectListService {
	private Logger logger;

	@Override
	public List<?> getResultObjectList(int start_look, int max_look, List<?> list) {
		try {
			if (list != null && list.size() >= 1) {
				if ((start_look - 1) * max_look >= list.size()) {
					return null;
				} else if (list.size() <= start_look * max_look) {
					return list.subList((start_look - 1) * max_look, list.size());
				} else {
					return list.subList((start_look - 1) * max_look, max_look * start_look);
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数start_look:" + start_look + ",max_look:"
					+ max_look + ",list:" + list + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

}
