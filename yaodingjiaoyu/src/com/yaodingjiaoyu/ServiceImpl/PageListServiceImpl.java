package com.yaodingjiaoyu.ServiceImpl;

import java.util.List;

import com.yaodingjiaoyu.Service.PageListService;
import com.yaodingjiaoyu.dao.PageListDao;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.Channel;
import com.yaodingjiaoyu.datebase.pojo.ClassTime;
import com.yaodingjiaoyu.datebase.pojo.CourseType;
import com.yaodingjiaoyu.datebase.pojo.HetongType;
import com.yaodingjiaoyu.datebase.pojo.HtProperty;
import com.yaodingjiaoyu.datebase.pojo.Job;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Probability;
import com.yaodingjiaoyu.datebase.pojo.Stuff;
import com.yaodingjiaoyu.datebase.pojo.Subject;

public class PageListServiceImpl implements PageListService {
	private PageListDao loadPageListDao;

	public PageListDao getLoadPageListDao() {
		return loadPageListDao;
	}

	public void setLoadPageListDao(PageListDao loadPageListDao) {
		this.loadPageListDao = loadPageListDao;
	}

	@Override
	public List<Level> getLevel_list() {
		// TODO Auto-generated method stub
		return loadPageListDao.getLevel_list();
	}

	@Override
	public List<Probability> getProbability_list() {
		// TODO Auto-generated method stub
		return loadPageListDao.getProbability_list();
	}

	@Override
	public List<Channel> getChannel_list() {
		// TODO Auto-generated method stub
		return loadPageListDao.getChannel_list();
	}

	@Override
	public List<Campus> getCampus_list() {
		// TODO Auto-generated method stub
		return loadPageListDao.getCampus_list();
	}

	@Override
	public List<Stuff> getSalerListByCampus(String campus) {
		// TODO Auto-generated method stub
		return loadPageListDao.getSalerListByCampus(campus);
	}

	@Override
	public List<Stuff> getcr_trListByCampus(String campus) {
		// TODO Auto-generated method stub
		return loadPageListDao.getcr_trListByCampus(campus);
	}

	@Override
	public List<HetongType> getHetongTypeList() {
		// TODO Auto-generated method stub
		return loadPageListDao.getHetongTypeList();
	}

	@Override
	public List<CourseType> getCourseTypeList() {
		// TODO Auto-generated method stub
		return loadPageListDao.getCourseTypeList();
	}

	@Override
	public List<HtProperty> getHtPropertyList() {
		// TODO Auto-generated method stub
		return loadPageListDao.getHtPropertyList();
	}

	@Override
	public List<Subject> getSubjectList() {
		// TODO Auto-generated method stub
		return loadPageListDao.getSubjectList();
	}

	@Override
	public List<Stuff> getTrListByCampus(String campus) {
		// TODO Auto-generated method stub
		return loadPageListDao.getTrListByCampus(campus);
	}

	@Override
	public List<Stuff> getcr_ccListByCampus(String campus) {
		// TODO Auto-generated method stub
		return loadPageListDao.getcr_ccListByCampus(campus);
	}

	@Override
	public List<ClassTime> getClassTimeList() {
		// TODO Auto-generated method stub
		return loadPageListDao.getClassTimeList();
	}

	@Override
	public List<Stuff> getTrListByCampusAndInput(String campus, String input) {
		// TODO Auto-generated method stub
		return loadPageListDao.getTrListByCampusAndInput(campus, input);
	}

	@Override
	public List<Stuff> getcrListByCampus(String campus) {
		// TODO Auto-generated method stub
		return loadPageListDao.getcrListByCampus(campus);
	}

	@Override
	public List<Job> getJob_list() {
		
		return loadPageListDao.getJob_list();
	}

	@Override
	public List<Job> getJob_listForSd() {
		
		return loadPageListDao.getJob_listForSd();
	}

}
