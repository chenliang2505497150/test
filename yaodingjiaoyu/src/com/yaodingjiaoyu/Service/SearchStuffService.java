package com.yaodingjiaoyu.Service;

import java.util.List;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public interface SearchStuffService {
	//传入校区和权限作为参数
	public List<Stuff> findBySdRequest(String name,String job,String status,String part_time,String campus);	
}
