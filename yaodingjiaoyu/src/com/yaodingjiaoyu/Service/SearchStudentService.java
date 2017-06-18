package com.yaodingjiaoyu.Service;

import java.util.List;
import java.util.Map;

import com.yaodingjiaoyu.datebase.pojo.Student;

public interface SearchStudentService {
	//姓名，学校，年级，班级，电话，是否结课，开始查看位置，显示的最大条数
	public List<Student> findByCrRequest(String name, String school, String level, String now_class, String phone,
			String stu_status,String stuff,String campus);
	
	public List<Student> findByAdminRequest(String name,String school,String level,String now_class,String phone,String stu_status,String campus);

	public List<Map<String, Object>> findByCrRequest_MAP(String name,String school,String level,String now_class,String phone,String stu_status,String isend,String stuff,String campus);

	public List<Map<String, Object>> findByTrRequest_MAP(String name,String school,String level,String now_class,String phone,String stuff,String campus);

	public boolean TrHaveStudent(String student, String stuff, String campus);
	
	public List<Student> findBySdRequest(String name,String school,String level,String now_class,String phone,String stu_status,String campus,String status,String stuff);

	public int getStudentBy(String name,String phone1,String phone2);
}
