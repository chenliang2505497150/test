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
		
		
		$("select[name=level]").change(function(){
			//发送Ajax
			GetPrice();
		});
		$("select[name=course_type]").change(function(){
			//发送Ajax
			GetPrice();
		});
		//设置课时框只能输入数字
		$("input[name='normal_hour']").keyup(function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).bind("paste",function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).css("ime-mode", "disabled");      
		
		function GetPrice(){
			$.ajax({
                type: "post",
                url: "unitPrice",
                async: false,
                data: {
                	level: $("select[name=level]").val(),
                	course_type: $("select[name=course_type]").val(),
                },
                success: function (data, status) {//再此判断数据是否非空
                	
                		var result_obj = $.parseJSON(data)[0];
                		if(result_obj.status==200){
                			//更新单价
                			 $("input[name=unit_price]").prop("value",result_obj.price);
                		}
                		else{
                			alert("获取价格信息失败");
                		}
                },
                error: function () { 
	                	alert("获取价格信息失败");
                	}
                
            });
		}
		//表单的重置按钮
		$("a[name=reset_sd_add_hetong]").click(function(){
			$("form[name=sd_add_hetong]")[0].reset();
			$(".selectbox").select2();//刷新select样式
		});
		
		
		//表单的提交
		$("a[name=submit_sd_add_hetong]").click(function(){
			submitExamples();//上传例子
		});
		
		//提交表单的Ajax封装
		function submitExamples(){
			// 发送Ajax请求
	        $.ajax({
	                type: "post",
	                url: "sd_add_hetong",
	                async: false,
	                data: { 
	                	name: $("input[name=name]").val(),
	                	sex: $("select[name=sex]").val(),
	                	birthday: $("input[name=birthday]").val(),
	                	school: $("input[name=school]").val(),
	                	level: $("select[name=level]").val(),
	                	now_class: $("input[name=now_class]").val(),
	                	phone1: $("input[name=phone1]").val(),
	                	phone2: $("input[name=phone2]").val(),
	                	address: $("input[name=address]").val(),
	                	parent_name: $("input[name=parent_name]").val(),
	                	hetong_num: $("input[name=hetong_num]").val(),
	                	subject: $("select[name=subject]").val(),
	                	normal_hour: $("input[name=normal_hour]").val(),
	                	ht_property: $("select[name=ht_property]").val(),
	                	course_type: $("select[name=course_type]").val(),
	                	pos: $("input[name=pos]").val(),
	                	cash: $("input[name=cash]").val(),
	                	pos_num: $("input[name=pos_num]").val(),
	                	receipt_num: $("input[name=receipt_num]").val(),
	                	stuff: $("select[name=stuff]").val(),
	                	remarks: $("textarea[name=remarks]").val(),
	                },
	                success: function (data, status) {
	                	
	                		var result_obj = $.parseJSON(data)[0];
	                		alert(result_obj.message);
	                },
	                error: function () { alert("添加合同失败!"); }
	                
	            });
		}
		
	});
		
	
</script>

</head>
<body>
	<div id="page-inner"> 
		
		<form name = "sd_add_hetong" >
			<table class="table table-striped table-bordered table-hover">
			
			<caption align="top">添加新签合同</caption>
			 
				<tr class="odd gradeX">
					<td>姓名:</td>
					<td><input type = "text" name = "name"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>性别:</td>
					<td><s:select list="#{'男':'男','女':'女'}" class="selectbox" name = "sex"  style="width:153px" theme="simple"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>生日:</td>
					<td><input type = "text" name = "birthday" onClick="WdatePicker()" /></td>
				</tr>
				<tr class="odd gradeX">
					<td>学校:</td>
					<td><input type = "text" name = "school"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>年级:</td>
					<td>
						<s:select list="level_list" listKey="PId" listValue="name" class="selectbox" name = "level"  style="width:153px" theme="simple"/>
					</td>
				</tr>
				<tr class="odd gradeX">
					<td>班级:</td>
					<td><input type = "text" name = "now_class"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>父亲电话(学生账号、必填):</td>
					<td><input type = "text" name = "phone1"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>母亲电话(可不填):</td>
					<td><input type = "text" name = "phone2"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>父母姓名:</td>
					<td><input type = "text" name = "parent_name"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>家庭住址:</td>
					<td><input type = "text" name = "address"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>合同编号:</td>
					<td><input type = "text" name = "hetong_num"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>科目:</td>
					<td><s:select list="subject_list" listKey="PId" listValue="name" class="selectbox" name = "subject"  style="width:153px" theme="simple"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>课时:</td>
					<td><input type = "text" name = "normal_hour"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>课时单价:</td>
					<s:set name="unit_price" value="unit_price"/>  
					<td><input type = "text" name = "unit_price" disabled="disabled" value="${unit_price}"/></td>
				</tr>
				
				<tr class="odd gradeX">
					<td>合同属性:</td>
					<td>
						<s:select list="ht_property_list" listKey="PId" listValue="name" class="selectbox" name = "ht_property"  style="width:153px" theme="simple"/>
					</td>
				</tr>
				
				<tr class="odd gradeX">
					<td>课程类型:</td>
					<td>
						<s:select list="course_type_list" listKey="PId" listValue="name" class="selectbox" name = "course_type"  style="width:153px" theme="simple"/>
					</td>
				</tr>
				<tr class="odd gradeX">
					<td>pos:</td>
					<td><input type = "text" name = "pos"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>现金:</td>
					<td><input type = "text" name = "cash"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>pos单号:</td>
					<td><input type = "text" name = "pos_num"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>收据单号:</td>
					<td><input type = "text" name = "receipt_num"/></td>
				</tr>
				
				<tr class="odd gradeX">
					<td>签约人:</td>
					<td>
						<s:select list="stuff_list" listKey="PId" listValue="name" class="selectbox" name = "stuff"  style="width:153px" theme="simple"/>
					</td>
				</tr>
				
				<tr class="odd gradeX">
					<td>合同备注:</td>
					<td><textarea class="form-control" rows="3" name="remarks"></textarea></td>
				</tr>
				<tr class="odd gradeX">
					<td><a name = "reset_sd_add_hetong" href="javascript:void(0);" class="btn btn-warning">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重置&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
					<td><a name = "submit_sd_add_hetong" href="javascript:void(0);" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
				</tr>
			</table>
		</form> 
		

	</div>
</body>
</html>