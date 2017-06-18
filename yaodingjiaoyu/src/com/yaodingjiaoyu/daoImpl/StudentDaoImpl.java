package com.yaodingjiaoyu.daoImpl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yaodingjiaoyu.dao.UserDao;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class StudentDaoImpl implements UserDao {

	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int save(Object user) {
		Session session = null;
		int result = 0;
		try {
			Student student = (Student) user;
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(student);
			session.getTransaction().commit();
			result = student.getPId();
			return result;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->save:运行失败。MESSAGE:" + e.getMessage());
			return 0;
		} finally {
			session.close();
		}

	}

	@Override
	public Student getUser(int id) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Student student = (Student) session.get(Student.class, id);
			session.getTransaction().commit();

			return student;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->getUser:运行失败。MESSAGE:" + e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean update(Object user) {
		Session session = null;
		try {
			Student student = (Student) user;
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(student);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->update:运行失败。MESSAGE:" + e.getMessage());
			return false;
		} finally {
			session.close();
		}

	}

	@Override
	public boolean delete(Object user) {
		Session session = null;
		try {
			Student student = (Student) user;
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(student);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->delete:运行失败。MESSAGE:" + e.getMessage());
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
					"from Student as s left join fetch s.level  left join fetch s.campus left join fetch s.stuff where s.name = :name");
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
			logger.error(this.getClass().getName() + "-->findByName:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:"
					+ e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student findByPass(String username, String password) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"from Student as s left join fetch s.level  left join fetch s.campus left join fetch s.stuff where s.username = :username and s.password = :password");

			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			query.setString("username", username);
			query.setString("password", password);
			List<Student> list = query.list();
			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->findByPass:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:"
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
					"from Student as s left join fetch s.campus left join fetch s.job where s.username = :username");

			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			query.setString("username", username);
			List<Student> list = query.list();
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
