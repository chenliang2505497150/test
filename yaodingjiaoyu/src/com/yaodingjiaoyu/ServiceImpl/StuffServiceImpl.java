package com.yaodingjiaoyu.ServiceImpl;

import java.util.List;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.dao.UserDao;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class StuffServiceImpl implements UserService {

	private UserDao userdao;
	private Logger logger;


	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

	@Override
	public Stuff CheckLogin(String username, String password) {
		try {
			Stuff stuff = (Stuff) userdao.findByPass(username, password);

			if (stuff != null) {
				return stuff;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数username:" + username + ",password:" + password
					+ ",MESSAGE:" + e.getMessage());
			return null;
		}

	}

	@Override
	public Object getUser(int id) {
		// TODO Auto-generated method stub
		Stuff stuff = (Stuff) userdao.getUser(id);
		return stuff;
	}

	@Override
	public int save(Object user) {
		// TODO Auto-generated method stub
		return userdao.save(user);
	}

	@Override
	public boolean update(Object user) {
		// TODO Auto-generated method stub
		return userdao.update(user);
	}

	@Override
	public boolean delete(Object user) {
		// TODO Auto-generated method stub
		return userdao.delete(user);
	}

	@Override
	public List<Object> findByName(String name) {
		// TODO Auto-generated method stub
		return userdao.findByName(name);
	}

	@Override
	public boolean checkSignIn(String username) {
		// TODO Auto-generated method stub
		return userdao.checkSignIn(username);
	}

}
