package com.yaodingjiaoyu.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.ExamplesService;
import com.yaodingjiaoyu.dao.ExamplesDao;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.Channel;
import com.yaodingjiaoyu.datebase.pojo.Examples;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Probability;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class ExamplesServiceImpl implements ExamplesService {
	private Logger logger;
	private ExamplesDao save_examples;

	public ExamplesDao getSave_examples() {
		return save_examples;
	}

	public void setSave_examples(ExamplesDao save_examples) {
		this.save_examples = save_examples;
	}

	@Override
	public boolean SaveExamples(Examples examples) {
		// TODO Auto-generated method stub
		return save_examples.SaveExamples(examples);
	}

	@Override
	public boolean SaveExamplesList(List<Examples> list) {
		// TODO Auto-generated method stub
		return save_examples.SaveExamplesList(list);
	}

	@Override // 管理员的查询
	public List<Examples> findByAdminRequest(String name, String school, String level, String now_class, String phone,
			String address, String youxiao, String zhuangtai, String probability, String channel, String campus) {

		return save_examples.findByAdminRequest(name, school, level, now_class, phone, address, youxiao, zhuangtai,
				probability, channel, campus);
	}

	@Override
	public List<Map<String, Object>> findByAdminRequest_MAP(String name, String school, String level, String now_class,
			String phone, String address, String youxiao, String zhuangtai, String probability, String channel,
			String campus, String cc_total) {
		// TODO Auto-generated method stub
		return save_examples.findByAdminRequest_MAP(name, school, level, now_class, phone, address, youxiao, zhuangtai,
				probability, channel, campus, cc_total);
	}

	@Override
	public List<Map<String, Object>> findByCcRequest_MAP(String name, String school, String level, String now_class,
			String phone, String address, String youxiao, String zhuangtai, String probability, String channel,
			String campus, String stuff, String cc_total) {
		// TODO Auto-generated method stub
		return save_examples.findByCcRequest_MAP(name, school, level, now_class, phone, address, youxiao, zhuangtai,
				probability, channel, campus, stuff, cc_total);
	}

	@Override
	public Examples findById(int id) {
		// TODO Auto-generated method stub
		return save_examples.findById(id);
	}

	@Override
	public boolean CcUpdateExamples(Examples examples, String name, String school, int level, int now_class,
			String address, int youxiao, int zhuangtai, int probability, int channel) {

		try {
			examples.setName(name);
			examples.setSchool(school);
			examples.setNowClass(now_class);
			examples.setAddress(address);

			Level level_tmp = new Level();
			level_tmp.setPId(level);
			examples.setLevel(level_tmp);

			examples.setYouxiao(youxiao);
			examples.setZhuangtai(zhuangtai);

			Probability probability_tmp = new Probability();
			probability_tmp.setPId(probability);
			examples.setProbability(probability_tmp);

			Channel channel_tmp = new Channel();
			channel_tmp.setPId(channel);
			examples.setChannel(channel_tmp);

			return save_examples.UpdateExamples(examples);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数examplesId:" + examples.getPId() + ",name:"
					+ name + ",school:" + school + ",level:" + level + ",now_class:" + now_class + ",address:" + address
					+ ",youxiao:" + youxiao + ",zhuangtai:" + zhuangtai + ",probability:" + probability + ",channel:"
					+ channel + ",MESSAGE:" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean CcAddContentUpdateExamples(Examples examples, String youxiao,String zhuangtai, String probability, Date last_time) {
		try {
			examples.setLastTime(last_time);
			
			if (!("no".equals(zhuangtai))) {
				examples.setZhuangtai(Integer.parseInt(zhuangtai));
			}
			
			if (!("no".equals(youxiao))) {
				examples.setYouxiao(Integer.parseInt(youxiao));
			}

			if (!("no".equals(probability))) {
				Probability probability_tmp = new Probability();
				probability_tmp.setPId(Integer.parseInt(probability));
				examples.setProbability(probability_tmp);
			}
			return save_examples.UpdateExamples(examples);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数examplesId:" + examples.getPId() + ",youxiao:"
					+ youxiao + ",probability:" + probability + ",last_time:" + last_time + ",MESSAGE:"
					+ e.getMessage());
			return false;
		}
	}

	@Override
	public boolean DeleteExamples(Examples examples) {

		return save_examples.DeleteExamples(examples);
	}

	@Override
	public boolean AdminUpdateExamples(Examples examples, String phone1, String phone2, String name, String school,
			int level, int now_class, String address, int youxiao, int zhuangtai, int probability, int channel,
			int campus) {
		try {
			examples.setName(name);
			examples.setSchool(school);
			examples.setNowClass(now_class);
			examples.setAddress(address);
			examples.setPhone1(phone1);
			examples.setPhone2(phone2);

			Campus campus_tmp = new Campus();
			campus_tmp.setPId(campus);
			examples.setCampus(campus_tmp);

			Level level_tmp = new Level();
			level_tmp.setPId(level);
			examples.setLevel(level_tmp);

			examples.setYouxiao(youxiao);
			examples.setZhuangtai(zhuangtai);

			Probability probability_tmp = new Probability();
			probability_tmp.setPId(probability);
			examples.setProbability(probability_tmp);

			Channel channel_tmp = new Channel();
			channel_tmp.setPId(channel);
			examples.setChannel(channel_tmp);

			return save_examples.UpdateExamples(examples);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数examplesId:" + examples.getPId() + ",name:"
					+ name + ",school:" + school + ",level:" + level + ",now_class:" + now_class + ",address:" + address
					+ ",youxiao:" + youxiao + ",zhuangtai:" + zhuangtai + ",probability:" + probability + ",channel:"
					+ channel + ",campus:" + campus + ",phone1:" + phone1 + ",phone2:" + phone2 + ",MESSAGE:"
					+ e.getMessage());
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> findBySdRequest_MAP(String name, String school, String level, String now_class,
			String phone, String address, String youxiao, String zhuangtai, String probability, String channel,
			String campus, String stuff, String cc_total, String status) {
		// TODO Auto-generated method stub
		return save_examples.findBySdRequest_MAP(name, school, level, now_class, phone, address, youxiao, zhuangtai,
				probability, channel, campus, stuff, cc_total, status);
	}

	@Override
	public boolean SdDistributionExamples(Examples examples, int cc) {

		try {
			examples.setStatus(1);
			Stuff stuff = new Stuff();
			stuff.setPId(cc);

			examples.setStuff(stuff);
			return save_examples.UpdateExamples(examples);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数examplesId:" + examples.getPId() + ",cc:" + cc
					+ ",MESSAGE:" + e.getMessage());
			return false;
		}
	}

}
