package com.yaodingjiaoyu.daoImpl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yaodingjiaoyu.dao.ClassHourDao;
import com.yaodingjiaoyu.datebase.pojo.ClassHour;

public class ClassHourDaoImpl implements ClassHourDao {
	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int save(ClassHour ht) {
		int result = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(ht);
			session.getTransaction().commit();
			result = ht.getPId();
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

	@SuppressWarnings("unchecked")
	@Override
	public ClassHour getClassHour(String student, String course_type) {

		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from ClassHour as cc left join fetch cc.student left join fetch cc.courseType  where 1=1");

			if (!("".equals(student))) {
				strSQL.append(" and cc.student.PId = :student");
			}

			if (!("".equals(course_type))) {
				strSQL.append(" and cc.courseType.PId = :course_type");
			}

			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("".equals(student))) {
				query.setString("student", student);
			}

			if (!("".equals(course_type))) {
				query.setString("course_type", course_type);
			}

			List<ClassHour> list = query.list();

			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(
					this.getClass().getName() + "-->execute:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:" + e.getMessage());
			return null;
		} finally {
			session.close();
		}

	}

	@Override
	public boolean update(ClassHour ht) {
		Session session = null;
		boolean success = false;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(ht);
			session.getTransaction().commit();

			success = true;
			return success;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。MESSAGE:" + e.getMessage());
			return false;
		} finally {
			session.close();
		}

	}

}
