package com.yaodingjiaoyu.Utils;

import org.apache.log4j.Logger;

public final class Util {

	private static Util singleton = null;
	private Logger logger;

	private Util() {

	}

	public static Util getInstance() {
		if (singleton == null) {
			synchronized (Util.class) {
				if (singleton == null) {
					singleton = new Util();
				}
			}
		}
		return singleton;
	}

	/**
	 * get postfix of the path
	 * 
	 * @param path
	 * @return
	 */
	public String getPostfix(String path) {
		try {
			if (path == null || Common.EMPTY.equals(path.trim())) {
				return Common.EMPTY;
			}
			if (path.contains(Common.POINT)) {
				return path.substring(path.lastIndexOf(Common.POINT) + 1, path.length());
			}
			return Common.EMPTY;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数path:" + path + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}
}
