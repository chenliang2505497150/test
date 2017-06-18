package com.yaodingjiaoyu.Service;

import java.util.List;

public interface UserService {
	public Object CheckLogin(String username,String password);//检查员工用户登录是否正确
	public Object getUser(int id);//根据ID查询员工或，学生对象
	public int  save(Object user);//返回持久化对象的ID
	public boolean update(Object user);
	public boolean delete(Object user);
	public List<Object> findByName(String name);
	public boolean checkSignIn(String username);//检查该用户是否注册
}
