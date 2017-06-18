package com.yaodingjiaoyu.Service;

import com.yaodingjiaoyu.datebase.pojo.Stuff;

public interface AccessManagerService {
	/**
	 * 
	 * @param power 存储在session中的权限
	 * @param campus	存储在session中的校区编号
	 * @param stuff_id 职工的编号
	 * @return 有权限返回true
	 */
	public boolean SdToStuff(String power,int campus,Stuff stuff);

}
