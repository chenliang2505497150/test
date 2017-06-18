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
										<td><s:property value="examples.P_ID"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>姓名:</td>
										<td><s:property value="examples.name"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>学校:</td>
										<td><s:property value="examples.school"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>年级:</td>
										<td><s:property value="examples.level"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>班级:</td>
										<td><s:property value="examples.now_class"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>父亲电话:</td>
										<td><s:property value="examples.phone1"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>母亲电话:</td>
										<td><s:property value="examples.phone2"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>添加人:</td>
										<td><s:property value="examples.address"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>是否有效:</td>
										<td><s:property value="examples.youxiao"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>状态:</td>
										<td><s:property value="examples.zhuangtai"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>可能性:</td>
										<td><s:property value="examples.probability"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>来源:</td>
										<td><s:property value="examples.channel"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>是否分配:</td>
										<td><s:property value="examples.status"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>所属销售:</td>
										<td><s:property value="examples.stuff"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>校区:</td>
										<td><s:property value="examples.campus"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>创建时间:</td>
										<td><s:property value="examples.creat_time"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>最近跟踪时间:</td>
										<td><s:property value="examples.last_time"/></td>
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