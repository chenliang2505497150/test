package com.yaodingjiaoyu.daoImpl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yaodingjiaoyu.dao.UnitPriceDao;
import com.yaodingjiaoyu.datebase.pojo.CampusPrice;

public class UnitPriceDaoImpl implements UnitPriceDao {
	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CampusPrice GetUnitPriceBy(int campus, int course_type) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();

		try {
			strSQL.append("from CampusPrice as cp left join fetch cp.campus  left join fetch cp.courseType  where 1=1");

			strSQL.append(" and cp.campus = :campus");

			strSQL.append(" and cp.courseType = :course_type");

			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());

			query.setString("campus", campus + "");
			query.setString("course_type", course_type + "");

			List<CampusPrice> list = query.list();

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

}
