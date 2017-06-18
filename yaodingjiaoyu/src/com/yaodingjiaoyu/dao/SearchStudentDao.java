package com.yaodingjiaoyu.dao;

import java.util.List;
import java.util.Map;

import com.yaodingjiaoyu.datebase.pojo.Student;

public interface SearchStudentDao {
	public List<Student> findByCrRequest(String name, String school, String level, String now_class, String phone,
			String stu_status,String stuff,String campus);
	
	public List<Student> findByAdminRequest(String name,String school,String level,String now_class,String phone,String stu_status,String campus);

	public List<Map<String, Object>> findByCrRequest_MAP(String name,String school,String level,String now_class,String phone,String stu_status,String isend,String stuff,String campus);
	
	public List<Map<String, Object>> findByTrRequest_MAP(String name,String school,String level,String now_class,String phone,String stuff,String campus);

	public boolean TrHaveStudent(String student,String stuff,String campus);
	
	public List<Student> findBySdRequest(String name,String school,String level,String now_class,String phone,String stu_status,String campus,String status,String stuff);

	public int getStudentBy(String name, String phone1, String phone2);
}
