package com.yaodingjiaoyu.daoImpl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yaodingjiaoyu.dao.UserDao;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class StuffDaoImpl implements UserDao {
	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int save(Object user) {
		Session session = null;

		try {
			Stuff stuff = (Stuff) user;
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(stuff);
			session.getTransaction().commit();

			return stuff.getPId();
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
	public Stuff getUser(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Stuff stuff = (Stuff) session.get(Stuff.class, id);
			session.getTransaction().commit();
			return stuff;
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
	public boolean update(Object user) {
		Session session = null;
		try {
			Stuff stuff = (Stuff) user;
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(stuff);
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
	public boolean delete(Object user) {
		Session session = null;
		try {
			Stuff stuff = (Stuff) user;
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(stuff);
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
	public List<Object> findByName(String name) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"from Stuff as stuff left join fetch stuff.campus left join fetch stuff.job where stuff.name = :name");
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			query.setString("name", name);
			List<Object> list = query.list();
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
	public Stuff findByPass(String username, String password) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"from Stuff as stuff left join fetch stuff.campus left join fetch stuff.job where stuff.username = :username and stuff.password = :password");

			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			query.setString("username", username);
			query.setString("password", password);
			List<Stuff> list = query.list();
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
	public boolean checkSignIn(String username) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"from Stuff as stuff left join fetch stuff.campus left join fetch stuff.job where stuff.username = :username");

			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			query.setString("username", username);
			List<Stuff> list = query.list();
			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->checkSignIn:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:"
					+ e.getMessage());
			return false;
		} finally {
			session.close();
		}
	}

}
