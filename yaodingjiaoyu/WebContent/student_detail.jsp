<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
 
<!-- Bootstrap Styles-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FontAwesome Styles-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
     <!-- Morris Chart Styles-->
   
        <!-- Custom Styles-->
    <link href="assets/css/custom-styles.css" rel="stylesheet" />

     <!-- TABLE STYLES-->
    <link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />    
</head>
<body>

	<div id="page-inner" style="background:#349Be4;"> 
	
		<div class="row">
                <div class="col-md-12">
                	<!-- Advanced Tables -->
			        <div class="panel panel-default">
			        
			        	<div class="panel-heading">
			                             学生的信息列表
						</div>
						<div class="panel-body">
		                   <div class="table-responsive">
		                   		<table class="table table-striped table-bordered table-hover">
			 	
								 	<tr class="odd gradeX">
										<td>编号:</td>
										<td><s:property value="student.P_ID"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>姓名:</td>
										<td><s:property value="student.name"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>性别:</td>
										<td><s:property value="student.sex"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>生日:</td>
										<td><s:property value="student.birthday"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>学校:</td>
										<td><s:property value="student.school"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>年级:</td>
										<td><s:property value="student.level"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>班级:</td>
										<td><s:property value="student.now_class"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>父母姓名:</td>
										<td><s:property value="student.parent_name"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>父亲电话:</td>
										<td><s:property value="student.phone1"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>母亲电话:</td>
										<td><s:property value="student.phone2"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>家庭住址:</td>
										<td><s:property value="student.address"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>是否分配:</td>
										<td><s:property value="student.status"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>班主任:</td>
										<td><s:property value="student.stuff"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>总课时:</td>
										<td><s:property value="student.all_hour"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>剩余课时:</td>
										<td><s:property value="student.last_hour"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>赠送课时:</td>
										<td><s:property value="student.present_hour"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>用户名:</td>
										<td><s:property value="student.username"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>校区:</td>
										<td><s:property value="student.campus"/></td>
									</tr>
									
								</table>	
		                   </div>
			            </div>
			        	
			        </div>
       				<!-- /Advanced Tables -->
                </div>
        </div>
		
			
	</div>
</body>
</html>