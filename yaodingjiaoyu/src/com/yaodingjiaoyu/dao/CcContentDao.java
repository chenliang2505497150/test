package com.yaodingjiaoyu.dao;

import java.util.List;

import com.yaodingjiaoyu.datebase.pojo.CcContent;

public interface CcContentDao {

	public List<CcContent> findCcContentByAdmin(String campus,String time1,String time2,String stuff_id);
	public List<CcContent> findCcContentByCc(String name,String campus,String time1,String time2,String stuff_id);
	public List<CcContent> findCcContentByExamplesId(String id ,String stuff,String campus);
	public int addContent(CcContent ccContent);
}
