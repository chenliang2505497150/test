package com.yaodingjiaoyu.ServiceImpl.System;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yaodingjiaoyu.Service.System.SystemPower;

public final class SystemPowerImpl implements SystemPower {

	private Map<String, List<String>> power_list = null;
	private Set<String> action_list = null;
	private Logger logger;

	private static SystemPowerImpl singleton = null;

	/**
	 * 初始化power_list
	 */
	private SystemPowerImpl() {
		synchronized (SystemPowerImpl.class) {
			power_list = getPowerList();
			action_list = getActionList();
		}
	}

	public static SystemPowerImpl getInstance() {
		if (singleton == null) {
			synchronized (SystemPowerImpl.class) {
				if (singleton == null) {
					singleton = new SystemPowerImpl();
				}
			}
		}
		return singleton;
	}

	/**
	 * getResource()方法会去classpath下找这个文件，获取到url resource,
	 * 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List<String>> getPowerList() {
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		String SYSTEM_POWER_LIST = "powerContext.xml";

		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader.getResource(SYSTEM_POWER_LIST);
		File xmlFile = new File(url.getFile());
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(xmlFile);
			Element employees = document.getRootElement();

			for (Iterator i = employees.elementIterator(); i.hasNext();) {
				Element employee = (Element) i.next();
				List<String> tmp = new ArrayList<String>();
				for (Iterator j = employee.elementIterator(); j.hasNext();) {
					Element node = (Element) j.next();
					tmp.add(node.getText());
				}
				result.put(employee.attributeValue("name"), tmp);
			}

			return result;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数SYSTEM_POWER_LIST:" + SYSTEM_POWER_LIST
					+ ",MESSAGE:" + e.getStackTrace());
			return null;
		}

	}

	/**
	 * 判断是否是合法的请求，即判断Map中有没有这个key-value, 如何让power_list初始化一次，尚未解决
	 */
	@Override
	public boolean isLegal(String power, String actionPath) {
		try {
			List<String> list = power_list.get(power);
			return list.contains(actionPath);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数power:" + power + ",actionPath:" + actionPath
					+ ",MESSAGE:" + e.getStackTrace());
			return false;
		}
	}

	@Override
	public Set<String> getActionList() {
		try {
			Set<String> result = new HashSet<String>();
			for (List<String> list : power_list.values()) {
				result.addAll(list);
			}
			return result;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。MESSAGE:" + e.getStackTrace());
			return null;
		}
	}

	@Override
	public boolean isLegalAction(String actionPath) {
		try {
			return action_list.contains(actionPath);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数actionPath:" + actionPath + ",actionPath:"
					+ actionPath + ",MESSAGE:" + e.getStackTrace());
			return false;
		}
	}

}
