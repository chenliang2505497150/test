package com.yaodingjiaoyu.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.yaodingjiaoyu.Service.System.SystemPower;
import com.yaodingjiaoyu.ServiceImpl.System.SystemPowerImpl;

public class PowerInterceptor extends MethodFilterInterceptor {
	/**
	 * @author chenliang 主要对访问网站的权限进行控制，不同权限的用户只能访问本权限内的页面 这里是在配置文件中配置各个权限访问的页面
	 */
	public SystemPower systemPower;
	private static final long serialVersionUID = 1L;
	private Logger logger;


	@Override
	protected String doIntercept(ActionInvocation invoker) {

		try {
			systemPower = SystemPowerImpl.getInstance();
			// 之前的过滤器已经过滤了未登陆的请求，因此这里的请求都是登陆过的
			Object power = ServletActionContext.getRequest().getSession().getAttribute("power");
			String path = ServletActionContext.getRequest().getServletPath();
			
			// 未登陆
			if (("/Login".equals(path))) {
				return invoker.invoke();
			}else if(null == power) {
				return "login";
			}else if (systemPower.isLegal(power.toString(), path)) {
				return invoker.invoke();
			} else {
				return "login";
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。MESSAGE:" + e.getMessage());
			return "error";
		}
	}

}
