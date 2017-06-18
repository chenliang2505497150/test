package com.yaodingjiaoyu.ServiceImpl;

import java.util.List;
import com.yaodingjiaoyu.Service.SearchStuffService;
import com.yaodingjiaoyu.dao.SearchStuffDao;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class SearchStuffServiceImpl implements SearchStuffService{
	private SearchStuffDao searchStuffDao;
	
	public void setSearchStuffDao(SearchStuffDao searchStuffDao) {
		this.searchStuffDao = searchStuffDao;
	}

	/**
	 * 这个方法只返回该校区不包含管理员以及校区权限的员工
	 */
	@Override
	public List<Stuff> findBySdRequest(String name, String job, String status, String part_time, String campus) {
		return searchStuffDao.findBySdRequest(name, job, status, part_time, campus);
	}

}
