package com.yaodingjiaoyu.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 对所有的请求进行登陆验证
 * 
 * @author chenliang
 *
 */
@SuppressWarnings("serial")
public class LoginInterceptor extends MethodFilterInterceptor {
	private Logger logger;

	@Override
	protected String doIntercept(ActionInvocation invoker) {
		try {
			// 排除登陆请求
			Object userId = ServletActionContext.getRequest().getSession().getAttribute("ID");
			String path = ServletActionContext.getRequest().getServletPath();
			if (null == userId) {
				if ("/Login".equals(path)) {
					return invoker.invoke();
				} else {
					return "login";
				}

			} else {
				// 已经登录过了
				return invoker.invoke();
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。MESSAGE:" + e.getStackTrace());
			return "error";
		}
	}

}
