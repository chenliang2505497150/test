package com.yaodingjiaoyu.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.DayService;
import com.yaodingjiaoyu.Service.TransLateService;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.CcContent;
import com.yaodingjiaoyu.datebase.pojo.Channel;
import com.yaodingjiaoyu.datebase.pojo.ClassContent;
import com.yaodingjiaoyu.datebase.pojo.ClassTable;
import com.yaodingjiaoyu.datebase.pojo.ClassTime;
import com.yaodingjiaoyu.datebase.pojo.Examples;
import com.yaodingjiaoyu.datebase.pojo.Hetong;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Probability;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class TransLateServiceImpl implements TransLateService {
	Map<Integer, String> level = null;
	Map<Integer, String> probability = null;
	Map<Integer, String> channel = null;
	Map<Integer, String> campus = null;
	Map<Integer, String> youxiao = null;
	Map<Integer, String> zhuangtai = null;
	private DayService dayService;
	private Logger logger;

	public void setDayService(DayService dayService) {
		this.dayService = dayService;
	}

	public void setLevel(Map<Integer, String> level) {
		this.level = level;
	}

	public void setProbability(Map<Integer, String> probability) {
		this.probability = probability;
	}

	public void setChannel(Map<Integer, String> channel) {
		this.channel = channel;
	}

	public void setCampus(Map<Integer, String> campus) {
		this.campus = campus;
	}

	public void setYouxiao(Map<Integer, String> youxiao) {
		this.youxiao = youxiao;
	}

	public void setZhuangtai(Map<Integer, String> zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

	@Override
	public List<Map<String, Object>> transLateExamples(List<Level> level_list, List<Probability> probability_list,
			List<Channel> channel_list, List<Campus> campus_list, List<Map<String, Object>> list) {
		try {
			if (list != null && list.size() >= 1) {

				for (Level tmp : level_list) {
					level.put(tmp.getPId(), tmp.getName());
				}
				for (Probability tmp : probability_list) {
					probability.put(tmp.getPId(), tmp.getName());
				}
				for (Channel tmp : channel_list) {
					channel.put(tmp.getPId(), tmp.getName());
				}
				for (Campus tmp : campus_list) {
					campus.put(tmp.getPId(), tmp.getName());
				}
				youxiao.put(0, "无效");
				youxiao.put(1, "有效");
				zhuangtai.put(0, "未上门");
				zhuangtai.put(1, "已上门");
				// 翻译
				for (Map<String, Object> tmp : list) {
					if (tmp.containsKey("level")) {// 含有这个字段
						tmp.put("level", level.get(tmp.get("level")));
					}

					if (tmp.containsKey("youxiao")) {// 含有这个字段
						tmp.put("youxiao", youxiao.get(tmp.get("youxiao")));
					}

					if (tmp.containsKey("zhuangtai")) {// 含有这个字段
						tmp.put("zhuangtai", zhuangtai.get(tmp.get("zhuangtai")));
					}
					if (tmp.containsKey("probability")) {// 含有这个字段
						tmp.put("probability", probability.get(tmp.get("probability")));
					}
					if (tmp.containsKey("channel")) {// 含有这个字段
						tmp.put("channel", channel.get(tmp.get("channel")));
					}
					if (tmp.containsKey("campus")) {// 含有这个字段
						tmp.put("campus", campus.get(tmp.get("campus")));
					}

					SimpleDateFormat dateFm = new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss"); // 格式化当前系统日期
					if (tmp.containsKey("creat_time")) {// 含有这个字段
						if (("".equals(tmp.get("creat_time"))) || (tmp.get("creat_time") == null)) {
							tmp.put("creat_time", null);
						} else {
							Date creat_time = (Date) tmp.get("creat_time");
							tmp.put("creat_time", dateFm.format(creat_time));
						}
					}

					if (tmp.containsKey("last_time")) {
						if (("".equals(tmp.get("last_time"))) || (tmp.get("last_time") == null)) {
							tmp.put("last_time", null);
						} else {
							Date last_time = (Date) tmp.get("last_time");
							tmp.put("last_time", dateFm.format(last_time));
						}
					}

				}

				return list;

			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数level_list:" + level_list + ",probability_list:"
					+ probability_list + ",channel_list:" + channel_list + ",campus_list:" + campus_list + ",list:"
					+ list + ",MESSAGE:" + e.getMessage());
			return null;
		}

	}

	// 管理员查询跟踪记录
	@Override
	public List<Map<String, Object>> transLateCcContent(List<CcContent> ccContentList) {

		try {
			List<Map<String, Object>> ccContent_resultlist = new ArrayList<Map<String, Object>>();
			ccContent_resultlist.clear();
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss"); // 格式化当前系统日期
			if (ccContentList != null) {
				for (CcContent content : ccContentList) {
					Map<String, Object> stuMap = new HashMap<String, Object>();
					stuMap.put("P_ID", content.getPId());
					if (content.getExamples() == null) {
						stuMap.put("name", "");
					} else {
						stuMap.put("name", content.getExamples().getName());

					}
					if (content.getStuff() == null) {
						stuMap.put("stuff", "");
					} else {
						stuMap.put("stuff", content.getStuff().getName());
					}
					if (content.getInsertTime() == null) {
						stuMap.put("insertTime", "");
					} else {
						stuMap.put("insertTime", dateFm.format(content.getInsertTime()));
					}

					stuMap.put("contents", content.getContents());
					stuMap.put("campus", content.getCampus().getName());

					ccContent_resultlist.add(stuMap);
				}
				return ccContent_resultlist;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数ccContentList:" + ccContentList + ",MESSAGE:"
					+ e.getMessage());
			return null;
		}
	}

	// Admin的接口
	@Override
	public List<Map<String, Object>> transLateStudentList(List<Student> studentList) {
		try {
			List<Map<String, Object>> stu_resultlist = new ArrayList<Map<String, Object>>();
			stu_resultlist.clear();
			if (studentList != null) {
				for (Student stu : studentList) {
					Map<String, Object> stuMap = new HashMap<String, Object>();
					stuMap.put("P_ID", stu.getPId());
					stuMap.put("name", stu.getName());
					stuMap.put("school", stu.getSchool());
					stuMap.put("level", stu.getLevel().getName());
					stuMap.put("now_class", stu.getNowClass());
					stuMap.put("keshi", stu.getAllHour() + "(" + stu.getLastHour() + ")");
					stuMap.put("phone1", stu.getPhone1());
					stuMap.put("phone2", stu.getPhone2());
					stuMap.put("campus", stu.getCampus().getName());
					stu_resultlist.add(stuMap);
				}
				return stu_resultlist;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数studentList:" + studentList + ",MESSAGE:"
					+ e.getMessage());
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> transLateStuffList(List<Stuff> stuffs) {
		List<Map<String, Object>> stuff_resultlist = new ArrayList<Map<String, Object>>();
		try {
			stuff_resultlist.clear();
			if (stuffs != null) {
				for (Stuff stuff : stuffs) {
					Map<String, Object> stuMap = new HashMap<String, Object>();
					stuMap.put("P_ID", stuff.getPId());
					stuMap.put("name", stuff.getName());
					stuMap.put("phone", stuff.getPhone());
					stuff_resultlist.add(stuMap);
				}
				return stuff_resultlist;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(
					this.getClass().getName() + "-->execute:运行失败。参数stuffs:" + stuffs + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> transLateClassContent(List<ClassContent> ContentList) {

		try {
			List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
			resultlist.clear();
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss"); // 格式化当前系统日期
			if (ContentList != null) {
				for (ClassContent content : ContentList) {
					Map<String, Object> tmp_map = new HashMap<String, Object>();
					tmp_map.put("P_ID", content.getPId());
					if (content.getStudent() == null) {
						tmp_map.put("name", "");
					} else {
						tmp_map.put("name", content.getStudent().getName());

					}
					if (content.getJob() == null) {
						tmp_map.put("job", "");
					} else {
						tmp_map.put("job", content.getJob().getName());

					}

					if (content.getStuff() == null) {
						tmp_map.put("stuff", "");
					} else {
						tmp_map.put("stuff", content.getStuff().getName());
					}
					if (content.getInsertTime() == null) {
						tmp_map.put("insertTime", "");
					} else {
						tmp_map.put("insertTime", dateFm.format(content.getInsertTime()));
					}

					tmp_map.put("contents", content.getContents());
					tmp_map.put("campus", content.getCampus().getName());

					resultlist.add(tmp_map);
				}
				return resultlist;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数ContentList:" + ContentList + ",MESSAGE:"
					+ e.getMessage());
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> transLateCrAndCcList(List<Stuff> stuffs) {
		try {
			List<Map<String, Object>> stuff_resultlist = new ArrayList<Map<String, Object>>();
			stuff_resultlist.clear();
			if (stuffs != null) {
				for (Stuff stuff : stuffs) {
					Map<String, Object> stuMap = new HashMap<String, Object>();
					stuMap.put("P_ID", stuff.getPId());
					stuMap.put("name", stuff.getName() + "(" + stuff.getJob().getName() + ")");
					stuff_resultlist.add(stuMap);
				}
				return stuff_resultlist;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(
					this.getClass().getName() + "-->execute:运行失败。参数stuffs:" + stuffs + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> transLateHetongList(List<Hetong> List) {
		try {
			List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
			resultlist.clear();
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss"); // 格式化当前系统日期
			if (List != null) {
				for (Hetong obj : List) {
					Map<String, Object> tmp = new HashMap<String, Object>();
					tmp.put("P_ID", obj.getPId());
					tmp.put("hetongNum", obj.getHetongNum());
					tmp.put("name", obj.getStudent().getName());
					tmp.put("level", obj.getLevel().getName());
					tmp.put("subject", obj.getSubject().getName());
					tmp.put("keshi", obj.getNormalHour());
					tmp.put("hetongType", obj.getHetongType().getName());
					tmp.put("courseType", obj.getCourseType().getName());
					tmp.put("money", obj.getCash() + obj.getPos());
					tmp.put("signTime", dateFm.format(obj.getSignTime()));
					tmp.put("stuff", obj.getStuff().getName());
					tmp.put("campus", obj.getCampus().getName());
					resultlist.add(tmp);
				}
				return resultlist;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数List:" + List + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

	// 班主任的接口
	@Override
	public List<Map<String, Object>> transLateStudent(List<Level> level_list, List<Map<String, Object>> list) {
		try {
			int all = 0;
			int last = 0;
			List<Map<String, Object>> stu_resultlist = new ArrayList<Map<String, Object>>();
			stu_resultlist.clear();
			if (list != null && list.size() >= 1) {

				for (Level tmp : level_list) {
					level.put(tmp.getPId(), tmp.getName());
				}

				for (Map<String, Object> tmp : list) {
					Map<String, Object> stuMap = new HashMap<String, Object>();

					all = (int) tmp.get("all_hour") / 3;

					if (tmp.get("total") != null) {
						last = all - Integer.parseInt(tmp.get("total").toString());
					} else {
						last = all;
					}

					stuMap.put("P_ID", tmp.get("P_ID"));
					stuMap.put("name", tmp.get("name"));
					stuMap.put("school", tmp.get("school"));
					stuMap.put("level", level.get(tmp.get("level")));
					stuMap.put("keshi", tmp.get("all_hour") + "(" + tmp.get("last_hour") + ")");
					stuMap.put("phone1", tmp.get("phone1"));
					stuMap.put("phone2", tmp.get("phone2"));
					stuMap.put("now_class", tmp.get("now_class"));
					if (tmp.containsKey("total")) {
						stuMap.put("total", last + "/" + all);
					}
					stu_resultlist.add(stuMap);
				}
				return stu_resultlist;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数level_list:" + level_list + ",list:" + list
					+ ",MESSAGE:" + e.getMessage());
			return null;
		}

	}

	@Override
	public List<Map<String, Object>> transLateSimpleHetongList(List<Hetong> List) {
		try {
			List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
			resultlist.clear();
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss"); // 格式化当前系统日期
			if (List != null) {
				for (Hetong obj : List) {
					Map<String, Object> tmp = new HashMap<String, Object>();
					tmp.put("P_ID", obj.getPId());
					tmp.put("hetongNum", obj.getHetongNum());
					tmp.put("name", obj.getStudent().getName());
					tmp.put("level", obj.getLevel().getName());
					tmp.put("subject", obj.getSubject().getName());
					tmp.put("keshi", obj.getNormalHour());
					tmp.put("hetongType", obj.getHetongType().getName());
					tmp.put("courseType", obj.getCourseType().getName());
					tmp.put("money", obj.getCash() + obj.getPos());
					tmp.put("signTime", dateFm.format(obj.getSignTime()));
					resultlist.add(tmp);
				}
				return resultlist;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数List:" + List + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

	@Override
	public Map<String, Object> transLateExample(Examples examples) {
		try {
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss"); // 格式化当前系统日期

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("P_ID", examples.getPId());
			result.put("name", examples.getName());
			result.put("school", examples.getSchool());
			result.put("level", examples.getLevel().getName());
			result.put("now_class", examples.getNowClass());
			result.put("phone1", examples.getPhone1());
			result.put("phone2", examples.getPhone2());
			result.put("address", examples.getAddress());

			if (examples.getYouxiao() == 0) {
				result.put("youxiao", "无效");
			} else {
				result.put("youxiao", "有效");
			}

			if (examples.getZhuangtai() == 0) {
				result.put("zhuangtai", "未上门");
			} else {
				result.put("zhuangtai", "已上门");
			}

			if (examples.getStatus() == 0) {
				result.put("status", "未分配");
			} else {
				result.put("status", "已分配");
			}

			if (null != examples.getCreatTime()) {
				result.put("creat_time", dateFm.format(examples.getCreatTime()));
			} else {
				result.put("creat_time", "");
			}

			result.put("probability", examples.getProbability().getName());
			result.put("channel", examples.getChannel().getName());
			
			try {
				if (null != examples.getStuff()) {
					result.put("stuff", examples.getStuff().getName());
				} else {
					result.put("stuff", "");
				}
			} catch (Exception e) {
				// 避免no session异常
				result.put("stuff", "");
			}

			if (null != examples.getLastTime()) {
				result.put("last_time", dateFm.format(examples.getLastTime()));
			} else {
				result.put("last_time", "");
			}

			result.put("campus", examples.getCampus().getName());
			return result;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数examplesId:" + examples.getPId() + ",MESSAGE:"
					+ e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Map<String, Object>> transLateClassTableList(List<ClassTable> List) {
		try {
			Map<String, Map<String, Object>> resultmap = new HashMap<String, Map<String, Object>>();
			resultmap.clear();
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd"); // 格式化当前系统日期

			if (List != null) {
				for (ClassTable obj : List) {
					Map<String, Object> tmp = resultmap.get(obj.getStuff().getPId() + "");
					if (tmp == null) {
						tmp = new HashMap<String, Object>();
					}

					String key = dayService.getDayOfWeek(obj.getDayTime()) + "_" + obj.getClassTime().getPId();

					Map<String, Object> detail = new HashMap<String, Object>();
					detail.put("P_ID", obj.getPId());
					detail.put("subject", obj.getSubject().getName());
					detail.put("classtable_id", obj.getPId());
					detail.put("student", obj.getStudent().getName());
					detail.put("student_id", obj.getStudent().getPId());
					detail.put("day_time", dateFm.format(obj.getDayTime()));
					detail.put("class_time", obj.getClassTime().getPId());
					detail.put("status", obj.getStatus());
					detail.put("course_type", obj.getCourseType().getName());

					Map<String, Object> current = (Map<String, Object>) tmp.get(key);
					if (current == null) {
						if (null != obj.getRemarks()) {
							detail.put("title", obj.getCourseType().getName() + ": " + obj.getStudent().getName() + "("
									+ obj.getRemarks() + ")" + " ");
						} else {
							detail.put("title",
									obj.getCourseType().getName() + ": " + obj.getStudent().getName() + " ");
						}
					} else {
						if (null != obj.getRemarks()) {
							detail.put("title", current.get("title") + obj.getStudent().getName() + " ");
						} else {
							detail.put("title", current.get("title") + obj.getStudent().getName() + "("
									+ obj.getRemarks() + ")" + " ");
						}
					}

					if (obj.getStatus() == 0) {// 表示该课目前未上
						detail.put("color", obj.getCourseType().getColor());// 灰色
					} else {
						detail.put("color", "#E1E1E1");// 灰色
					}

					tmp.put(key, detail);

					resultmap.put(obj.getStuff().getPId() + "", tmp);
				}
				return resultmap;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数List:" + List + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> transLateClassTimeList(List<ClassTime> classTimes) {
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list.clear();
			if (classTimes != null) {
				for (ClassTime tmp : classTimes) {
					Map<String, Object> stuMap = new HashMap<String, Object>();
					stuMap.put("P_ID", tmp.getPId());
					stuMap.put("classtime", tmp.getClasstime());
					list.add(stuMap);
				}
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数classTimes:" + classTimes + ",MESSAGE:"
					+ e.getMessage());
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> transLateClassTableListToMap(List<ClassTable> List) {
		try {
			List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
			resultlist.clear();
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy年 MM月 dd日"); // 格式化当前系统日期
			if (List != null) {
				for (ClassTable obj : List) {
					Map<String, Object> tmp = new HashMap<String, Object>();
					tmp.put("P_ID", obj.getPId());
					tmp.put("subject", obj.getSubject().getName());
					tmp.put("student", obj.getStudent().getName());
					tmp.put("stuff", obj.getStuff().getName());
					tmp.put("level", obj.getLevel().getName());
					tmp.put("day_time", dateFm.format(obj.getDayTime()));
					tmp.put("class_time", obj.getClassTime().getClasstime());
					tmp.put("course_type", obj.getCourseType().getName());

					if (obj.getStatus() == 0) {
						tmp.put("status", "未上");
					} else {
						tmp.put("status", "已上");
					}

					resultlist.add(tmp);
				}
				return resultlist;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数List:" + List + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

	@Override
	public Map<String, Object> transLateStudent(Student student) {

		try {
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy年 MM月 dd日"); // 格式化当前系统日期

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("P_ID", student.getPId());
			result.put("name", student.getName());
			result.put("sex", student.getSex());
			result.put("birthday", dateFm.format(student.getBirthday()));
			result.put("school", student.getSchool());
			result.put("level", student.getLevel().getName());
			result.put("now_class", student.getNowClass());
			result.put("phone1", student.getPhone1());
			result.put("phone2", student.getPhone2());
			result.put("address", student.getAddress());
			result.put("parent_name", student.getParentName());

			if (student.getStatus() == 0) {
				result.put("status", "未分配");
			} else {
				result.put("status", "已分配");
			}

			result.put("stuff", student.getStuff().getName());
			result.put("all_hour", student.getAllHour());
			result.put("last_hour", student.getLastHour());
			result.put("present_hour", student.getPresentHour());
			result.put("campus", student.getCampus().getName());
			result.put("username", student.getUsername());

			return result;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数studentId:" + student + ",MESSAGE:"
					+ e.getMessage());
			return null;
		}
	}

	@Override
	public Map<String, Object> transLateHetong(Hetong hetong) {

		try {
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy年 MM月 dd日 HH:mm:ss"); // 格式化当前系统日期

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("P_ID", hetong.getPId());
			result.put("student", hetong.getStudent().getName());
			result.put("hetong_num", hetong.getHetongNum());
			result.put("sign_time", dateFm.format(hetong.getSignTime()));
			result.put("level", hetong.getLevel().getName());
			result.put("subject", hetong.getSubject().getName());
			result.put("normal_hour", hetong.getNormalHour());
			result.put("unit_price", hetong.getUnitPrice());
			result.put("hetong_type", hetong.getHetongType().getName());
			result.put("ht_property", hetong.getHtProperty().getName());
			result.put("course_type", hetong.getCourseType().getName());

			if (hetong.getStatus() == 0) {
				result.put("status", "未审核");
			} else {
				result.put("status", "已审核");
			}

			result.put("pos", hetong.getPos());
			result.put("cash", hetong.getCash());
			result.put("pos_num", hetong.getPosNum());
			result.put("receipt_num", hetong.getReceiptNum());
			result.put("stuff", hetong.getStuff().getName());
			result.put("remarks", hetong.getRemarks());
			result.put("campus", hetong.getCampus().getName());

			return result;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(
					this.getClass().getName() + "-->execute:运行失败。参数hetongId:" + hetong + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

	@Override
	public Map<String, Object> transLateClassTableListForTr(List<ClassTable> List) {

		try {
			Map<String, Object> tmp = new HashMap<String, Object>();

			if (List != null) {
				for (ClassTable obj : List) {
					Map<String, Object> detail = new HashMap<String, Object>();
					String key = dayService.getDayOfWeek(obj.getDayTime()) + "_" + obj.getClassTime().getPId();

					detail.put("student", obj.getStudent().getName());
					detail.put("title", obj.getCourseType().getName() + ": " + obj.getSubject().getName() + " ");

					if (obj.getStatus() == 0) {// 表示该课目前未上
						detail.put("color", obj.getCourseType().getColor());// 灰色
					} else {
						detail.put("color", "#E1E1E1");// 灰色
					}

					tmp.put(key, detail);

				}
				return tmp;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数List:" + List + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> transLateStuffDetailList(List<Stuff> stuffs) {
		List<Map<String, Object>> stuff_resultlist = new ArrayList<Map<String, Object>>();
		try {
			stuff_resultlist.clear();
			if (stuffs != null) {
				for (Stuff stuff : stuffs) {
					Map<String, Object> stuMap = new HashMap<String, Object>();
					stuMap.put("P_ID", stuff.getPId());
					stuMap.put("sex", stuff.getSex());
					stuMap.put("name", stuff.getName());
					stuMap.put("phone", stuff.getPhone());
					stuMap.put("username", stuff.getUsername());
					stuMap.put("job", stuff.getJob().getName());
					if (stuff.getStatus() == 0) {
						stuMap.put("status", "正常");
					} else {
						stuMap.put("status", "冻结");
					}

					if (stuff.getPartTime() == 0) {
						stuMap.put("part_time", "常规员工");
					} else {
						stuMap.put("part_time", "兼职员工");
					}

					stuff_resultlist.add(stuMap);
				}
				return stuff_resultlist;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(
					this.getClass().getName() + "-->execute:运行失败。参数stuffs:" + stuffs + ",MESSAGE:" + e.getMessage());
			return null;
		}
	}

}
