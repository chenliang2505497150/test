package com.yaodingjiaoyu.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yaodingjiaoyu.dao.ClassContentDao;
import com.yaodingjiaoyu.datebase.pojo.ClassContent;

public class ClassContentDaoImpl implements ClassContentDao {

	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// 这是管理员的查询
	@SuppressWarnings("unchecked")
	@Override
	public List<ClassContent> findClassContentByAdmin(String campus, String time1, String time2, String stuff_id,
			String name) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();

		try {
			strSQL.append(
					"from ClassContent as cc left join fetch cc.student  left join fetch cc.stuff left join fetch cc.campus left join fetch cc.job where 1=1");

			if (!("no".equals(campus))) {
				strSQL.append(" and cc.campus = :campus");
			}
			if (!("no".equals(stuff_id))) {
				strSQL.append(" and cc.stuff = :stuff_id");
			}
			if (!("".equals(time1))) {
				strSQL.append(" and cc.insertTime >= :time1");
			}
			if (!("".equals(time2))) {
				strSQL.append(" and cc.insertTime <= :time2");
			}
			if (!("".equals(name))) {
				strSQL.append(" and cc.student.name like :name");
			}

			strSQL.append(" order by cc.insertTime desc");
			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("no".equals(campus))) {
				query.setString("campus", campus);
			}
			if (!("no".equals(stuff_id))) {
				query.setString("stuff_id", stuff_id);
			}
			if (!("".equals(time1))) {
				query.setString("time1", time1);
			}
			if (!("".equals(time2))) {
				query.setString("time2", time2);
			}
			if (!("".equals(name))) {
				query.setString("name", "%" + name + "%");
			}

			List<ClassContent> list = query.list();

			session.getTransaction().commit();
			if (list != null && list.size() >= 1) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:"
					+ e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassContent> findClassContentByCr(String campus, String time1, String time2, String Cr,
			String stuff_id, String name) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"from ClassContent as cc left join fetch cc.student  left join fetch cc.stuff left join fetch cc.campus left join fetch cc.job where 1=1");

			if (!("no".equals(campus))) {
				strSQL.append(" and cc.campus = :campus");
			}
			if (!("no".equals(stuff_id))) {
				strSQL.append(" and cc.stuff = :stuff_id");
			}
			if (!("".equals(time1))) {
				strSQL.append(" and cc.insertTime >= :time1");
			}
			if (!("".equals(time2))) {
				strSQL.append(" and cc.insertTime <= :time2");
			}
			if (!("".equals(name))) {
				strSQL.append(" and cc.student.name like :name");
			}
			// 限定是这个班主任的学生
			strSQL.append(" and cc.student.stuff = :Cr");

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("no".equals(campus))) {
				query.setString("campus", campus);
			}
			if (!("no".equals(stuff_id))) {
				query.setString("stuff_id", stuff_id);
			}
			if (!("".equals(time1))) {
				query.setString("time1", time1);
			}
			if (!("".equals(time2))) {
				query.setString("time2", time2);
			}
			if (!("".equals(name))) {
				query.setString("name", "%" + name + "%");
			}
			query.setString("Cr", Cr);

			List<ClassContent> list = query.list();

			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:"
					+ e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassContent> findCrContentByStudentId(String id, String stuff, String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"from ClassContent as cc left join fetch cc.student  left join fetch cc.stuff left join fetch cc.campus left join fetch cc.job where 1=1");

			if (!("no".equals(campus))) {
				strSQL.append(" and cc.campus = :campus");
			}
			if (!("no".equals(stuff))) {
				strSQL.append(" and cc.student.stuff.PId = :stuff");
			}

			strSQL.append(" and cc.student.PId = :id");
			strSQL.append(" order by cc.insertTime desc");
			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("no".equals(campus))) {
				query.setString("campus", campus);
			}
			if (!("no".equals(stuff))) {
				query.setString("stuff", stuff);
			}

			query.setString("id", id);

			List<ClassContent> list = query.list();

			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:"
					+ e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public int addContent(ClassContent classContent) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(classContent);
			session.getTransaction().commit();
			return classContent.getPId();
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
	public List<ClassContent> findTrContentByStudentId(String id, String stuff, String campus) {

		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"from ClassContent as cc left join fetch cc.student  left join fetch cc.stuff left join fetch cc.campus left join fetch cc.job where 1=1");

			if (!("no".equals(campus))) {
				strSQL.append(" and cc.campus = :campus");
			}
			if (!("no".equals(stuff))) {
				strSQL.append(" and cc.stuff.PId = :stuff");
			}
			strSQL.append(" and cc.student.PId = :id");
			strSQL.append(" order by cc.insertTime desc");
			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("no".equals(campus))) {
				query.setString("campus", campus);
			}
			if (!("no".equals(stuff))) {
				query.setString("stuff", stuff);
			}

			query.setString("id", id);

			List<ClassContent> list = query.list();

			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:"
					+ e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

}
