package com.yaodingjiaoyu.daoImpl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yaodingjiaoyu.dao.SearchStuffDao;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class SearchStuffDaoImpl implements SearchStuffDao {
	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Stuff> findBySdRequest(String name, String job, String status, String part_time, String campus) {

		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			// 定义冻结字段status，如果status ＝ 1表示冻结，status ＝ 0表示账户已经冻结
			// 定义兼职字段part_time，如果part_time ＝ 1表示兼职，part_time ＝ 0表示正式员工
			strSQL.append("from Stuff as s left join fetch s.job  left join fetch s.campus where 1=1 and s.job.key not in('admin','sd')");

			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and s.name like :name");
			}
			if (!("no".equals(job))) {// 搜索框传入的school不为空
				strSQL.append(" and s.job = :job");
			}
			if (!("no".equals(status))) {// 搜索框传入的phone不为空
				strSQL.append(" and s.status = :status");
			}
			if (!("no".equals(part_time))) {// 搜索框传入的stu_class不为空
				strSQL.append(" and s.partTime = :part_time");
			}

			strSQL.append(" and s.campus = " + campus);

			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("".equals(name))) {// 搜索框传入的name不为空
				query.setString("name", "%" + name + "%");
			}
			if (!("no".equals(job))) {// 搜索框传入的school不为空
				query.setString("job", job);
			}
			if (!("no".equals(status))) {// 搜索框传入的phone不为空
				query.setString("status", status);
			}
			if (!("no".equals(part_time))) {// 搜索框传入的stu_class不为空
				query.setString("part_time", part_time);
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

}
