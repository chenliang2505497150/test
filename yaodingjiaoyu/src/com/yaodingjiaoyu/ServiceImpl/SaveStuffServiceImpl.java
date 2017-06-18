package com.yaodingjiaoyu.ServiceImpl;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.JobService;
import com.yaodingjiaoyu.Service.SaveStuffService;
import com.yaodingjiaoyu.dao.UserDao;
import com.yaodingjiaoyu.datebase.pojo.Job;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class SaveStuffServiceImpl implements SaveStuffService {
	private UserDao userDao;
	private Logger logger;
	private JobService jobService;

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean updateBySd(Stuff stuff, String name, String sex, String id_Card, String phone, String address,
			String jingji_phone, int job, String username, int status, int part_time) {
		try {
			stuff.setName(name);
			stuff.setSex(sex);
			stuff.setIdCard(id_Card);
			;
			stuff.setPhone(phone);
			stuff.setAddress(address);
			stuff.setJingjiPhone(jingji_phone);

			Job job_temp = jobService.getJobById(job);
			stuff.setJob(job_temp);

			stuff.setPower(job_temp.getKey());

			stuff.setUsername(username);
			stuff.setStatus(status);
			stuff.setPartTime(part_time);

			return userDao.update(stuff);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->updateBySd:运行失败。参数sex:" + sex + ",name:" + name + ",id_Card:"
					+ id_Card + ",phone:" + phone + ",address:" + address + ",jingji_phone:" + jingji_phone + ",job:"
					+ job + ",username:" + username + ",status:" + status + ",part_time:" + part_time + ",stuffId:"
					+ stuff.getPId() + ",MESSAGE:" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean freezeStuffBySd(Stuff stuff) {
		try {
			stuff.setStatus(1);
			return userDao.update(stuff);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->freezeStuffBySd:运行失败。参数pid:" + stuff.getPId() + ",MESSAGE:"
					+ e.getMessage());
			return false;
		}
	}

	@Override
	public boolean unfreezeStuffBySd(Stuff stuff) {
		try {
			stuff.setStatus(0);
			return userDao.update(stuff);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->freezeStuffBySd:运行失败。参数pid:" + stuff.getPId() + ",MESSAGE:"
					+ e.getMessage());
			return false;
		}
	}

}
