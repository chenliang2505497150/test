<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
<link href="assets/css/select2.min.css" rel="stylesheet" >
    
 <!-- DATA TABLE SCRIPTS -->
<script src="assets/js/select2.full.min.js"></script>
<script src="assets/js/ajaxfileupload.js"></script>
<!-- 日期选择插件 -->
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".selectbox").select2();//重新加载select的样式
		
		//设置电话只能输入数字
		$("input[name='phone']").keyup(function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).bind("paste",function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).css("ime-mode", "disabled");  


		//设置紧急电话只能输入数字
		$("input[name='jingji_phone']").keyup(function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).bind("paste",function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).css("ime-mode", "disabled");

		//设置用户名只能输入数字
		$("input[name='username']").keyup(function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).bind("paste",function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).css("ime-mode", "disabled");  
		
		
		//表单的重置按钮
		$("a[name=reset_create]").click(function(){
			$("form[name=admin_add_stuff]")[0].reset();
			$(".selectbox").select2();//刷新select样式
		});
		
		
		//表单的提交
		$("a[name=submit_create]").click(function(){
			//判断表单的完整性
			if($("input[name=name]").val() == "") {
				alert("请输入姓名!");
				return;
			}

			if($("input[name=username]").val() == "") {
				alert("请输入用户名!");
				return;
			}

			if($("input[name=password]").val() == "") {
				alert("请输入密码!");
				return;
			}
			submitStuff();//上传例子
		});
		
		//提交表单的Ajax封装
		function submitStuff(){
			// 发送Ajax请求
	        $.ajax({
	                type: "post",
	                url: "admin_add_stuff",
	                async: false,
	                data: { 
	                	name: $("input[name=name]").val(),
	                	sex: $("select[name=sex]").val(),
	                	ID_card: $("input[name=ID_card]").val(),
	                	phone: $("input[name=phone]").val(),
	                	job: $("select[name=job]").val(),
	                	jingji_phone: $("input[name=jingji_phone]").val(),
	                	username: $("input[name=username]").val(),
	                	password: $("input[name=password]").val(),
	                	campus: $("select[name=campus]").val(),
	                	part_time: $("select[name=part_time]").val(),
	                	address: $("textarea[name=address]").val(),
	                },
	                success: function (data, status) {
	                	
	                		var result_obj = $.parseJSON(data)[0];
	                		alert(result_obj.message);
	                },
	                error: function () { alert("添加账号失败!"); }
	                
	            });
		}
		
	});
		
	
</script>

</head>
<body>
	<div id="page-inner"> 
		
		<form name = "admin_add_stuff" >
			<table class="table table-striped table-bordered table-hover">
			
			<caption align="top">添加新的员工</caption>
			 
				<tr class="odd gradeX">
					<td>姓名:</td>
					<td><input type = "text" name = "name"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>性别:</td>
					<td><s:select list="#{'男':'男','女':'女'}" class="selectbox" name = "sex"  style="width:153px" theme="simple"/></td>
				</tr>
				
				<tr class="odd gradeX">
					<td>身份证号:</td>
					<td><input type = "text" name = "ID_card"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>联系电话:</td>
					<td><input type = "text" name = "phone"/></td>
				</tr>
				
				<tr class="odd gradeX">
					<td>紧急联系人电话:</td>
					<td><input type = "text" name = "jingji_phone"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>岗位:</td>
					<td><s:select list="job_list" listKey="PId" listValue="name" class="selectbox" name = "job"  style="width:153px" theme="simple"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>用户名:</td>
					<td><input type = "text" name = "username"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>密码:</td>
					<td><input type = "password" name = "password"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>校区:</td>
					<td><s:select list="campus_list" listKey="PId" listValue="name" class="selectbox" name = "campus"  style="width:153px" theme="simple"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>是否兼职:</td>
					<td><s:select list="#{'0':'否','1':'是'}" class="selectbox" name = "part_time"  style="width:153px" theme="simple"/></td>
				</tr>
				
				<tr class="odd gradeX">
					<td>家庭住址:</td>
					<td><textarea class="form-control" rows="3" name="address"></textarea></td>
				</tr>
				
				<tr class="odd gradeX">
					<td colspan="2" align="center">
						<a name = "reset_create" href="javascript:void(0);" class="btn btn-warning">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重置&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
						<a name = "submit_create" href="javascript:void(0);" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</td>
				</tr>
			</table>
		</form> 
		

	</div>
</body>
</html>