package com.yaodingjiaoyu.ServiceImpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.yaodingjiaoyu.Service.SaveUploadFileService;

public class SaveUploadFileServiceImpl implements SaveUploadFileService {

	private Logger logger;

	@Override
	public boolean SaveFile(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), 2048);
			out = new BufferedOutputStream(new FileOutputStream(dst), 2048);
			byte[] buffer = new byte[2048];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);

			}
			return true;
		} catch (Exception e) {
			// 初始化日志
			logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName() + "-->execute:运行失败。参数src:" + src + ",dst:" + dst + ",MESSAGE:"
					+ e.getMessage());
			return false;
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					// 初始化日志
					logger = Logger.getLogger(this.getClass());
					logger.error(this.getClass().getName() + "-->execute:运行失败。参数src:" + src + ",dst:" + dst
							+ ",MESSAGE:" + e.getMessage());

				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					// 初始化日志
					logger = Logger.getLogger(this.getClass());
					logger.error(this.getClass().getName() + "-->execute:运行失败。参数src:" + src + ",dst:" + dst
							+ ",MESSAGE:" + e.getMessage());

				}
			}
		}
	}

}
