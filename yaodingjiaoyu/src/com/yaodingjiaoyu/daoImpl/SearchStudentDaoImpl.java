package com.yaodingjiaoyu.daoImpl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import com.yaodingjiaoyu.dao.SearchStudentDao;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class SearchStudentDaoImpl implements SearchStudentDao {
	private SessionFactory sessionFactory;
	private Logger logger;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findByCrRequest(String name, String school, String level, String now_class, String phone,
			String stu_status, String stuff, String campus) {

		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			// 定义剩余时间字段，如果stu_status ＝ 1表示未结课stu_status ＝ 0表示已经结课，对应的last_hour ＝
			// 0
			strSQL.append(
					"from Student as s left join fetch s.level  left join fetch s.campus left join fetch s.stuff where 1=1");

			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and s.name like :name");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				strSQL.append(" and s.school like :school");
			}
			if (!("".equals(phone))) {// 搜索框传入的phone不为空
				strSQL.append(" and (s.phone1 like :phone or s.phone2 like :phone)");
			}
			if (!("".equals(now_class))) {// 搜索框传入的stu_class不为空
				strSQL.append(" and s.nowClass = :now_class");
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				strSQL.append(" and s.level = :level");
			}
			if (!("no".equals(stu_status))) {// 搜索框传入的stu_status不为no
				if ("0".equals(stu_status)) {// 已结课
					strSQL.append(" and s.lastHour = 0");
				}
				if ("1".equals(stu_status)) {// 未结课
					strSQL.append(" and s.lastHour > 0");
				}

			}

			strSQL.append(" and s.stuff = " + stuff + " and s.campus = " + campus);

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
			if (!("".equals(now_class))) {// 搜索框传入的stu_class不为空
				query.setString("now_class", now_class);
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				query.setString("level", level);
			}

			List<Student> list = query.list();

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
	public List<Student> findByAdminRequest(String name, String school, String level, String now_class, String phone,
			String stu_status, String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			// 定义剩余时间字段，如果stu_status ＝ 1表示未结课stu_status ＝ 0表示已经结课，对应的last_hour ＝
			// 0
			strSQL.append(
					"from Student as s left join fetch s.level  left join fetch s.campus left join fetch s.stuff  where 1=1");

			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and s.name like :name");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				strSQL.append(" and s.school like :school");
			}
			if (!("".equals(phone))) {// 搜索框传入的phone不为空
				strSQL.append(" and (s.phone1 like :phone or s.phone2 like :phone)");
			}
			if (!("".equals(now_class))) {// 搜索框传入的stu_class不为空
				strSQL.append(" and s.nowClass = :now_class");
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				strSQL.append(" and s.level = :level");
			}
			if (!("no".equals(stu_status))) {// 搜索框传入的stu_status不为no
				if ("0".equals(stu_status)) {// 已结课
					strSQL.append(" and s.lastHour = 0");
				}
				if ("1".equals(stu_status)) {// 未结课
					strSQL.append(" and s.lastHour > 0");
				}

			}
			if (!("no".equals(campus))) {// 搜索框传入的campus不为no
				strSQL.append(" and s.campus = :campus");
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
			if (!("".equals(now_class))) {// 搜索框传入的stu_class不为空
				query.setString("now_class", now_class);
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				query.setString("level", level);
			}
			if (!("no".equals(campus))) {// 搜索框传入的campus不为no
				query.setString("campus", campus);
			}

			List<Student> list = query.list();
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
	public List<Map<String, Object>> findByCrRequest_MAP(String name, String school, String level, String now_class,
			String phone, String stu_status, String isend, String stuff, String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"select P_ID,name,school,level,now_class,all_hour,last_hour,phone1,phone2,total from( select student.*,temp.total  from student  left join (select student,count(*)as total from class_table c group by c.student)as temp on student.P_ID = temp.student)as student_list where 1 = 1");
			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and student_list.name like '%" + name + "%'");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				strSQL.append(" and student_list.school like '%" + school + "%'");
			}
			if (!("".equals(phone))) {// 搜索框传入的phone不为空
				strSQL.append(" and (student_list.phone1 like '%" + phone + "%' or student_list.phone2 like '%" + phone
						+ "%')");
			}
			if (!("".equals(now_class))) {// 搜索框传入的now_class不为空
				strSQL.append(" and student_list.now_class = '" + now_class + "'");
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				strSQL.append(" and student_list.level = '" + level + "'");
			}
			if (!("no".equals(stu_status))) {// 搜索框传入的stu_status不为no
				if ("0".equals(stu_status)) {// 已结课
					strSQL.append(" and student_list.last_hour = 0");
				}
				if ("1".equals(stu_status)) {// 未结课
					strSQL.append(" and student_list.last_hour > 0");
				}

			}
			if (!("no".equals(isend))) {// 搜索框传入的isend不为no
				if ("0".equals(isend)) {// 已排课完毕
					strSQL.append(" and student_list.total * 3 = student_list.all_hour");
				}
				if ("1".equals(isend)) {// 未排课完毕
					strSQL.append(
							" and (student_list.total * 3 < student_list.all_hour or student_list.total is null)");
				}

			}

			strSQL.append(" and student_list.campus = '" + campus + "'");
			strSQL.append(" and student_list.stuff = '" + stuff + "'");

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
	public List<Map<String, Object>> findByTrRequest_MAP(String name, String school, String level, String now_class,
			String phone, String stuff, String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append(
					"select P_ID,name,school,level,now_class,all_hour,last_hour,phone1,phone2 from student where P_ID in(select distinct(student) from class_table where stuff = "
							+ stuff + ") ");
			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and name like '%" + name + "%'");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				strSQL.append(" and school like '%" + school + "%'");
			}
			if (!("".equals(phone))) {// 搜索框传入的phone不为空
				strSQL.append(" and (phone1 like '%" + phone + "%' or phone2 like '%" + phone + "%')");
			}
			if (!("".equals(now_class))) {// 搜索框传入的now_class不为空
				strSQL.append(" and now_class = '" + now_class + "'");
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				strSQL.append(" and level = '" + level + "'");
			}

			strSQL.append(" and campus = '" + campus + "'");

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
	public boolean TrHaveStudent(String student, String stuff, String campus) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("select count(*) from class_table cc where 1=1");

			if (!("no".equals(student))) {
				strSQL.append(" and cc.student = :student");
			}

			if (!("no".equals(stuff))) {
				strSQL.append(" and cc.stuff = :stuff");
			}

			if (!("no".equals(campus))) {
				strSQL.append(" and cc.campus = :campus");
			}

			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createSQLQuery(strSQL.toString());
			if (!("no".equals(student))) {
				query.setString("student", student);
			}

			if (!("no".equals(stuff))) {
				query.setString("stuff", stuff);
			}

			if (!("no".equals(campus))) {
				query.setString("campus", campus);
			}

			List<BigInteger> list = query.list();

			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数strSQL：" + strSQL.toString() + ",MESSAGE:"
					+ e.getMessage());
			return false;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findBySdRequest(String name, String school, String level, String now_class, String phone,
			String stu_status, String campus, String status, String stuff) {
		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			// 定义剩余时间字段，如果stu_status ＝ 1表示未结课stu_status ＝ 0表示已经结课，对应的last_hour ＝
			// 0
			strSQL.append(
					"from Student as s left join fetch s.level  left join fetch s.campus left join fetch s.stuff  where 1=1");

			if (!("".equals(name))) {// 搜索框传入的name不为空
				strSQL.append(" and s.name like :name");
			}
			if (!("".equals(school))) {// 搜索框传入的school不为空
				strSQL.append(" and s.school like :school");
			}
			if (!("".equals(phone))) {// 搜索框传入的phone不为空
				strSQL.append(" and (s.phone1 like :phone or s.phone2 like :phone)");
			}
			if (!("".equals(now_class))) {// 搜索框传入的stu_class不为空
				strSQL.append(" and s.nowClass = :now_class");
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				strSQL.append(" and s.level = :level");
			}

			if (!("no".equals(status))) {// 搜索框传入的分配
				strSQL.append(" and s.status = :status");
			}

			if (!("no".equals(stuff))) {
				strSQL.append(" and s.stuff = :stuff");
			}

			if (!("no".equals(stu_status))) {// 搜索框传入的stu_status不为no
				if ("0".equals(stu_status)) {// 已结课
					strSQL.append(" and s.lastHour = 0");
				}
				if ("1".equals(stu_status)) {// 未结课
					strSQL.append(" and s.lastHour > 0");
				}

			}
			if (!("no".equals(campus))) {// 搜索框传入的campus不为no
				strSQL.append(" and s.campus = :campus");
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
			if (!("no".equals(stuff))) {
				query.setString("stuff", stuff);
			}
			if (!("".equals(now_class))) {// 搜索框传入的stu_class不为空
				query.setString("now_class", now_class);
			}
			if (!("no".equals(status))) {
				query.setString("status", status);
			}
			if (!("no".equals(level))) {// 搜索框传入的level不为空
				query.setString("level", level);
			}
			if (!("no".equals(campus))) {// 搜索框传入的campus不为no
				query.setString("campus", campus);
			}

			List<Student> list = query.list();
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
	public int getStudentBy(String name, String phone1, String phone2) {

		Session session = null;
		StringBuffer strSQL = new StringBuffer();
		try {
			strSQL.append("select P_ID from student cc where 1=1");

			strSQL.append(" and cc.name = :name");

			strSQL.append(" and (cc.phone1 = :phone1 or cc.phone1 = :phone2)");

			// 管理员没有限定

			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createSQLQuery(strSQL.toString());

			query.setString("name", name);

			query.setString("phone1", phone1);

			query.setString("phone2", phone2);

			List<Integer> list = query.list();

			session.getTransaction().commit();

			if (list != null && list.size() >= 1) {
				Integer result = list.get(0);
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

}
