package com.yaodingjiaoyu.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.HetongService;
import com.yaodingjiaoyu.dao.HetongDao;
import com.yaodingjiaoyu.datebase.pojo.Campus;
import com.yaodingjiaoyu.datebase.pojo.CourseType;
import com.yaodingjiaoyu.datebase.pojo.Hetong;
import com.yaodingjiaoyu.datebase.pojo.HetongType;
import com.yaodingjiaoyu.datebase.pojo.HtProperty;
import com.yaodingjiaoyu.datebase.pojo.Level;
import com.yaodingjiaoyu.datebase.pojo.Student;
import com.yaodingjiaoyu.datebase.pojo.Stuff;
import com.yaodingjiaoyu.datebase.pojo.Subject;

public class HetongServiceImpl implements HetongService {

	private HetongDao save_hetong_dao;
	private Logger logger;

	public void setSave_hetong_dao(HetongDao save_hetong_dao) {
		this.save_hetong_dao = save_hetong_dao;
	}

	@Override
	public int saveHetong(int hetong_type, int student, String hetong_num, int level, int subject, int normal_hour,
			double unitPrice, int ht_property, int course_type, double pos, double cash, String pos_num,
			String receipt_num, int stuff, int campus, String remarks) {

		try {
			Hetong hetong = new Hetong();

			Student student_temp = new Student();
			student_temp.setPId(student);
			hetong.setStudent(student_temp);

			hetong.setHetongNum(hetong_num);

			Level level_temp = new Level();
			level_temp.setPId(level);
			hetong.setLevel(level_temp);

			Subject subject_temp = new Subject();
			subject_temp.setPId(subject);

			hetong.setSubject(subject_temp);
			hetong.setNormalHour(normal_hour);
			hetong.setUnitPrice(unitPrice);

			HetongType hetongType_temp = new HetongType();
			hetongType_temp.setPId(hetong_type);
			hetong.setHetongType(hetongType_temp);

			HtProperty htProperty_temp = new HtProperty();
			htProperty_temp.setPId(ht_property);
			hetong.setHtProperty(htProperty_temp);

			CourseType courseType_temp = new CourseType();
			courseType_temp.setPId(course_type);
			hetong.setCourseType(courseType_temp);

			hetong.setPos(pos);
			hetong.setCash(cash);
			hetong.setPosNum(pos_num);
			hetong.setReceiptNum(receipt_num);
			hetong.setSignTime(new Date());

			Stuff stuff_temp = new Stuff();
			stuff_temp.setPId(stuff);
			hetong.setStuff(stuff_temp);

			hetong.setRemarks(remarks);

			Campus campus_temp = new Campus();
			campus_temp.setPId(campus);
			hetong.setCampus(campus_temp);

			hetong.setStatus(0);// 标记未审核

			return save_hetong_dao.save(hetong);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数hetong_type:" + hetong_type + ",student:"
					+ student + ",hetong_num:" + hetong_num + ",level:" + level + ",subject:" + subject
					+ ",normal_hour:" + normal_hour + ",unitPrice:" + unitPrice + ",ht_property:" + ht_property
					+ ",course_type:" + course_type + ",pos:" + pos + ",cash:" + cash + ",pos_num:" + pos_num
					+ ",receipt_num:" + receipt_num + ",stuff:" + stuff + ",campus:" + campus + ",remarks:" + remarks
					+ ",MESSAGE:" + e.getMessage());
			return 0;
		}
	}

	@Override
	public List<Hetong> findHetongByAdmin(String hetong_num, String name, String school, String level,
			String hetong_type, String campus, String stuff, String course_type, String time1, String time2) {
		// TODO Auto-generated method stub
		return save_hetong_dao.findHetongByAdmin(hetong_num, name, school, level, hetong_type, campus, stuff,
				course_type, time1, time2);
	}

	@Override
	public Hetong findById(int id) {
		// TODO Auto-generated method stub
		return save_hetong_dao.findById(id);
	}

	@Override
	public boolean UpdateHetong(Hetong hetong) {
		// TODO Auto-generated method stub
		return save_hetong_dao.UpdateHetong(hetong);
	}

	@Override
	public boolean updateHetongBy(Hetong hetong, int level, int unit_price, String hetong_num, int subject,
			int normal_hour, int hetong_type, int ht_property, int course_type, double pos, double cash, String pos_num,
			String receipt_num) {

		try {
			// 更新合同
			Level level_tmp = new Level();
			level_tmp.setPId(level);
			hetong.setLevel(level_tmp);

			hetong.setHetongNum(hetong_num);

			Subject subject_tmp = new Subject();
			subject_tmp.setPId(subject);
			hetong.setSubject(subject_tmp);

			hetong.setNormalHour(normal_hour);
			HetongType hetongType = new HetongType();
			hetongType.setPId(hetong_type);
			hetong.setHetongType(hetongType);

			HtProperty htProperty = new HtProperty();
			htProperty.setPId(ht_property);
			hetong.setHtProperty(htProperty);

			CourseType courseType = new CourseType();
			courseType.setPId(course_type);
			hetong.setCourseType(courseType);

			hetong.setPos(pos);
			hetong.setCash(cash);
			hetong.setPosNum(pos_num);
			hetong.setReceiptNum(receipt_num);

			return save_hetong_dao.UpdateHetong(hetong);
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数hetong_type:" + hetong_type + ",hetongId:"
					+ hetong.getPId() + ",hetong_num:" + hetong_num + ",level:" + level + ",subject:" + subject
					+ ",normal_hour:" + normal_hour + ",unit_price:" + unit_price + ",ht_property:" + ht_property
					+ ",course_type:" + course_type + ",pos:" + pos + ",cash:" + cash + ",pos_num:" + pos_num
					+ ",receipt_num:" + receipt_num + ",hetong_type:" + hetong_type + ",MESSAGE:" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean DeleteHetong(Hetong hetong) {

		return save_hetong_dao.DeleteHetong(hetong);
	}

	@Override
	public double findHetong_Sum_Money(String hetong_num, String name, String school, String level, String hetong_type,
			String campus, String stuff, String course_type, String time1, String time2) {
		
		return save_hetong_dao.findHetong_Sum_Money(hetong_num, name, school, level, hetong_type, campus, stuff, course_type, time1, time2);
	}

}
