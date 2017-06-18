package com.yaodingjiaoyu.ajax.sd;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassHourService;
import com.yaodingjiaoyu.Service.HetongService;
import com.yaodingjiaoyu.Service.UnitPriceService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.ClassHour;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class Sd_AddOperateHetong_Ajax {
	private int id;
	private String hetong_num;
	private int subject;
	private int course_type;
	private int normal_hour;
	private int ht_property;
	private double pos;
	private double cash;
	private String pos_num;
	private String receipt_num;
	private String remarks;
	private int hetong_type;
	private int stuff;

	private HetongService hetongService;
	private UserService studentService;
	private UnitPriceService unitPriceService;
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private ClassHourService classHourService;

	public int getStuff() {
		return stuff;
	}

	public void setStuff(int stuff) {
		this.stuff = stuff;
	}

	public int getHetong_type() {
		return hetong_type;
	}

	public void setHetong_type(int hetong_type) {
		this.hetong_type = hetong_type;
	}

	public void setClassHourService(ClassHourService classHourService) {
		this.classHourService = classHourService;
	}

	public void setUnitPriceService(UnitPriceService unitPriceService) {
		this.unitPriceService = unitPriceService;
	}

	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}

	public void setHetongService(HetongService hetongService) {
		this.hetongService = hetongService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHetong_num() {
		return hetong_num;
	}

	public void setHetong_num(String hetong_num) {
		this.hetong_num = hetong_num;
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public int getCourse_type() {
		return course_type;
	}

	public void setCourse_type(int course_type) {
		this.course_type = course_type;
	}

	public int getNormal_hour() {
		return normal_hour;
	}

	public void setNormal_hour(int normal_hour) {
		this.normal_hour = normal_hour;
	}

	public int getHt_property() {
		return ht_property;
	}

	public void setHt_property(int ht_property) {
		this.ht_property = ht_property;
	}

	public double getPos() {
		return pos;
	}

	public void setPos(double pos) {
		this.pos = pos;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public String getPos_num() {
		return pos_num;
	}

	public void setPos_num(String pos_num) {
		this.pos_num = pos_num;
	}

	public String getReceipt_num() {
		return receipt_num;
	}

	public void setReceipt_num(String receipt_num) {
		this.receipt_num = receipt_num;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	private void addOperate() {
		int success = 0;
		// 获得存储在SESSION中的校区和职工编号
		int campus = (int) ActionContext.getContext().getSession().get("campus");
		// int stuff = (int) ActionContext.getContext().getSession().get("ID");

		Student student = (Student) studentService.getUser(id);

		ClassHour classHour = classHourService.getClassHour(id + "", course_type + "");

		if (student != null) {
			int level = student.getLevel().getPId();
			double unit_price = unitPriceService.GetUnitPriceBy(campus, course_type, level);

			// 只有学生已经分配才能添加续费合同,且合同类型必须时续费
			if (hetong_type == 2) {

				success = hetongService.saveHetong(hetong_type, id, hetong_num, level, subject, normal_hour, unit_price,
						ht_property, course_type, pos, cash, pos_num, receipt_num, stuff, campus,
						remarks);

			} else {// 赠送或是退费
				success = hetongService.saveHetong(hetong_type, id, hetong_num, level, subject, normal_hour, unit_price,
						ht_property, course_type, pos, cash, pos_num, receipt_num, stuff, campus, remarks);
			}

			if (success != 0) {
				// 增加或减少课时
				student.setAllHour(student.getAllHour() + normal_hour);
				student.setLastHour(student.getLastHour() + normal_hour);
				studentService.update(student);

				if (classHour != null) {
					classHour.setAllHour(classHour.getAllHour() + normal_hour);
					classHour.setLastHour(classHour.getLastHour() + normal_hour);
					classHourService.update(classHour);
				} else {
					// 新建
					classHourService.saveBy(id, course_type, normal_hour, normal_hour);
				}

				// 更新

				resultMap.put("status", 200);
				resultMap.put("message", "添加合同成功");
			}

		} else {
			resultMap.put("status", 400);
			resultMap.put("message", "没有该学生");
		}
	}

	public String execute() {

		try {
			resultMap.clear();
			addOperate();
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			resultMap.put("status", 400);
			resultMap.put("message", "添加合同失败");
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:初始化学生列表失败。参数id：" + id + ",hetong_num:" + hetong_num
					+ ",subject:" + subject + ",course_type:" + course_type + ",normal_hour:" + normal_hour
					+ ",ht_property:" + ht_property + ",pos:" + pos + ",cash:" + cash + ",pos_num:" + pos_num
					+ ",receipt_num:" + receipt_num + ",remarks:" + remarks + ",hetong_type:" + hetong_type + ",SdId:"
					+ ActionContext.getContext().getSession().get("ID") + ",MESSAGE:" + e.getMessage());
			return "error";
		}
		return null;
	}
}
