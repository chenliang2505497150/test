package com.yaodingjiaoyu.daoImpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yaodingjiaoyu.dao.CourseTypeDao;
import com.yaodingjiaoyu.datebase.pojo.CourseType;

public class CourseTypeDaoImpl implements CourseTypeDao {
	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int save(CourseType courseType) {
		int result = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(courseType);
			session.getTransaction().commit();

			result = courseType.getPId();
			return result;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。MESSAGE:" + e.getMessage());
			return 0;
		} finally {
			session.close();
		}

	}

	@Override
	public CourseType getCourseType(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			CourseType courseType = (CourseType) session.get(CourseType.class, id);
			session.getTransaction().commit();
			return courseType;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。MESSAGE:" + e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

}
