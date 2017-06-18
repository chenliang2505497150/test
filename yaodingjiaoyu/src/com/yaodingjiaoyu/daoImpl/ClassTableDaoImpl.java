package com.yaodingjiaoyu.daoImpl;

import java.math.BigInteger;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yaodingjiaoyu.dao.ClassTableDao;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class ClassTableDaoImpl implements ClassTableDao {

	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassTable> findClassTableByCr(String time1, String time2, String campus, List<Stuff> stuff) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"from ClassTable as cc left join fetch cc.subject left join fetch cc.level left join fetch cc.campus left join fetch cc.student  left join fetch cc.stuff  left join fetch cc.courseType left join fetch cc.classTime where 1=1");
			if (!("no".equals(campus))) {
				strSQL.append(" and cc.campus = :campus");
			}
			if (!(stuff == null)) {
				strSQL.append(" and ( 1=2");
				// 循环查找
				for (Stuff tmp : stuff) {
					strSQL.append(" or cc.stuff.PId = " + tmp.getPId());
				}
				strSQL.append(" )");
			}
			if (!("".equals(time1))) {
				strSQL.append(" and cc.dayTime >= :time1");
			}
			if (!("".equals(time2))) {
				strSQL.append(" and cc.dayTime <= :time2");
			}
			strSQL.append(" group by cc.PId,cc.stuff.PId");

			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("no".equals(campus))) {
				query.setString("campus", campus);
			}

			if (!("".equals(time1))) {
				query.setString("time1", time1);
			}
			if (!("".equals(time2))) {
				query.setString("time2", time2);
			}

			List<ClassTable> list = query.list();

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
	public int getCount(String student, String course_type) {

		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("select count(*) from class_table cc where 1=1");

			if (!("".equals(student))) {
				strSQL.append(" and cc.student = :student");
			}

			if (!("".equals(course_type))) {
				strSQL.append(" and cc.course_type = :course_type");
			}

			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createSQLQuery(strSQL.toString());
			if (!("".equals(student))) {
				query.setString("student", student);
			}

			if (!("".equals(course_type))) {
				query.setString("course_type", course_type);
			}

			List<BigInteger> list = query.list();
			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				BigInteger result = list.get(0);
				return result.intValue();
			} else {
				return 0;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:"
					+ e.getMessage());
			return 0;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getCountByDay(String student, String class_time, String day_time) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("select count(*) from class_table cc where 1=1");

			if (!("".equals(student))) {
				strSQL.append(" and cc.student = :student");
			}

			if (!("".equals(class_time))) {
				strSQL.append(" and cc.class_time = :class_time");
			}

			if (!("".equals(day_time))) {
				strSQL.append(" and cc.day_time = :day_time");
			}

			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createSQLQuery(strSQL.toString());
			if (!("".equals(student))) {
				query.setString("student", student);
			}

			if (!("".equals(class_time))) {
				query.setString("class_time", class_time);
			}

			if (!("".equals(day_time))) {
				query.setString("day_time", day_time);
			}

			List<BigInteger> list = query.list();

			session.getTransaction().commit();
			if (list != null && list.size() >= 1) {
				BigInteger result = list.get(0);
				return result.intValue();
			} else {
				return 0;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:"
					+ e.getMessage());
			return 0;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ClassTable getTeacherTable(String stuff, String day_time, String class_time) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from ClassTable as cc left join fetch cc.courseType  where 1=1");

			if (!("".equals(stuff))) {
				strSQL.append(" and cc.stuff = :stuff");
			}

			if (!("".equals(day_time))) {
				strSQL.append(" and cc.dayTime = :day_time");
			}
			if (!("".equals(class_time))) {
				strSQL.append(" and cc.classTime = :class_time");
			}

			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("".equals(stuff))) {
				query.setString("stuff", stuff);
			}

			if (!("".equals(day_time))) {
				query.setString("day_time", day_time);
			}
			if (!("".equals(class_time))) {
				query.setString("class_time", class_time);
			}

			List<ClassTable> list = query.list();

			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				return list.get(0);
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
	public int getCountByTeacher(String stuff, String class_time, String day_time) {

		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("select count(*) from class_table cc where 1=1");

			if (!("".equals(stuff))) {
				strSQL.append(" and cc.stuff = :stuff");
			}

			if (!("".equals(class_time))) {
				strSQL.append(" and cc.class_time = :class_time");
			}

			if (!("".equals(day_time))) {
				strSQL.append(" and cc.day_time = :day_time");
			}

			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createSQLQuery(strSQL.toString());
			if (!("".equals(stuff))) {
				query.setString("stuff", stuff);
			}

			if (!("".equals(class_time))) {
				query.setString("class_time", class_time);
			}

			if (!("".equals(day_time))) {
				query.setString("day_time", day_time);
			}

			List<BigInteger> list = query.list();

			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				BigInteger result = list.get(0);
				return result.intValue();
			} else {
				return 0;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:"
					+ e.getMessage());
			return 0;
		} finally {
			session.close();
		}
	}

	@Override
	public int saveClassTable(ClassTable classTable) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(classTable);
			session.getTransaction().commit();

			return classTable.getPId();
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
	public List<ClassTable> findClassTable(String subject, List<Stuff> tr, String cr, String student, String time1,
			String time2, String status, String class_time, String campus, String course_type, String level) {

		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"from ClassTable as cc left join fetch cc.subject left join fetch cc.level left join fetch cc.campus left join fetch cc.student  left join fetch cc.stuff  left join fetch cc.courseType left join fetch cc.classTime where 1=1");

			if (!("no".equals(campus))) {
				strSQL.append(" and cc.campus = :campus");
			}
			if (!(tr == null)) {
				strSQL.append(" and ( 1=2");
				// 循环查找
				for (Stuff tmp : tr) {
					strSQL.append(" or cc.stuff.PId = " + tmp.getPId());
				}
				strSQL.append(" )");
			}
			if (!("".equals(time1))) {
				strSQL.append(" and cc.dayTime >= :time1");
			}
			if (!("".equals(time2))) {
				strSQL.append(" and cc.dayTime <= :time2");
			}

			if (!("no".equals(subject))) {
				strSQL.append(" and cc.subject.PId = :subject");
			}

			if (!("".equals(student))) {
				strSQL.append(" and cc.student.name like :student");
			}
			if (!("no".equals(status))) {
				strSQL.append(" and cc.status = :status");
			}

			if (!("no".equals(class_time))) {
				strSQL.append(" and cc.classTime.PId = :class_time");
			}

			if (!("no".equals(course_type))) {
				strSQL.append(" and cc.courseType.PId = :course_type");
			}

			if (!("no".equals(level))) {
				strSQL.append(" and cc.level.PId = :level");
			}

			if (!("no".equals(cr))) {
				strSQL.append(" and cc.student.stuff.PId = :cr");
			}

			strSQL.append(" order by cc.dayTime,cc.classTime");
			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("no".equals(campus))) {
				query.setString("campus", campus);
			}

			if (!("".equals(time1))) {
				query.setString("time1", time1);
			}
			if (!("".equals(time2))) {
				query.setString("time2", time2);
			}

			if (!("no".equals(subject))) {
				query.setString("subject", subject);
			}

			if (!("".equals(student))) {
				query.setString("student", student);
			}
			if (!("no".equals(status))) {
				query.setString("status", status);
			}

			if (!("no".equals(class_time))) {
				query.setString("class_time", class_time);
			}

			if (!("no".equals(course_type))) {
				query.setString("course_type", course_type);
			}

			if (!("no".equals(level))) {
				query.setString("level", level);
			}

			if (!("no".equals(cr))) {
				query.setString("cr", cr);
			}

			List<ClassTable> list = query.list();

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
	public boolean deleteTable(ClassTable classTable) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(classTable);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。MESSAGE:" + e.getMessage());
			return false;
		} finally {
			session.close();
		}

	}

	@Override
	public ClassTable geTable(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			ClassTable obj = (ClassTable) session.get(ClassTable.class, id);
			session.getTransaction().commit();
			return obj;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。MESSAGE:" + e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean update(ClassTable ht) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(ht);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。MESSAGE:" + e.getMessage());
			return false;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassTable> findClassTableByTr(String time1, String time2, String campus, String stuff) {

		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"from ClassTable as cc left join fetch cc.subject left join fetch cc.level left join fetch cc.campus left join fetch cc.student  left join fetch cc.stuff  left join fetch cc.courseType left join fetch cc.classTime where 1=1");

			if (!("no".equals(campus))) {
				strSQL.append(" and cc.campus = :campus");
			}

			if (!("no".equals(stuff))) {
				strSQL.append(" and cc.stuff = :stuff");
			}

			if (!("".equals(time1))) {
				strSQL.append(" and cc.dayTime >= :time1");
			}
			if (!("".equals(time2))) {
				strSQL.append(" and cc.dayTime <= :time2");
			}

			strSQL.append(" group by cc.PId,cc.stuff.PId");

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

			if (!("".equals(time1))) {
				query.setString("time1", time1);
			}
			if (!("".equals(time2))) {
				query.setString("time2", time2);
			}

			List<ClassTable> list = query.list();

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
	public List<ClassTable> findTrClassTable(String subject, String stuff, String student, String time1, String time2,
			String status, String class_time, String campus, String course_type, String level) {

		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"from ClassTable as cc left join fetch cc.subject left join fetch cc.level left join fetch cc.campus left join fetch cc.student  left join fetch cc.stuff  left join fetch cc.courseType left join fetch cc.classTime where 1=1");

			if (!("no".equals(campus))) {
				strSQL.append(" and cc.campus = :campus");
			}
			if (!("".equals(time1))) {
				strSQL.append(" and cc.dayTime >= :time1");
			}
			if (!("".equals(time2))) {
				strSQL.append(" and cc.dayTime <= :time2");
			}

			if (!("no".equals(subject))) {
				strSQL.append(" and cc.subject.PId <= :subject");
			}

			if (!("".equals(student))) {
				strSQL.append(" and cc.student.name like :student");
			}
			if (!("no".equals(status))) {
				strSQL.append(" and cc.status = :status");
			}

			if (!("no".equals(class_time))) {
				strSQL.append(" and cc.classTime.PId = :class_time");
			}

			if (!("no".equals(course_type))) {
				strSQL.append(" and cc.courseType.PId = :course_type");
			}

			if (!("no".equals(level))) {
				strSQL.append(" and cc.level.PId = :level");
			}

			if (!("no".equals(stuff))) {
				strSQL.append(" and cc.stuff = :stuff");
			}

			strSQL.append(" order by cc.dayTime,cc.classTime");
			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("no".equals(campus))) {
				query.setString("campus", campus);
			}

			if (!("".equals(time1))) {
				query.setString("time1", time1);
			}
			if (!("".equals(time2))) {
				query.setString("time2", time2);
			}

			if (!("no".equals(subject))) {
				query.setString("subject", subject);
			}

			if (!("".equals(student))) {
				query.setString("student", student);
			}
			if (!("no".equals(status))) {
				query.setString("status", status);
			}

			if (!("no".equals(class_time))) {
				query.setString("class_time", class_time);
			}

			if (!("no".equals(course_type))) {
				query.setString("course_type", course_type);
			}

			if (!("no".equals(level))) {
				query.setString("level", level);
			}

			if (!("no".equals(stuff))) {
				query.setString("stuff", stuff);
			}

			List<ClassTable> list = query.list();

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
