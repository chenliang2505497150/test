package com.yaodingjiaoyu.dao;

import java.util.List;

public interface UserDao {
	
		public int save(Object user);
		
		public Object getUser(int id);
		
		public boolean update(Object user);
		
		public boolean delete(Object user);
		
		public List<Object> findByName(String name);
		
		public Object findByPass(String username,String password);
		
		public boolean checkSignIn(String username);//检查该用户是否注册
}
