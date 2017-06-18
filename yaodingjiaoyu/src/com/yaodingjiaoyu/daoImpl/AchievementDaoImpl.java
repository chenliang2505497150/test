package com.yaodingjiaoyu.daoImpl;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.yaodingjiaoyu.dao.AchievementDao;

public class AchievementDaoImpl implements AchievementDao {
	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getCcAchievement(String name, String time1, String time2, String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			// cc_total代表跟踪记录次数
			strSQL.append(
					"select s.P_ID P_ID,s.name name,(select name from campus where P_ID = s.campus ) campus,sum(pos+cash) achieve from stuff s left join hetong h on h.stuff = s.P_ID where s.power = 'cc' and s.status = 0 ");

			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and s.name like '%" + name + "%'");
			}
			if (!("".equals(time1))) {// 搜索框传入的school不为空
				strSQL.append(" and h.sign_time >= '" + time1 + "'");
			}
			if (!("".equals(time2))) {// 搜索框传入的phone不为空
				strSQL.append(" and h.sign_time <= '" + time2 + "'");
			}
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				strSQL.append(" and s.campus = '" + campus + "'");
			}

			strSQL.append(" group by P_ID,name,campus order by achieve desc");
			// 管理员没有限定
			session = sessionFactory.openSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(strSQL.toString());
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);// 将结果转化为MAP
			List<?> list = query.list();
			session.getTransaction().commit();
			if (list != null && list.size() >= 1) {
				return (List<Map<String, Object>>) list;
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
	public List<Map<String, Object>> getCrAchievement(String name, String time1, String time2, String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			// cc_total代表跟踪记录次数
			strSQL.append(
					"select s.P_ID P_ID,s.name name,(select name from campus where P_ID = s.campus ) campus,sum(pos+cash) achieve from stuff s left join hetong h on h.stuff = s.P_ID where s.power = 'cr' and s.status = 0 ");

			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and s.name like '%" + name + "%'");
			}
			if (!("".equals(time1))) {// 搜索框传入的school不为空
				strSQL.append(" and h.sign_time >= '" + time1 + "'");
			}
			if (!("".equals(time2))) {// 搜索框传入的phone不为空
				strSQL.append(" and h.sign_time <= '" + time2 + "'");
			}
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				strSQL.append(" and s.campus = '" + campus + "'");
			}

			strSQL.append(" group by P_ID,name,campus order by achieve desc");
			// 管理员没有限定
			session = sessionFactory.openSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(strSQL.toString());
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);// 将结果转化为MAP
			List<?> list = query.list();
			session.getTransaction().commit();
			if (list != null && list.size() >= 1) {
				return (List<Map<String, Object>>) list;
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
	public List<Map<String, Object>> getTrAchievement(String name, String time1, String time2, String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			// cc_total代表跟踪记录次数
			strSQL.append(
					"select s.P_ID P_ID,s.name name,(select name from campus where P_ID = s.campus ) campus,count(1) achieve from stuff s left join class_table c on c.stuff = s.P_ID where s.power = 'tr' and c.course_type = 1 and s.status = 0 ");

			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and s.name like '%" + name + "%'");
			}
			if (!("".equals(time1))) {// 搜索框传入的school不为空
				strSQL.append(" and c.day_time >= '" + time1 + "'");
			}
			if (!("".equals(time2))) {// 搜索框传入的phone不为空
				strSQL.append(" and c.day_time <= '" + time2 + "'");
			}
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				strSQL.append(" and s.campus = '" + campus + "'");
			}

			strSQL.append(" group by P_ID,name,campus order by achieve desc");

			// 管理员没有限定
			session = sessionFactory.openSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(strSQL.toString());
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);// 将结果转化为MAP
			List<?> list = query.list();
			session.getTransaction().commit();
			if (list != null && list.size() >= 1) {
				return (List<Map<String, Object>>) list;
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
