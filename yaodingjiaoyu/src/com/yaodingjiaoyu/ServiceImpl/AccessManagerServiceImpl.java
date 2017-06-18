package com.yaodingjiaoyu.ServiceImpl;

import org.apache.log4j.Logger;
import com.yaodingjiaoyu.Service.AccessManagerService;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class AccessManagerServiceImpl implements AccessManagerService {

	private Logger logger;

	@Override
	public boolean SdToStuff(String power, int campus, Stuff stuff) {

		try {
			if ("sd".equals(power)) {
				// 判断发出该请求的用户有没有权限访问该职工信息,只需要判断校区是否相同即可
				if ((!"sd".equals(stuff.getPower())) && (!"admin".equals(stuff.getPower()))) {
					// 如果校区不同，则返回空
					if (!(campus == stuff.getCampus().getPId())) {
						return false;
					} else {
						return true;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->SdToStuff:运行失败。参数power:" + power + ",campus:" + campus
					+ ",stuff_id:" + stuff.getPId() + ",MESSAGE:" + e.getMessage());
			return false;
		}
	}

}
