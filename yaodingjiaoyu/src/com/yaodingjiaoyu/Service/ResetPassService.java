package com.yaodingjiaoyu.Service;

import com.yaodingjiaoyu.datebase.pojo.Stuff;

/**
 * 重置密码的服务
 * @author chenliang
 *
 */
public interface ResetPassService {

	/**
	 * 该方法适合校长重置员工密码，其他场合谨慎使用
	 * @param stuff 需要重置密码的员工
	 * @return 员工的新密码
	 */
	public String SdResetStuff(Stuff stuff);
}
