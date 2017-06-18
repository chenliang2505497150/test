package com.yaodingjiaoyu.ServiceImpl;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.EncryptionService;

public class EncryptionServiceImpl implements EncryptionService {
	private Logger logger;

	@Override
	public String My_MD5(String plainText) {

		String re_md5 = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数plainText:" + plainText + ",MESSAGE:"
					+ e.getMessage());
			return null;
		}
		return re_md5;
	}

}
