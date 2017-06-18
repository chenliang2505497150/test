package com.yaodingjiaoyu.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.CcContentService;
import com.yaodingjiaoyu.dao.CcContentDao;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.CcContent;
import com.yaodingjiaoyu.datebase.pojo.Examples;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class CcContentServiceImpl implements CcContentService {

	private CcContentDao search_cc_content_dao;
	private Logger logger;

	public void setSearch_cc_content_dao(CcContentDao search_cc_content_dao) {
		this.search_cc_content_dao = search_cc_content_dao;
	}

	@Override
	public List<CcContent> findCcContentByAdmin(String campus, String time1, String time2, String stuff_id) {

		return search_cc_content_dao.findCcContentByAdmin(campus, time1, time2, stuff_id);
	}

	@Override
	public List<CcContent> findCcContentByCc(String name, String campus, String time1, String time2, String stuff_id) {
		// TODO Auto-generated method stub
		return search_cc_content_dao.findCcContentByCc(name, campus, time1, time2, stuff_id);
	}

	@Override
	public List<CcContent> findCcContentByExamplesId(String id, String stuff, String campus) {
		// TODO Auto-generated method stub
		return search_cc_content_dao.findCcContentByExamplesId(id, stuff, campus);
	}

	@Override
	public int addContent(int id, Date insert_time, String contents, int campus, int stuff) {
		try {
			CcContent ccContent = new CcContent();

			Examples examples = new Examples();
			examples.setPId(id);
			ccContent.setExamples(examples);

			ccContent.setInsertTime(insert_time);
			ccContent.setContents(contents);

			Campus campus_tmp = new Campus();
			campus_tmp.setPId(campus);
			ccContent.setCampus(campus_tmp);

			Stuff stuff_tmp = new Stuff();
			stuff_tmp.setPId(stuff);
			ccContent.setStuff(stuff_tmp);

			return search_cc_content_dao.addContent(ccContent);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数id:" + id + ",insert_time:" + insert_time
					+ ",contents:" + contents + ",campus:" + campus + ",stuff:" + stuff + ",MESSAGE:" + e.getMessage());
			return 0;
		}
	}

}
