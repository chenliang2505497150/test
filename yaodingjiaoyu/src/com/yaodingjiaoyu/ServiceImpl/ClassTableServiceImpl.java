package com.yaodingjiaoyu.ServiceImpl;

import java.util.Date;
import java.util.List;

import com.yaodingjiaoyu.Service.ClassTableService;
import com.yaodingjiaoyu.dao.ClassTableDao;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;
import com.yaodingjiaoyu.datebase.pojo.ClassTime;
import com.yaodingjiaoyu.datebase.pojo.CourseType;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;
import com.yaodingjiaoyu.datebase.pojo.Subject;

public class ClassTableServiceImpl implements ClassTableService{

	private ClassTableDao classTableDao;
	
	public void setClassTableDao(ClassTableDao classTableDao) {
		this.classTableDao = classTableDao;
	}

	@Override
	public List<ClassTable> findClassTableByCr(String time1, String time2, String campus, List<Stuff> stuff) {
		
		return classTableDao.findClassTableByCr(time1, time2, campus, stuff);
	}

	@Override
	public int getCount(String student, String course_type) {

		return classTableDao.getCount(student, course_type);
	}

	@Override
	public int getCountByDay(String student, String class_time, String day_time) {

		return classTableDao.getCountByDay(student, class_time, day_time);
	}

	@Override
	public ClassTable getTeacherTable(String stuff, String day_time, String class_time) {
		// TODO Auto-generated method stub
		return classTableDao.getTeacherTable(stuff, day_time, class_time);
	}

	@Override
	public int getCountByTeacher(String stuff, String class_time, String day_time) {
		// TODO Auto-generated method stub
		return classTableDao.getCountByTeacher(stuff, class_time, day_time);
	}

	@Override
	public int saveClassTable(Stuff stuff, Student student, Date day_time, String class_time, String course_type) {
		
		Subject subject = new Subject();
		
		//根据老师的岗位判读老师所教的科目
		switch (stuff.getJob().getPId()) {
		case 5:
			subject.setPId(1);
			break;
		case 6:
			subject.setPId(2);
			break;
		
		case 7:
			subject.setPId(3);
			break;
		case 8:
			subject.setPId(4);
			break;
		case 9:
			subject.setPId(5);
			break;
		case 10:
			subject.setPId(6);
			break;
		case 11:
			subject.setPId(7);
			break;
		case 12:
			subject.setPId(8);
			break;
		case 13:
			subject.setPId(9);
			break;
		
		}
		
		
		ClassTable classTable = new ClassTable();
		classTable.setSubject(subject);
		classTable.setStuff(stuff);
		classTable.setStudent(student);
		classTable.setDayTime(day_time);
		
		ClassTime classTime = new ClassTime();
		classTime.setPId(Integer.parseInt(class_time));
		classTable.setClassTime(classTime);
		
		classTable.setStatus(0);
		classTable.setCampus(stuff.getCampus());
		
		CourseType courseType = new CourseType();
		courseType.setPId(Integer.parseInt(course_type));
		classTable.setCourseType(courseType);
		
		classTable.setLevel(student.getLevel());
		return classTableDao.saveClassTable(classTable);
	}

	@Override
	public List<ClassTable> findClassTable(String subject, List<Stuff> tr, String cr, String student, String time1,
			String time2, String status, String class_time, String campus, String course_type, String level) {
		
		return classTableDao.findClassTable(subject, tr, cr, student, time1, time2, status, class_time, campus, course_type, level);
	}

	@Override
	public boolean deleteTable(int id) {
		ClassTable classTable = classTableDao.geTable(id);
		return classTableDao.deleteTable(classTable);
	}

	@Override
	public ClassTable geTable(int id) {
		
		return classTableDao.geTable(id);
	}

	@Override
	public boolean update(ClassTable classTable) {
		
		return classTableDao.update(classTable);
	}

	@Override
	public List<ClassTable> findClassTableByTr(String time1, String time2, String campus, String stuff) {
		// TODO Auto-generated method stub
		return classTableDao.findClassTableByTr(time1, time2, campus, stuff);
	}

	@Override
	public List<ClassTable> findTrClassTable(String subject, String stuff, String student, String time1, String time2,
			String status, String class_time, String campus, String course_type, String level) {
		// TODO Auto-generated method stub
		return classTableDao.findTrClassTable(subject, stuff, student, time1, time2, status, class_time, campus, course_type, level);
	}

}
