package com.yaodingjiaoyu.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.yaodingjiaoyu.datebase.pojo.Examples;

public interface ExamplesService {
	public boolean SaveExamples(Examples examples);
	
	public boolean SaveExamplesList(List<Examples> list);
	
	public List<Examples> findByAdminRequest(String name, String school, String level, String now_class, String phone,
			String address, String youxiao, String zhuangtai, String probability, String channel, String campus);
	
	public List<Map<String, Object>> findByAdminRequest_MAP(String name, String school, String level, String now_class, String phone, String address,String youxiao, String zhuangtai, String probability, String channel, String campus,String cc_total);


	public List<Map<String, Object>> findByCcRequest_MAP(String name,String school,String level,String now_class,String phone,String address,String youxiao,String zhuangtai,String probability,String channel,String campus,String stuff,String cc_total);

	public Examples findById(int id);
	
	public boolean CcUpdateExamples(Examples examples,String name,String school,int level,int now_class,String address,int youxiao,int zhuangtai,int probability,int channel);
	
	public boolean AdminUpdateExamples(Examples examples,String phone1,String phone2,String name,String school,int level,int now_class,String address,int youxiao,int zhuangtai,int probability,int channel,int campus);
	
	public boolean CcAddContentUpdateExamples(Examples examples,String youxiao,String zhuangtai,String probability,Date last_time);
	
	public boolean DeleteExamples(Examples examples);
	
	public List<Map<String, Object>> findBySdRequest_MAP(String name,String school,String level,String now_class,String phone,String address,String youxiao,String zhuangtai,String probability,String channel,String campus,String stuff,String cc_total,String status);

	//校长分配例子给销售 
	public boolean SdDistributionExamples(Examples examples,int  cc);

}
