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
		                url: "admin_change_hetong_ajax",
		                async: false,
		                data: { 
		                	id: $("input[name=id]").val(),
		                	name: $("input[name=name]").val(),
		                	hetong_num: $("input[name=hetong_num]").val(),
		                	level: $("select[name=level]").val(),
		                	subject: $("select[name=subject]").val(),
		                	normal_hour: $("input[name=normal_hour]").val(),
		                	unit_price: $("input[name=unit_price]").val(),
		                	hetong_type: $("select[name=hetong_type]").val(),
		                	ht_property: $("select[name=ht_property]").val(),
		                	course_type: $("select[name=course_type]").val(),
		                	pos: $("input[name=pos]").val(),
		                	cash: $("input[name=cash]").val(),
		                	pos_num: $("input[name=pos_num]").val(),
		                	receipt_num: $("input[name=receipt_num]").val(),
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
			                             修改合同信息
						</div>
						<div class="panel-body">
		                   <div class="table-responsive">
		                   		<table class="table table-striped table-bordered table-hover">
			 	
								 	<tr class="odd gradeX">
										<td>编号:</td> 
										<td><input type = "text" class="form-control" placeholder="Search" style="width:153px" name = "id" disabled="disabled" value="${hetong.PId}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>姓名:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "name" style="width:153px" value="${hetong.student.name}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>合同编号:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "hetong_num" style="width:153px" value="${hetong.hetongNum}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>年级:</td>
										<td><s:select list="level_list" listKey="PId" listValue="name" value = "hetong.level.PId" class="selectbox" name = "level"  style="width:153px" theme="simple"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>科目:</td>
										<td><s:select list="subject_list" listKey="PId" listValue="name" value = "hetong.subject.PId" class="selectbox" name = "subject"  style="width:153px" theme="simple"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>常规课时:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "normal_hour" style="width:153px"  value="${hetong.normalHour}"/></td>
									</tr>
									
									
									<tr class="odd gradeX">
										<td>课时单价:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "unit_price" style="width:153px" value="${hetong.unitPrice}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>合同类型:</td>
										<td><s:select list="hetong_type_list" listKey="PId" listValue="name" value = "hetong.hetongType.PId" class="selectbox" name = "hetong_type"  style="width:153px" theme="simple"/></td>
									</tr>
									<tr class="odd gradeX">
										<td>合同属性:</td>
										<td><s:select list="ht_property_list" listKey="PId" listValue="name" value = "hetong.htProperty.PId" class="selectbox" name = "ht_property"  style="width:153px" theme="simple"/></td>
									</tr>
									<tr class="odd gradeX">
										<td>课程类型:</td>
										<td><s:select list="course_type_list" listKey="PId" listValue="name" value = "hetong.courseType.PId" class="selectbox" name = "course_type"  style="width:153px" theme="simple"/></td>
									</tr>
									<tr class="odd gradeX">
										<td>POS:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "pos" style="width:153px" value="${hetong.pos}"/></td>
									</tr>
									<tr class="odd gradeX">
										<td>现金:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "cash" style="width:153px" value="${hetong.cash}"/></td>
									</tr>
									<tr class="odd gradeX">
										<td>pos单号:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "pos_num" style="width:153px" value="${hetong.posNum}"/></td>
									</tr>
									<tr class="odd gradeX">
										<td>收据单号:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "receipt_num" style="width:153px" value="${hetong.receiptNum}"/></td>
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