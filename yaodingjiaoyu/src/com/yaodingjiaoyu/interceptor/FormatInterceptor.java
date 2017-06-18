package com.yaodingjiaoyu.interceptor;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import net.sf.json.JSONArray;

/**
 * 该拦截器主要是对ajax请求中返回的MAP集合序列化成json的处理
 * 
 * @author chenliang
 *
 */
public class FormatInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;
	/**
	 * 上一个action传入的结果，需要进行处理并返回json
	 */

	private Logger logger;

	@SuppressWarnings("unchecked")
	@Override
	protected String doIntercept(ActionInvocation invoker) throws Exception {

		try {
			/**
			 * 不做请求的预处理,如果有预处理则在该注释前添加
			 */
			String result = invoker.invoke();
			/**
			 * 只对返回的请求进行处理
			 */
			Map<String, Object> resultMap = (Map<String, Object>) ActionContext.getContext().get("resultMap");
			if (null != resultMap) {
				JSONArray jsonObject = JSONArray.fromObject(resultMap);
				ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
				ServletActionContext.getResponse().getWriter().print(jsonObject.toString());
			}
			resultMap = null;
			return result;

		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。MESSAGE:" + e.getMessage());
			return "error";
		}
	}

}
