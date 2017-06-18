package com.yaodingjiaoyu.Service;

import com.yaodingjiaoyu.datebase.pojo.Stuff;

public interface SaveStuffService {
	/**
	 * 该方法是校长用户修改本校区员工信息时调用的,其他情况下谨慎使用
	 * 
	 * @param stuff
	 *            传入的员工对象
	 * @param name
	 *            修改后的员工姓名
	 * @param sex
	 *            员工的性别
	 * @param id_Card
	 *            员工的身份证号码
	 * @param phone
	 *            员工的电话
	 * @param address
	 *            员工的住址
	 * @param jingji_phone
	 *            员工的紧急联系号码
	 * @param job
	 *            员工的岗位
	 * @param username
	 *            员工的用户名
	 * @param status
	 *            员工的冻结状态
	 * @param part_time
	 *            员工是否是兼职
	 * @return true代表修改成功
	 */
	public boolean updateBySd(Stuff stuff, String name, String sex, String id_Card, String phone, String address,
			String jingji_phone, int job, String username, int status, int part_time);
	
	/**
	 * 冻结员工账户
	 * @param pid 需要冻结账户的pid
	 * @return
	 */
	public boolean freezeStuffBySd(Stuff stuff);
	
	/**
	 * 解除冻结员工账户
	 * @param pid 需要冻结账户的pid
	 * @return
	 */
	public boolean unfreezeStuffBySd(Stuff stuff);
}
