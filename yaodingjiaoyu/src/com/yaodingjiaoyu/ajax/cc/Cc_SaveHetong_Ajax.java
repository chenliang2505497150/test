package com.yaodingjiaoyu.ajax.cc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ClassHourService;
import com.yaodingjiaoyu.Service.HetongService;
import com.yaodingjiaoyu.Service.SaveStudentService;
import com.yaodingjiaoyu.Service.SearchStudentService;
import com.yaodingjiaoyu.Service.UnitPriceService;
import com.yaodingjiaoyu.Service.UserService;
import com.yaodingjiaoyu.datebase.pojo.ClassHour;
import com.yaodingjiaoyu.datebase.pojo.Student;

public class Cc_SaveHetong_Ajax {
	private HetongService saveHetongService;
	private SaveStudentService saveStudentService;
	private UnitPriceService unitPriceService;
	private ClassHourService classHourService;
	private SearchStudentService searchStudentService;
	private UserService studentService;

	public void setStudentService(UserService studentService) {
		this.studentService = studentService;
	}

	public void setSearchStudentService(SearchStudentService searchStudentService) {
		this.searchStudentService = searchStudentService;
	}

	public void setClassHourService(ClassHourService classHourService) {
		this.classHourService = classHourService;
	}

	public void setUnitPriceService(UnitPriceService unitPriceService) {
		this.unitPriceService = unitPriceService;
	}

	public void setSaveHetongService(HetongService saveHetongService) {
		this.saveHetongService = saveHetongService;
	}

	public void setSaveStudentService(SaveStudentService saveStudentService) {
		this.saveStudentService = saveStudentService;
	}

	private Map<String, Object> resultMap = new HashMap<String, Object>();

	private int student_id = 0;
	private int hetong_id = 0;
	private int unit_price = 0;
	private int classhour_id = 0;
	private String name;
	private String sex;
	private Date birthday;
	private String school;
	private int level = 0;
	private int now_class;
	private String phone1;
	private String phone2;
	private String parent_name;
	private String address;
	private String hetong_num;
	private int subject;
	private int normal_hour;
	private int ht_property;
	private int course_type;
	private double pos;
	private double cash;
	private String pos_num;
	private String receipt_num;
	private String remarks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getNow_class() {
		return now_class;
	}

	public void setNow_class(int now_class) {
		this.now_class = now_class;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public int getCourse_type() {
		return course_type;
	}

	public void setCourse_type(int course_type) {
		this.course_type = course_type;
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

	public boolean checkInput() {
		boolean success = false;
		if ((course_type == 0) || (ht_property == 0) || (normal_hour == 0) || (subject == 0) || (now_class == 0)
				|| ("".equals(name)) || ("".equals(school)) || (birthday == null) || ("".equals(sex)) || (0 == level)
				|| ("".equals(phone1)) || ("".equals(parent_name)) || ("".equals(address))
				|| ("".equals(hetong_num))) {
			success = false;
		} else {
			success = true;
		}
		return success;
	}

	public void doRequest() {
		// 获得存储在SESSION中的校区和职工编号
		int stuff = (int) ActionContext.getContext().getSession().get("ID");
		int campus = (int) ActionContext.getContext().getSession().get("campus");
		unit_price = unitPriceService.GetUnitPriceBy(campus, course_type, level);

		if (checkInput()) {// 健壮性判断
			// 判断该学生以前有没有添加过，如果添加过，直接获得该学生
			student_id = searchStudentService.getStudentBy(name, phone1, phone2);
			
			if (student_id != 0) {
				//更新学生和课时数据
				updateStuAndCTB();
				//不回滚添加合同
				AddHetong_MethodTwo(campus,stuff);
			} else {
				createStuAndCTB(campus);
				//可以回滚添加合同
				AddHetong_MethodOne(campus,stuff);
			}

		} else {
			resultMap.put("message", "不合法的输入!");
			resultMap.put("status", "400");

			return;
		}
	}
	
	//更新学生和课时数据
	public void updateStuAndCTB() {
		Student student = (Student) studentService.getUser(student_id);
		ClassHour classHour = classHourService.getClassHour(student_id + "", course_type + "");
		
		//相当于续费，只是增加课时，并不创建学生以及课时表
		student.setAllHour(student.getAllHour() + normal_hour);
		student.setLastHour(student.getLastHour() + normal_hour);
		studentService.update(student);
		
		if (classHour != null) {
			classHour.setAllHour(classHour.getAllHour() + normal_hour);
			classHour.setLastHour(classHour.getLastHour() + normal_hour);
			classHourService.update(classHour);
			
			classhour_id = classHour.getPId();
		} else {
			// 新建课时表
			classhour_id = classHourService.saveBy(student_id, course_type, normal_hour, normal_hour);
		}
	}

	//创建学生以及课时表
	public void createStuAndCTB(int campus) {
		// 新建学生、并获取编号
		student_id = saveStudentService.saveStudent(name, sex, birthday, school, level, phone1, phone2, campus,
				parent_name, address, 0, normal_hour, campus);

		if (student_id != 0) {
			classhour_id = classHourService.saveBy(student_id, course_type, normal_hour, normal_hour);
			
		} else {
			resultMap.put("message", "请检查学生信息是否完整!");
			resultMap.put("status", "400");
			return;
		}
	}
	
	
	//带回滚学生信息的插入
		public void AddHetong_MethodOne(int campus,int stuff) {
			if (classhour_id != 0) {
				// 更新合同表
				hetong_id = saveHetongService.saveHetong(1, student_id, hetong_num, level, subject, normal_hour,
						unit_price, ht_property, course_type, pos, cash, pos_num, receipt_num, stuff, campus,
						remarks);
				if (hetong_id != 0) {
					resultMap.put("message", "添加合同成功!");
					resultMap.put("status", "200");
					return;
				} else {
					//合同没有写入成功、回滚之前的数据
					// 删除学生信息
					saveStudentService.deleteStudent(student_id);

					resultMap.put("message", "请检查合同信息是否完整!");
					resultMap.put("status", "400");
					return;
				}
			} else {
				// 删除学生信息
				saveStudentService.deleteStudent(student_id);
				resultMap.put("message", "请检查课时信息是否完整!");
				resultMap.put("status", "400");
				return;
			}
		}
		
		
		//不带回滚学生信息的插入
			public void AddHetong_MethodTwo(int campus,int stuff) {
				if (classhour_id != 0) {
					// 更新合同表
					hetong_id = saveHetongService.saveHetong(1, student_id, hetong_num, level, subject, normal_hour,
							unit_price, ht_property, course_type, pos, cash, pos_num, receipt_num, stuff, campus,
							remarks);
					if (hetong_id != 0) {
						resultMap.put("message", "添加合同成功!");
						resultMap.put("status", "200");
						return;
					} else {
						//合同没有写入成功、回滚之前的数据
						// 删除学生信息
						saveStudentService.deleteStudent(student_id);

						resultMap.put("message", "请检查合同信息是否完整!");
						resultMap.put("status", "400");
						return;
					}
				} else {
					resultMap.put("message", "请检查课时信息是否完整!");
					resultMap.put("status", "400");
					return;
				}
			}

	// 获取前台传来的参数
	public String execute() {

		try {
			resultMap.clear();
			doRequest();// 处理请求
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			// 初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:保存合同信息失败。参数sex：" + sex + ",school:" + school + ",name:"
					+ name + ",level:" + level + ",now_class:" + now_class + ",address:" + address + ",birthday:"
					+ birthday + ",phone1:" + phone1 + ",phone2:" + phone2 + ",parent_name:" + parent_name
					+ ",hetong_num:" + hetong_num + ",subject:" + subject + ",normal_hour:" + normal_hour
					+ ",ht_property:" + ht_property + ",course_type:" + course_type + ",pos:" + pos + ",cash:" + cash
					+ ",pos_num:" + pos_num + ",receipt_num:" + receipt_num + ",remarks:" + remarks + ",MESSAGE:"
					+ e.getMessage());
			return "error";

		}
		return null;
	}
}
