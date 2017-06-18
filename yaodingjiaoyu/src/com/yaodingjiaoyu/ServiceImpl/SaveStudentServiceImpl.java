package com.yaodingjiaoyu.ServiceImpl;

import java.util.Date;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.EncryptionService;
import com.yaodingjiaoyu.Service.SaveStudentService;
import com.yaodingjiaoyu.dao.UserDao;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class SaveStudentServiceImpl implements SaveStudentService {
	private UserDao userDao;
	private EncryptionService encryptionService;
	private Logger logger;

	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int saveStudent(String name, String sex, Date birthday, String school, int level, String phone1,
			String phone2, int now_class, String parent_name, String address, int status, int normal_hour, int campus) {

		try {
			// 保存学生
			Student student = new Student();

			Campus campus_temp = new Campus();
			campus_temp.setPId(campus);
			student.setCampus(campus_temp);

			student.setName(name);
			student.setSex(sex);
			student.setBirthday(birthday);
			student.setSchool(school);

			Level level_temp = new Level();
			level_temp.setPId(level);
			student.setLevel(level_temp);
			student.setPhone2(phone2);
			student.setPhone1(phone1);
			student.setNowClass(now_class);
			student.setParentName(parent_name);
			student.setAddress(address);
			student.setStatus(0);
			student.setAllHour(normal_hour);
			student.setLastHour(normal_hour);

			student.setUsername(phone1);
			student.setPassword(encryptionService.My_MD5(phone1 + "+" + "123456"));
			student.setPower("ss");
			student.setLastTime(new Date());// 传入当前的系统时间

			// 获得学生的编号并插入合同

			return userDao.save(student);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数sex:" + sex + ",name:" + name + ",school:"
					+ school + ",level:" + level + ",now_class:" + now_class + ",address:" + address + ",birthday:"
					+ birthday + ",phone1:" + phone1 + ",phone2:" + phone2 + ",parent_name:" + parent_name + ",status:"
					+ status + ",normal_hour:" + normal_hour + ",campus:" + campus + ",MESSAGE:" + e.getMessage());
			return 0;
		}
	}

	@Override
	public void deleteStudent(int id) {
		Student student = (Student) userDao.getUser(id);
		userDao.delete(student);
	}

	@Override
	public boolean update(Student student, String name, String sex, Date birthday, String school, int level,
			int now_class, String phone1, String phone2, String address, String parent_name, int campus) {

		try {
			student.setName(name);
			student.setSex(sex);
			student.setBirthday(birthday);
			student.setSchool(school);
			student.setNowClass(now_class);

			Level level_temp = new Level();
			level_temp.setPId(level);
			student.setLevel(level_temp);

			student.setPhone1(phone1);
			student.setPhone2(phone2);
			student.setAddress(address);
			student.setParentName(parent_name);

			Campus campus_tmp = new Campus();
			campus_tmp.setPId(campus);
			student.setCampus(campus_tmp);

			return userDao.update(student);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数sex:" + sex + ",name:" + name + ",school:"
					+ school + ",level:" + level + ",now_class:" + now_class + ",address:" + address + ",birthday:"
					+ birthday + ",phone1:" + phone1 + ",phone2:" + phone2 + ",parent_name:" + parent_name
					+ ",studentId:" + student + ",campus:" + campus + ",MESSAGE:" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateBySd(Student student, String name, String sex, Date birthday, String school, int level,
			int now_class, String address, String parent_name) {
		try {
			student.setName(name);
			student.setSex(sex);
			student.setBirthday(birthday);
			student.setSchool(school);
			student.setNowClass(now_class);

			Level level_temp = new Level();
			level_temp.setPId(level);
			student.setLevel(level_temp);

			student.setAddress(address);
			student.setParentName(parent_name);

			return userDao.update(student);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数sex:" + sex + ",name:" + name + ",school:"
					+ school + ",level:" + level + ",now_class:" + now_class + ",address:" + address + ",birthday:"
					+ birthday + ",parent_name:" + parent_name + ",studentId:" + student + ",MESSAGE:"
					+ e.getMessage());
			return false;
		}
	}

	@Override
	public boolean SdDistributionStudent(Student student, int stuff) {

		try {
			student.setStatus(1);
			Stuff stuff_tmp = new Stuff();
			stuff_tmp.setPId(stuff);
			student.setStuff(stuff_tmp);
			return userDao.update(student);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数stuff:" + stuff + ",studentId:" + student
					+ ",MESSAGE:" + e.getMessage());
			return false;
		}
	}

}
