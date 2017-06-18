package com.yaodingjiaoyu.Service;

import java.util.Date;

import com.yaodingjiaoyu.datebase.pojo.Student;
public interface SaveStudentService {

	public int saveStudent(String name,String sex,Date birthday,String school,int level,String phone1,String phone2,int now_class,String parent_name,String address,int status,int normal_hour,int campus);
	public void deleteStudent(int id);
	public boolean update(Student student,String name,String sex,Date birthday,String school,int level,int now_class,String phone1,String phone2,String address,String parent_name,int campus);
	public boolean updateBySd(Student student,String name,String sex,Date birthday,String school,int level,int now_class,String address,String parent_name);
	//校长分配学生班主任
	public boolean SdDistributionStudent(Student student,int stuff);
}
