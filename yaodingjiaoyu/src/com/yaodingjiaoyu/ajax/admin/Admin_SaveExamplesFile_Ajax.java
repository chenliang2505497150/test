package com.yaodingjiaoyu.ajax.admin;



import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.yaodingjiaoyu.Service.ExamplesService;
import com.yaodingjiaoyu.Service.SaveUploadFileService;
import com.yaodingjiaoyu.Utils.ReadExcel;
import com.yaodingjiaoyu.datebase.pojo.Examples;


public class Admin_SaveExamplesFile_Ajax {
	private File file;//上传文件的file对象
	private String fileFileName;//上传文件的名称
	private String fileContentType;//上传文件的MIME类型
	private String description;//上传的描述信息
	private SaveUploadFileService saveUploadFileService;
	private Map<String, Object> resultMap = new HashMap<String,Object>();
	private ExamplesService saveExamplesService;
	
	public void setSaveExamplesService(ExamplesService saveExamplesService) {
		this.saveExamplesService = saveExamplesService;
	}
	public void setSaveUploadFileService(SaveUploadFileService saveUploadFileService) {
		this.saveUploadFileService = saveUploadFileService;
	}
	public File getFile() {
	return file;
	}
	public void setFile(File file) {
	this.file = file;
	}
	public String getFileFileName() {
	return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
	this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
	return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
	this.fileContentType = fileContentType;
	}
	public String getDescription() {
	return description;
	}
	public void setDescription(String description) {
	this.description = description;
	}
	
	private String  DoSaveUploadFile() {
		
    	
			resultMap.put("status", 200);
			resultMap.put("msg", "导入例子成功!");
			//根据服务器的文件保存地址和原文件名创建目录文件全路径
			String dir = ServletActionContext.getServletContext() 
			                        .getRealPath(ServletActionContext.getServletContext().getInitParameter("FILE_UPLOAD")) 
			                        + "/" + this.getFileFileName(); 
			File temp = new File(dir); 
			saveUploadFileService.SaveFile(file, temp);
			return dir;
	}
	//将上传的文件复制到目标目录下

    public String execute() { 
    	
		try {
			resultMap.clear();
	    	//解析文件并保存到数据库
			List<Examples> list = new ReadExcel().readExcel(DoSaveUploadFile());
			
			 //存入数据库	
			if (list != null) {
				saveExamplesService.SaveExamplesList(list);
			}
			else{
				resultMap.put("status", 201);
			    resultMap.put("msg", "文件不能为空或文件格式不正确!");
			}
	        
			/**
			 * 获得ActionContext对象，并将结果存入该对象中
			 */
			ActionContext.getContext().put("resultMap", resultMap);
		} catch (Exception e) {
			//初始化日志
			Logger logger = Logger.getLogger(this.getClass());
			logger.error(this.getClass().getName()+"-->execute:保存文件失败。,MESSAGE:"+e.getMessage());
			return "error";
		}
        return null; 
    } 

}
