package com.yaodingjiaoyu.daoImpl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yaodingjiaoyu.dao.PageListDao;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.Channel;
import com.yaodingjiaoyu.datebase.pojo.ClassTime;
import com.yaodingjiaoyu.datebase.pojo.CourseType;
import com.yaodingjiaoyu.datebase.pojo.HetongType;
import com.yaodingjiaoyu.datebase.pojo.HtProperty;
import com.yaodingjiaoyu.datebase.pojo.Job;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Probability;
import com.yaodingjiaoyu.datebase.pojo.Stuff;
import com.yaodingjiaoyu.datebase.pojo.Subject;

public class PageListDaoImpl implements PageListDao {
	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Level> getLevel_list() {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from Level as l");
			session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			List<Level> list = query.list();
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
	public List<Probability> getProbability_list() {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from Probability as p");
			session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			List<Probability> list = query.list();
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
	public List<Channel> getChannel_list() {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from Channel as c");
			session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			List<Channel> list = query.list();
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
	public List<Campus> getCampus_list() {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from Campus as c");
			session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			List<Campus> list = query.list();
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
	public List<Stuff> getSalerListByCampus(String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from Stuff as stuff  where 1=1");

			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				strSQL.append(" and stuff.campus = :campus");
			}

			// 保证是在职员工
			strSQL.append(" and stuff.power = 'cc' and stuff.status = 0");

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				query.setString("campus", campus);
			}

			List<Stuff> list = query.list();

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
	public List<Stuff> getcr_trListByCampus(String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from Stuff as stuff left join fetch stuff.job left join fetch stuff.campus  where 1=1");

			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				strSQL.append(" and stuff.campus = :campus");
			}
			strSQL.append(" and (stuff.power = 'tr' or stuff.power = 'cr') and stuff.status = 0 ");

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				query.setString("campus", campus);
			}

			List<Stuff> list = query.list();

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
	public List<HetongType> getHetongTypeList() {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from HetongType as h");
			session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			List<HetongType> list = query.list();
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
	public List<CourseType> getCourseTypeList() {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from CourseType as h");
			session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			List<CourseType> list = query.list();
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
	public List<HtProperty> getHtPropertyList() {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from HtProperty as l");
			session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			List<HtProperty> list = query.list();
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
	public List<Subject> getSubjectList() {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from Subject as l");
			session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			List<Subject> list = query.list();
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
	public List<Stuff> getTrListByCampus(String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from Stuff as stuff left join fetch stuff.job left join fetch stuff.campus where 1=1");

			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				strSQL.append(" and stuff.campus = :campus");
			}

			strSQL.append(" and stuff.power = 'tr' and stuff.status = 0 ");

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				query.setString("campus", campus);
			}

			List<Stuff> list = query.list();

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
	public List<Stuff> getcr_ccListByCampus(String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from Stuff as stuff left join fetch stuff.job left join fetch stuff.campus  where 1=1");

			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				strSQL.append(" and stuff.campus = :campus");
			}
			strSQL.append(" and (stuff.power = 'cc' or stuff.power = 'cr') and stuff.status = 0 ");

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				query.setString("campus", campus);
			}

			List<Stuff> list = query.list();

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
	public List<ClassTime> getClassTimeList() {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from ClassTime as l");
			session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			List<ClassTime> list = query.list();
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
	public List<Stuff> getTrListByCampusAndInput(String campus, String input) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		String[] sourceStrArray;
		try {
			// 先将字符串分割
			if (("".equals(input)) || (input == null)) {
				sourceStrArray = null;
			} else {
				sourceStrArray = input.split(" ");
			}
			strSQL.append("from Stuff as stuff left join fetch stuff.job left join fetch stuff.campus where 1=1");

			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				strSQL.append(" and stuff.campus = :campus");
			}

			strSQL.append(" and stuff.power = 'tr' and stuff.status = 0 ");

			if (sourceStrArray != null) {
				strSQL.append(" and ( 1=2");
				// 循环查找
				for (int i = 0; i < sourceStrArray.length; i++) {
					strSQL.append(" or stuff.name like '" + sourceStrArray[i] + "%'");
				}
				strSQL.append(" )");
			}

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				query.setString("campus", campus);
			}

			List<Stuff> list = query.list();

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
	public List<Stuff> getcrListByCampus(String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();

		try {
			strSQL.append("from Stuff as stuff left join fetch stuff.job left join fetch stuff.campus  where 1=1");

			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				strSQL.append(" and stuff.campus = :campus");
			}

			strSQL.append(" and stuff.power = 'cr' and stuff.status = 0 ");

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				query.setString("campus", campus);
			}

			List<Stuff> list = query.list();

			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				return list;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getJob_list() {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from Job as j");
			session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			List<Job> list = query.list();
			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->getJob_list:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:"
					+ e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getJob_listForSd() {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("from Job as j where j.key not in ('admin','sd')");
			session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery(strSQL.toString());
			List<Job> list = query.list();
			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->getJob_list:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:"
					+ e.getMessage());
			return null;
		} finally {
			session.close();
		}
	}

}
