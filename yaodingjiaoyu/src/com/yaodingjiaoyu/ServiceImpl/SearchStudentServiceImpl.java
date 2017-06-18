package com.yaodingjiaoyu.ServiceImpl;

import java.util.List;
import java.util.Map;

import com.yaodingjiaoyu.Service.SearchStudentService;
import com.yaodingjiaoyu.dao.SearchStudentDao;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class SearchStudentServiceImpl implements SearchStudentService{
private SearchStudentDao searchStudent;

	public SearchStudentDao getSearchStudent() {
	return searchStudent;
}

public void setSearchStudent(SearchStudentDao searchStudent) {
	this.searchStudent = searchStudent;
}

@Override
public List<Student> findByCrRequest(String name, String school, String level, String now_class, String phone,
		String stu_status,String stuff,String campus) {
	// TODO Auto-generated method stub
	return searchStudent.findByCrRequest(name, school, level, now_class, phone, stu_status, stuff, campus);
}

@Override
public List<Student> findByAdminRequest(String name, String school, String level, String now_class, String phone,
		String stu_status,String campus) {
	return searchStudent.findByAdminRequest(name, school, level, now_class, phone, stu_status,campus);
}

@Override
public List<Map<String, Object>> findByCrRequest_MAP(String name, String school, String level, String now_class,
		String phone, String stu_status, String isend, String stuff, String campus) {
	// TODO Auto-generated method stub
	return searchStudent.findByCrRequest_MAP(name, school, level, now_class, phone, stu_status, isend, stuff, campus);
}

@Override
public List<Map<String, Object>> findByTrRequest_MAP(String name, String school, String level, String now_class,
		String phone, String stuff, String campus) {
	// TODO Auto-generated method stub
	return searchStudent.findByTrRequest_MAP(name, school, level, now_class, phone, stuff, campus);
}

@Override
public boolean TrHaveStudent(String student, String stuff, String campus) {
	// TODO Auto-generated method stub
	return searchStudent.TrHaveStudent(student, stuff, campus);
}

@Override
public List<Student> findBySdRequest(String name, String school, String level, String now_class, String phone,
		String stu_status, String campus, String status,String stuff) {
	// TODO Auto-generated method stub
	return searchStudent.findBySdRequest(name, school, level, now_class, phone, stu_status, campus, status,stuff);
}

@Override
public int getStudentBy(String name, String phone1, String phone2) {
	// TODO Auto-generated method stub
	return searchStudent.getStudentBy(name, phone1, phone2);
}







}
