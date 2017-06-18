<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>系统版本</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta content="" name="description" />
    <meta content="webthemez" name="author" />
   
    <link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    
</head>
<body>
	
            <div id="page-inner"> 
               
	            <div class="row">
	                <div class="col-md-12">
	                    <!-- Advanced Tables -->
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                             系统信息
	                            
	                        </div>
	                        <div class="panel-body">
	                            <div class="table-responsive">
	                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
	                                    <tr class="odd gradeX">
	                                            <td class="center">Auther</td>
	                                            <td class="center">chen.liang</td>
	                                    </tr>
	                                    <tr class="odd gradeX">
	                                            <td class="center">联系方式</td>
	                                            <td class="center">15651885595</td>
	                                    </tr>
	                                    <tr class="odd gradeX">
	                                            <td class="center">备案号</td>
	                                            <td class="center">苏ICP备17000323号</td>
	                                    </tr>
	                                    <tr class="odd gradeX">
	                                            <td class="center">当前版本</td>
	                                            <td class="center">${application.SYS_VERSION}</td>
	                                    </tr>
	                                    <tr class="odd gradeX">
	                                            <td class="center">系统消息</td>
	                                            <td class="center">${application.SYS_MESSAGE}</td>
	                                    </tr>
	                                </table>
	                            </div>
	                            
	                        </div>
	                    </div>
	                    <!--End Advanced Tables -->
	                </div>
	            </div>     
       		 </div>
                
</body>
</html>
