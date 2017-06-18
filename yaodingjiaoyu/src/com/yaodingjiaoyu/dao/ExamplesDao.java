package com.yaodingjiaoyu.dao;

import java.util.List;
import java.util.Map;

import com.yaodingjiaoyu.datebase.pojo.Examples;

public interface ExamplesDao {

	public boolean SaveExamples(Examples examples);
	public boolean SaveExamplesList(List<Examples> list);
	public List<Examples> findByAdminRequest(String name,String school,String level,String now_class,String phone,String address,String youxiao,String zhuangtai,String probability,String channel,String campus);
	public List<Map<String, Object>> findByAdminRequest_MAP(String name,String school,String level,String now_class,String phone,String address,String youxiao,String zhuangtai,String probability,String channel,String campus,String cc_total);
	public List<Map<String, Object>> findByCcRequest_MAP(String name,String school,String level,String now_class,String phone,String address,String youxiao,String zhuangtai,String probability,String channel,String campus,String stuff,String cc_total);
	public List<Map<String, Object>> findBySdRequest_MAP(String name,String school,String level,String now_class,String phone,String address,String youxiao,String zhuangtai,String probability,String channel,String campus,String stuff,String cc_total,String status);
	//通过id获得例子
	public Examples findById(int id);
	//更新例子
	public boolean UpdateExamples(Examples examples);
	
	//删除例子
	public boolean DeleteExamples(Examples examples);
}
