<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
 <title>修改学生信息</title>
<!-- Bootstrap Styles-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <link href="assets/css/custom-styles.css" rel="stylesheet" />
    <link href="assets/css/select2.min.css" rel="stylesheet" >
    <link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    
    <script src="assets/js/jquery-3.0.0.min.js"></script>
    <script src="assets/js/select2.full.min.js"></script>   
    
<!-- 日期选择插件 -->
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js" ></script>
    <script type="text/javascript">
	    $(document).ready(function() {
			$(".selectbox").select2();
			
			
			//表单的提交
			$("a[name=submit_admin_change]").click(function(){
				submitExample();//修改例子
			});
			
			//提交表单的Ajax封装
			function submitExample(){
				// 发送Ajax请求
		        $.ajax({
		                type: "post",
		                url: "admin_change_student_ajax",
		                async: false,
		                data: { 
		                	id: $("input[name=id]").val(),
		                	name: $("input[name=name]").val(),
		                	sex: $("input[name=sex]").val(),
		                	birthday: $("input[name=birthday]").val(),
		                	school: $("input[name=school]").val(),
		                	level: $("select[name=level]").val(),
		                	now_class: $("input[name=now_class]").val(),
		                	phone1: $("input[name=phone1]").val(),
		                	phone2: $("input[name=phone2]").val(),
		                	address: $("input[name=address]").val(),
		                	parent_name: $("input[name=parent_name]").val(),
		                	campus: $("select[name=campus]").val(),
		                },
		                success: function (data, status) {
		                	
		                		var result_obj = $.parseJSON(data)[0];
		                		alert(result_obj.message);
		                },
		                error: function () { alert("修改信息失败!"); }
		                
		            });
			}
			
			
			
		});
	</script>
</head>
<body>

	<div id="page-inner" style="background:#349Be4;"> 
	
		<div class="row">
                <div class="col-md-12">
                	<!-- Advanced Tables -->
			        <div class="panel panel-default">
			        
			        	<div class="panel-heading">
			                             修改学生信息
						</div>
						<div class="panel-body">
		                   <div class="table-responsive">
		                   		<table class="table table-striped table-bordered table-hover">
			 	
								 	<tr class="odd gradeX">
										<td>编号:</td> 
										<td><input type = "text" class="form-control" placeholder="Search" style="width:153px" name = "id" disabled="disabled" value="${student.PId}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>姓名:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "name" style="width:153px" value="${student.name}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>性别:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "sex" style="width:153px" value="${student.sex}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>生日:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "birthday" style="width:153px" onClick="WdatePicker()" value="${student.birthday}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>学校:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "school" style="width:153px" value="${student.school}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>年级:</td>
										<td><s:select list="level_list" listKey="PId" listValue="name" value = "student.level.PId" class="selectbox" name = "level"  style="width:153px" theme="simple"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>班级:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "now_class" style="width:153px" value="${student.nowClass}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>父亲电话:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "phone1" style="width:153px"  value="${student.phone1}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>母亲电话:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "phone2" style="width:153px"  value="${student.phone2}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>家庭住址:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "address" style="width:153px" value="${student.address}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>父母姓名:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "parent_name" style="width:153px" value="${student.parentName}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>校区:</td>
										<td><s:select list="campus_list" listKey="PId" listValue="name" value = "student.campus.PId" class="selectbox" name = "campus"  style="width:153px" theme="simple"/></td>
									</tr>							
									
									<tr class="odd gradeX">
										<td colspan="2" align="center">
											<a name = "submit_admin_change" href="javascript:void(0);" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
										</td>
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