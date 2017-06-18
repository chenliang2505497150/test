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
<link href="assets/css/select2.min.css" rel="stylesheet">
<link href="assets/js/dataTables/dataTables.bootstrap.css"
	rel="stylesheet" />

<script src="assets/js/jquery-3.0.0.min.js"></script>
<script src="assets/js/select2.full.min.js"></script>

<!-- 日期选择插件 -->
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".selectbox").select2();

		//表单的提交
		$("a[name=submit_stuInfo_change]").click(function() {
			submitChange();//修改例子
		});

		//提交表单的Ajax封装
		function submitChange() {
			// 发送Ajax请求
			$.ajax({
				type : "post",
				url : "change_stuffInfo",
				async : false,
				data : {
					sex : $("select[name=sex]").val(),
				},
				success : function(data, status) {

					var result_obj = $.parseJSON(data)[0];
					alert(result_obj.message);
				},
				error : function() {
					alert("修改信息失败!");
				}

			});
		}

	});
</script>
</head>
<body>

	<div id="page-inner" style="background: #EDEDED;">

		<div class="row">
			<div class="col-md-12">
				<!-- Advanced Tables -->
				<div class="panel panel-default">

					<div class="panel-heading">个人信息</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover">

								<tr class="odd gradeX">
									<td>编号:</td>
									<td><input type="text" class="form-control"
										placeholder="Search" style="width: 153px" name="id"
										disabled="disabled" value="${stuff.PId}" /></td>
								</tr>

								<tr class="odd gradeX">
									<td>姓名:</td>
									<td><input type="text" class="form-control"
										placeholder="Search" name="name" style="width: 153px"
										disabled="disabled" value="${stuff.name}" /></td>
								</tr>

								<tr class="odd gradeX">
									<td>性别:</td>
									<td><s:select list="#{'男':'男','女':'女'}" value="stuff.sex"
											class="selectbox" name="sex" style="width:153px"
											theme="simple" /></td>
								</tr>

								<tr class="odd gradeX">
									<td>身份证号:</td>
									<td><input type="text" class="form-control"
										placeholder="Search" name="idCard" disabled="disabled"
										style="width: 153px" value="${stuff.idCard}" /></td>
								</tr>

								<tr class="odd gradeX">
									<td>电话:</td>
									<td><input type="text" class="form-control"
										placeholder="Search" name="phone" style="width: 153px"
										disabled="disabled" value="${stuff.phone}" /></td>
								</tr>

								<tr class="odd gradeX">
									<td>紧急联系电话:</td>
									<td><input type="text" class="form-control"
										placeholder="Search" name="jingjiPhone" style="width: 153px"
										disabled="disabled" value="${stuff.jingjiPhone}" /></td>
								</tr>

								<tr class="odd gradeX">
									<td>家庭住址:</td>
									<td><input type="text" class="form-control"
										placeholder="Search" name="address" style="width: 153px"
										disabled="disabled" value="${stuff.address}" /></td>
								</tr>

								<tr class="odd gradeX">
									<td>用户名:</td>
									<td><input type="text" class="form-control"
										placeholder="Search" name="username" style="width: 153px"
										disabled="disabled" value="${stuff.username}" /></td>
								</tr>

								<tr class="odd gradeX">
									<td>校区:</td>
									<td><input type="text" class="form-control"
										placeholder="Search" name="campus" style="width: 153px"
										disabled="disabled" value="${stuff.campus.name}" /></td>
								</tr>

								<tr class="odd gradeX">
									<td colspan="2" align="center"><a
										name="submit_stuInfo_change" href="javascript:void(0);"
										class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
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