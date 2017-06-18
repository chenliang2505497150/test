package com.yaodingjiaoyu.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.ClassContentService;
import com.yaodingjiaoyu.dao.ClassContentDao;
import com.yaodingjiaoyu.datebase.pojo.ClassContent;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class ClassContentServiceImpl implements ClassContentService {

	private ClassContentDao search_classContent;
	private Logger logger;

	public void setSearch_classContent(ClassContentDao search_classContent) {
		this.search_classContent = search_classContent;
	}

	@Override
	public List<ClassContent> findClassContentByAdmin(String campus, String time1, String time2, String stuff_id,
			String name) {
		// TODO Auto-generated method stub
		return search_classContent.findClassContentByAdmin(campus, time1, time2, stuff_id, name);
	}

	@Override
	public List<ClassContent> findClassContentByCr(String campus, String time1, String time2, String Cr,
			String stuff_id, String name) {
		// TODO Auto-generated method stub
		return search_classContent.findClassContentByCr(campus, time1, time2, Cr, stuff_id, name);
	}

	@Override
	public List<ClassContent> findCrContentByStudentId(String id, String stuff, String campus) {
		// TODO Auto-generated method stub
		return search_classContent.findCrContentByStudentId(id, stuff, campus);
	}

	@Override
	public int addContent(int id, Date insert_time, String contents, Stuff stuff) {

		try {
			ClassContent classContent = new ClassContent();
			Student student = new Student();
			student.setPId(id);
			classContent.setStudent(student);
			classContent.setInsertTime(insert_time);
			classContent.setContents(contents);
			classContent.setJob(stuff.getJob());
			classContent.setCampus(stuff.getCampus());
			classContent.setStuff(stuff);
			return search_classContent.addContent(classContent);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数id:" + id + ",insert_time:" + insert_time
					+ ",contents:" + contents + ",stuff:" + stuff + ",MESSAGE:" + e.getMessage());
			return 0;
		}
	}

	@Override
	public List<ClassContent> findTrContentByStudentId(String id, String stuff, String campus) {
		// TODO Auto-generated method stub
		return search_classContent.findTrContentByStudentId(id, stuff, campus);
	}

}
