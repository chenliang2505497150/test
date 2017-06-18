package com.yaodingjiaoyu.ServiceImpl;

import java.util.Date;
import org.apache.log4j.Logger;
import com.yaodingjiaoyu.Service.EncryptionService;
import com.yaodingjiaoyu.Service.ResetPassService;
import com.yaodingjiaoyu.dao.UserDao;
import com.yaodingjiaoyu.datebase.pojo.Stuff;

public class ResetPassServiceImpl implements ResetPassService {

	private EncryptionService encryptionService;
	private UserDao userdao;
	private Logger logger;

	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	@Override
	public String SdResetStuff(Stuff stuff) {
		try {
			// 获得当前的系统时间的时间戳作为随机的密码
			String password = new Date().getTime() + "";

			String username = stuff.getUsername();
			stuff.setPassword(encryptionService.My_MD5(username + "+" + password));
			if (userdao.update(stuff)) {
				return password;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->SdResetStuff:重置密码。参数stuffId:" + stuff.getPId() + ",MESSAGE:"
					+ e.getMessage());
			return null;
		}

	}

}
