package com.yaodingjiaoyu.ServiceImpl;

import com.yaodingjiaoyu.Service.JobService;
import com.yaodingjiaoyu.dao.JobDao;
import com.yaodingjiaoyu.datebase.pojo.Job;

public class JobServiceImpl implements JobService {

	private JobDao jobdao;

	public JobDao getJobdao() {
		return jobdao;
	}

	public void setJobdao(JobDao jobdao) {
		this.jobdao = jobdao;
	}

	@Override
	public Job getJobById(int id) {
		return jobdao.getJobById(id);
	}

}
