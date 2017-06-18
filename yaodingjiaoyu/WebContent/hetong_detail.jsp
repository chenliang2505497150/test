<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
 <title>合同详情</title>
<!-- Bootstrap Styles-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FontAwesome Styles-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
     <!-- Morris Chart Styles-->
   
        <!-- Custom Styles-->
    <link href="assets/css/custom-styles.css" rel="stylesheet" />
     <!-- Google Fonts-->
   
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
			                             合同详情
						</div>
						<div class="panel-body">
		                   <div class="table-responsive">
		                   		<table class="table table-striped table-bordered table-hover">
			 	
								 	<tr class="odd gradeX">
										<td>编号:</td>
										<td><s:property value="hetong.P_ID"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>学生姓名:</td>
										<td><s:property value="hetong.student"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>合同编号:</td>
										<td><s:property value="hetong.hetong_num"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>年级:</td>
										<td><s:property value="hetong.level"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>科目:</td>
										<td><s:property value="hetong.subject"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>常规课时:</td>
										<td><s:property value="hetong.normal_hour"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>课时单价:</td>
										<td><s:property value="hetong.unit_price"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>合同类型:</td>
										<td><s:property value="hetong.hetong_type"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>合同属性:</td>
										<td><s:property value="hetong.ht_property"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>课程类型:</td>
										<td><s:property value="hetong.course_type"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>POS:</td>
										<td><s:property value="hetong.pos"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>现金:</td>
										<td><s:property value="hetong.cash"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>POS单号:</td>
										<td><s:property value="hetong.pos_num"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>收据单号:</td>
										<td><s:property value="hetong.receipt_num"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>签约时间:</td>
										<td><s:property value="hetong.sign_time"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>签约人:</td>
										<td><s:property value="hetong.stuff"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>校区:</td>
										<td><s:property value="hetong.campus"/></td>
									</tr>
									<tr class="odd gradeX">
										<td>是否审核:</td>
										<td><s:property value="hetong.status"/></td>
									</tr>
									<tr class="odd gradeX">
										<td>备注:</td>
										<td><s:property value="hetong.remarks"/></td>
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