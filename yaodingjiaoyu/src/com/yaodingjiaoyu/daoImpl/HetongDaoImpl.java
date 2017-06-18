package com.yaodingjiaoyu.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yaodingjiaoyu.dao.HetongDao;
import com.yaodingjiaoyu.datebase.pojo.Hetong;

public class HetongDaoImpl implements HetongDao {
	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @author chenliang
	 * @param ht
	 *            传入的合同
	 * @return 合同的编号，当失败时返回0
	 * @time 2016-12-19
	 */
	@Override
	public int save(Hetong ht) {
		int result = 0;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(ht);
			session.getTransaction().commit();
			result = ht.getPId();
			return result;
		} catch (HibernateException e) {
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
	public List<Hetong> findHetongByAdmin(String hetong_num, String name, String school, String level,
			String hetong_type, String campus, String stuff, String course_type, String time1, String time2) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"from Hetong as ht left join fetch ht.stuff left join fetch ht.student left join fetch ht.courseType left join fetch ht.htProperty left join fetch ht.campus left join fetch ht.level left join fetch ht.subject left join fetch ht.hetongType where 1=1");
			// 用join使对应字表对象也初始化
			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and ht.student.name like :name");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				strSQL.append(" and ht.student.school like :school");
			}
			if (!("no".equals(level))) {
				strSQL.append(" and ht.level = :level");
			}
			if (!("no".equals(hetong_type))) {
				strSQL.append(" and ht.hetongType = :hetong_type");
			}
			if (!("no".equals(campus))) {
				strSQL.append(" and ht.campus = :campus");
			}
			if (!("no".equals(stuff))) {
				strSQL.append(" and ht.stuff = :stuff");
			}
			if (!("no".equals(course_type))) {
				strSQL.append(" and ht.courseType = :course_type");
			}
			if (!("".equals(time1))) {
				strSQL.append(" and ht.signTime >= :time1");
			}
			if (!("".equals(time2))) {
				strSQL.append(" and ht.signTime <= :time2");
			}
			if (!("".equals(hetong_num))) {
				strSQL.append(" and ht.hetongNum = :hetong_num");
			}

			// a按照签约时间进行排序
			strSQL.append(" order by ht.signTime desc");
			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("".equals(hetong_num))) {
				query.setString("hetong_num", hetong_num);
			}
			if (!("".equals(name))) {// 搜索框传入的name不为空
				query.setString("name", "%" + name + "%");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				query.setString("school", "%" + school + "%");
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				query.setString("level", level);
			}
			if (!("no".equals(hetong_type))) {
				query.setString("hetong_type", hetong_type);
			}
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				query.setString("campus", campus);
			}
			if (!("no".equals(stuff))) {
				query.setString("stuff", stuff);
			}
			if (!("no".equals(course_type))) {
				query.setString("course_type", course_type);
			}
			if (!("".equals(time1))) {
				query.setString("time1", time1);
			}
			if (!("".equals(time2))) {
				query.setString("time2", time2);
			}

			List<Hetong> list = query.list();
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
	public Hetong findById(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Hetong obj = (Hetong) session.get(Hetong.class, id);
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
	public boolean UpdateHetong(Hetong hetong) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(hetong);
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
	public boolean DeleteHetong(Hetong hetong) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(hetong);
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
	public double findHetong_Sum_Money(String hetong_num, String name, String school, String level, String hetong_type,
			String campus, String stuff, String course_type, String time1, String time2) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"select sum(pos+cash) as total_money from Hetong as ht where 1=1");
			// 用join使对应字表对象也初始化
			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and ht.student.name like :name");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				strSQL.append(" and ht.student.school like :school");
			}
			if (!("no".equals(level))) {
				strSQL.append(" and ht.level = :level");
			}
			if (!("no".equals(hetong_type))) {
				strSQL.append(" and ht.hetongType = :hetong_type");
			}
			if (!("no".equals(campus))) {
				strSQL.append(" and ht.campus = :campus");
			}
			if (!("no".equals(stuff))) {
				strSQL.append(" and ht.stuff = :stuff");
			}
			if (!("no".equals(course_type))) {
				strSQL.append(" and ht.courseType = :course_type");
			}
			if (!("".equals(time1))) {
				strSQL.append(" and ht.signTime >= :time1");
			}
			if (!("".equals(time2))) {
				strSQL.append(" and ht.signTime <= :time2");
			}
			if (!("".equals(hetong_num))) {
				strSQL.append(" and ht.hetongNum = :hetong_num");
			}

			// a按照签约时间进行排序
			strSQL.append(" order by ht.signTime desc");
			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("".equals(hetong_num))) {
				query.setString("hetong_num", hetong_num);
			}
			if (!("".equals(name))) {// 搜索框传入的name不为空
				query.setString("name", "%" + name + "%");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				query.setString("school", "%" + school + "%");
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				query.setString("level", level);
			}
			if (!("no".equals(hetong_type))) {
				query.setString("hetong_type", hetong_type);
			}
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				query.setString("campus", campus);
			}
			if (!("no".equals(stuff))) {
				query.setString("stuff", stuff);
			}
			if (!("no".equals(course_type))) {
				query.setString("course_type", course_type);
			}
			if (!("".equals(time1))) {
				query.setString("time1", time1);
			}
			if (!("".equals(time2))) {
				query.setString("time2", time2);
			}

			List<Double> list = query.list();
			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				return list.get(0);
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

}
