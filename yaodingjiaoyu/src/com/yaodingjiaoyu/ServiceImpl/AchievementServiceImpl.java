package com.yaodingjiaoyu.ServiceImpl;

import java.util.List;
import java.util.Map;

import com.yaodingjiaoyu.Service.AchievementService;
import com.yaodingjiaoyu.dao.AchievementDao;

public class AchievementServiceImpl implements AchievementService {

	private AchievementDao achievementdao;

	public void setAchievementdao(AchievementDao achievementdao) {
		this.achievementdao = achievementdao;
	}

	@Override
	public List<Map<String, Object>> getCcAchievement(String name, String time1, String time2, String campus) {
		return achievementdao.getCcAchievement(name, time1, time2, campus);

	}

	@Override
	public List<Map<String, Object>> getCrAchievement(String name, String time1, String time2, String campus) {
		// TODO Auto-generated method stub
		return achievementdao.getCrAchievement(name, time1, time2, campus);
	}

	@Override
	public List<Map<String, Object>> getTrAchievement(String name, String time1, String time2, String campus) {
		// TODO Auto-generated method stub
		return achievementdao.getTrAchievement(name, time1, time2, campus);
	}

}
