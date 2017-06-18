package com.yaodingjiaoyu.ServiceImpl;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.UnitPriceService;
import com.yaodingjiaoyu.dao.UnitPriceDao;
import com.yaodingjiaoyu.datebase.pojo.CampusPrice;

public class UnitPriceServiceImpl implements UnitPriceService {
	private UnitPriceDao unit_price_dao;
	private Logger logger;

	public void setUnit_price_dao(UnitPriceDao unit_price_dao) {
		this.unit_price_dao = unit_price_dao;
	}

	@Override
	public int GetUnitPriceBy(int campus, int course_type, int level) {
		try {
			int price = 0;
			CampusPrice campusPrice = unit_price_dao.GetUnitPriceBy(campus, course_type);
			if (campusPrice != null) {
				switch (level) {
				case 1:
					price = campusPrice.getL1();
					break;
				case 2:
					price = campusPrice.getL2();
					break;
				case 3:
					price = campusPrice.getL3();
					break;
				case 4:
					price = campusPrice.getL4();
					break;
				case 5:
					price = campusPrice.getL5();
					break;
				case 6:
					price = campusPrice.getL6();
					break;
				case 7:
					price = campusPrice.getL7();
					break;
				case 8:
					price = campusPrice.getL8();
					break;
				case 9:
					price = campusPrice.getL9();
					break;
				case 10:
					price = campusPrice.getL10();
					break;
				case 11:
					price = campusPrice.getL11();
					break;
				case 12:
					price = campusPrice.getL12();
					break;

				}
			} else {
				price = -1;
			}

			return price;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数campus:" + campus + ",course_type:"
					+ course_type + ",level:" + level + ",MESSAGE:" + e.getMessage());
			return 0;
		}
	}

}
