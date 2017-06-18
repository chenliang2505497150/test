package com.yaodingjiaoyu.daoImpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yaodingjiaoyu.dao.JobDao;
import com.yaodingjiaoyu.datebase.pojo.Job;

public class JobDaoImpl implements JobDao {
	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Job getJobById(int id) {

		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Job job = (Job) session.get(Job.class, id);
			session.getTransaction().commit();
			return job;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->getJobById:运行失败。MESSAGE:" + e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

}
