package com.yaodingjiaoyu.daoImpl;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import com.yaodingjiaoyu.dao.ExamplesDao;
import com.yaodingjiaoyu.datebase.pojo.Examples;

public class ExamplesDaoImpl implements ExamplesDao {
	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean SaveExamples(Examples examples) {
		Session session = null;

		try {
			boolean success = false;
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(examples);
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

	@Override
	public boolean SaveExamplesList(List<Examples> list) {
		Session session = null;
		try {
			boolean success = false;
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (Examples examples : list) {
				session.save(examples);
			}
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

	// 该方法目前是被弃用的
	@SuppressWarnings("unchecked")
	@Override
	public List<Examples> findByAdminRequest(String name, String school, String level, String now_class, String phone,
			String address, String youxiao, String zhuangtai, String probability, String channel, String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"from Examples as examples left join fetch examples.stuff left join fetch examples.channel left join fetch examples.campus left join fetch examples.level left join fetch examples.probability  where 1=1");

			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and examples.name like :name");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				strSQL.append(" and examples.school like :school");
			}
			if (!("".equals(phone))) {// 搜索框传入的phone不为空
				strSQL.append(" and (examples.phone1 like :phone or examples.phone2 like :phone)");
			}
			if (!("".equals(now_class))) {// 搜索框传入的now_class不为空
				strSQL.append(" and examples.nowClass = :now_class");
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				strSQL.append(" and examples.level = :level");
			}
			if (!("".equals(address))) {// 搜索框传入的address不为空
				strSQL.append(" and examples.address like :address");
			}
			if (!("no".equals(youxiao))) {// 搜索框传入的level不为空
				strSQL.append(" and examples.youxiao = :youxiao");
			}
			if (!("no".equals(zhuangtai))) {// 搜索框传入的level不为空
				strSQL.append(" and examples.zhuangtai = :zhuangtai");
			}
			if (!("no".equals(probability))) {// 搜索框传入的level不为空
				strSQL.append(" and examples.probability = :probability");
			}
			if (!("no".equals(channel))) {// 搜索框传入的level不为空
				strSQL.append(" and examples.channel = :channel");
			}
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				strSQL.append(" and examples.campus = :campus");
			}
			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery(strSQL.toString());
			if (!("".equals(name))) {// 搜索框传入的name不为空
				query.setString("name", "%" + name + "%");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				query.setString("school", "%" + school + "%");
			}
			if (!("".equals(phone))) {// 搜索框传入的phone不为空
				query.setString("phone", "%" + phone + "%");
			}
			if (!("".equals(address))) {// 搜索框传入的address不为空
				query.setString("address", "%" + address + "%");
			}
			if (!("".equals(now_class))) {// 搜索框传入的stu_class不为空
				query.setString("now_class", now_class);
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				query.setString("level", level);
			}
			if (!("no".equals(youxiao))) {// 搜索框传入的level不为空
				query.setString("youxiao", youxiao);
			}
			if (!("no".equals(zhuangtai))) {// 搜索框传入的level不为空
				query.setString("zhuangtai", zhuangtai);
			}
			if (!("no".equals(probability))) {// 搜索框传入的level不为空
				query.setString("probability", probability);
			}
			if (!("no".equals(channel))) {// 搜索框传入的level不为空
				query.setString("channel", channel);
			}
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				query.setString("campus", campus);
			}

			List<Examples> list = query.list();

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

	// 返回的是简略信息
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findByAdminRequest_MAP(String name, String school, String level, String now_class,
			String phone, String address, String youxiao, String zhuangtai, String probability, String channel,
			String campus, String cc_total) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			// cc_total代表跟踪记录次数
			strSQL.append(
					"select P_ID,name,school,level,cc_total,last_time from( select examples.*,temp.cc_total  from examples  left join (select examples,count(*)as cc_total from cc_content c group by c.examples)as temp on examples.P_ID = temp.examples)as examples_list where 1 = 1");

			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and examples_list.name like '%" + name + "%'");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				strSQL.append(" and examples_list.school like '%" + school + "%'");
			}
			if (!("".equals(phone))) {// 搜索框传入的phone不为空
				strSQL.append(" and (examples_list.phone1 like '%" + phone + "%' or examples_list.phone2 like '%"
						+ phone + "%')");
			}
			if (!("".equals(now_class))) {// 搜索框传入的now_class不为空
				strSQL.append(" and examples_list.now_class = '" + now_class + "'");
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.level = '" + level + "'");
			}
			if (!("".equals(address))) {// 搜索框传入的address不为空
				strSQL.append(" and examples_list.address like '%" + address + "%'");
			}
			if (!("no".equals(youxiao))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.youxiao = '" + youxiao + "'");
			}
			if (!("no".equals(zhuangtai))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.zhuangtai = '" + zhuangtai + "'");
			}
			if (!("no".equals(probability))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.probability = '" + probability + "'");
			}
			if (!("no".equals(channel))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.channel = '" + channel + "'");
			}
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.campus = '" + campus + "'");
			}
			if (!("no".equals(cc_total))) {// 搜索框传入的跟踪次数不为空
				if ("0".equals(cc_total)) {
					strSQL.append(" and examples_list.cc_total is null");
				}
				if ("5".equals(cc_total)) {
					strSQL.append(" and examples_list.cc_total <= '" + cc_total + "'");
				}
				if ("10".equals(cc_total)) {
					strSQL.append(" and examples_list.cc_total <= '" + cc_total + "'");
				}
				if ("11".equals(cc_total)) {// 大于10次
					strSQL.append(" and examples_list.cc_total >= '" + cc_total + "'");
				}

			}
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
	public List<Map<String, Object>> findByCcRequest_MAP(String name, String school, String level, String now_class,
			String phone, String address, String youxiao, String zhuangtai, String probability, String channel,
			String campus, String stuff, String cc_total) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"select P_ID,name,school,level,cc_total,last_time from( select examples.*,temp.cc_total  from examples  left join (select examples,count(*)as cc_total from cc_content c group by c.examples)as temp on examples.P_ID = temp.examples)as examples_list where 1 = 1");

			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and examples_list.name like '%" + name + "%'");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				strSQL.append(" and examples_list.school like '%" + school + "%'");
			}
			if (!("".equals(phone))) {// 搜索框传入的phone不为空
				strSQL.append(" and (examples_list.phone1 like '%" + phone + "%' or examples_list.phone2 like '%"
						+ phone + "%')");
			}
			if (!("".equals(now_class))) {// 搜索框传入的now_class不为空
				strSQL.append(" and examples_list.now_class = '" + now_class + "'");
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.level = '" + level + "'");
			}
			if (!("".equals(address))) {// 搜索框传入的address不为空
				strSQL.append(" and examples_list.address like '%" + address + "%'");
			}
			if (!("no".equals(youxiao))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.youxiao = '" + youxiao + "'");
			}
			if (!("no".equals(zhuangtai))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.zhuangtai = '" + zhuangtai + "'");
			}
			if (!("no".equals(probability))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.probability = '" + probability + "'");
			}
			if (!("no".equals(channel))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.channel = '" + channel + "'");
			}
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.campus = '" + campus + "'");
			}
			if (!("no".equals(cc_total))) {// 搜索框传入的跟踪次数不为空
				if ("0".equals(cc_total)) {
					strSQL.append(" and examples_list.cc_total is null");
				}
				if ("5".equals(cc_total)) {
					strSQL.append(" and examples_list.cc_total <= '" + cc_total + "'");
				}
				if ("10".equals(cc_total)) {
					strSQL.append(" and examples_list.cc_total <= '" + cc_total + "'");
				}
				if ("11".equals(cc_total)) {// 大于10次
					strSQL.append(" and examples_list.cc_total >= '" + cc_total + "'");
				}

			}

			if (!("no".equals(stuff))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.stuff = '" + stuff + "'");
			}

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

	@Override
	public Examples findById(int id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Examples examples = (Examples) session.get(Examples.class, id);
			session.getTransaction().commit();
			return examples;
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
	public boolean UpdateExamples(Examples examples) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(examples);
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
	public boolean DeleteExamples(Examples examples) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(examples);
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
	public List<Map<String, Object>> findBySdRequest_MAP(String name, String school, String level, String now_class,
			String phone, String address, String youxiao, String zhuangtai, String probability, String channel,
			String campus, String stuff, String cc_total, String status) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"select P_ID,name,school,level,cc_total,last_time from( select examples.*,temp.cc_total  from examples  left join (select examples,count(*)as cc_total from cc_content c group by c.examples)as temp on examples.P_ID = temp.examples)as examples_list where 1 = 1");

			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and examples_list.name like '%" + name + "%'");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				strSQL.append(" and examples_list.school like '%" + school + "%'");
			}
			if (!("".equals(phone))) {// 搜索框传入的phone不为空
				strSQL.append(" and (examples_list.phone1 like '%" + phone + "%' or examples_list.phone2 like '%"
						+ phone + "%')");
			}
			if (!("".equals(now_class))) {// 搜索框传入的now_class不为空
				strSQL.append(" and examples_list.now_class = '" + now_class + "'");
			}
			if (!("no".equals(status))) {// 搜索框传入的now_class不为空
				strSQL.append(" and examples_list.status = '" + status + "'");
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.level = '" + level + "'");
			}
			if (!("".equals(address))) {// 搜索框传入的address不为空
				strSQL.append(" and examples_list.address like '%" + address + "%'");
			}
			if (!("no".equals(youxiao))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.youxiao = '" + youxiao + "'");
			}
			if (!("no".equals(zhuangtai))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.zhuangtai = '" + zhuangtai + "'");
			}
			if (!("no".equals(probability))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.probability = '" + probability + "'");
			}
			if (!("no".equals(channel))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.channel = '" + channel + "'");
			}
			if (!("no".equals(campus))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.campus = '" + campus + "'");
			}
			if (!("no".equals(cc_total))) {// 搜索框传入的跟踪次数不为空
				if ("0".equals(cc_total)) {
					strSQL.append(" and examples_list.cc_total is null");
				}
				if ("5".equals(cc_total)) {
					strSQL.append(" and examples_list.cc_total <= '" + cc_total + "'");
				}
				if ("10".equals(cc_total)) {
					strSQL.append(" and examples_list.cc_total <= '" + cc_total + "'");
				}
				if ("11".equals(cc_total)) {// 大于10次
					strSQL.append(" and examples_list.cc_total >= '" + cc_total + "'");
				}

			}

			if (!("no".equals(stuff))) {// 搜索框传入的level不为空
				strSQL.append(" and examples_list.stuff = '" + stuff + "'");
			}

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
