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
<script type="text/javascript">
	$(document).ready(function(){
		$(".selectbox").select2();//重新加载select的样式
		
		//表单的重置按钮
		$("a[name=reset_upload_examples]").click(function(){
			$("form[name=upload_examples]")[0].reset();
			$(".selectbox").select2();//刷新select样式
		});

		//设置父亲电话只能输入数字
		$("input[name='phone1']").keyup(function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).bind("paste",function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).css("ime-mode", "disabled");

		//设置母亲电话只能输入数字
		$("input[name='phone2']").keyup(function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).bind("paste",function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).css("ime-mode", "disabled");

		//设置班级只能输入数字
		$("input[name='now_class']").keyup(function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).bind("paste",function(){       
	        var tmptxt=$(this).val();       
	        $(this).val(tmptxt.replace(/\D|^0/g,''));       
	    }).css("ime-mode", "disabled");
	    
		//表单的提交
		$("a[name=submit_upload_examples]").click(function(){
			//首先进行表单完整性判断
			if($("input[name=name]").val() == "") {
				alert("请输入姓名");
				return ;
			}

			if($("input[name=school]").val() == "") {
				alert("请输入学校");
				return ;
			}

			if($("input[name=now_class]").val() == "") {
				alert("请输入班级");
				return ;
			}

			if($("input[name=phone1]").val() == "") {
				alert("请输入父亲电话");
				return ;
			}

			if($("input[name=phone2]").val() == "") {
				alert("请输入母亲电话");
				return ;
			}
			submitExamples();//上传例子
		});
		
		//提交表单的Ajax封装
		function submitExamples(){
			// 发送Ajax请求
	        $.ajax({
	                type: "post",
	                url: "sd_save_examples",
	                async: false,
	                data: { 
	                	name: $("input[name=name]").val(),
	                	school: $("input[name=school]").val(),
	                	level: $("select[name=level]").val(),
	                	now_class: $("input[name=now_class]").val(),
	                	phone1: $("input[name=phone1]").val(),
	                	phone2: $("input[name=phone2]").val(),
	                	address: $("input[name=address]").val(),
	                	zhuangtai: $("select[name=zhuangtai]").val(),
	                	stuff: $("select[name=stuff]").val(),
	                	probability: $("select[name=probability]").val(),
	                	channel: $("select[name=channel]").val(),
	                },
	                success: function (data, status) {
	                	
	                		var result_obj = $.parseJSON(data)[0];
	                		if(result_obj.status == 200){
	                			alert("添加例子成功!");
	                		}
	                		else{
	                			alert("添加例子失败!");
	                		}
	                },
	                error: function () { alert("添加例子失败!"); }
	                
	            });
		}
		
	});
		
	
</script>

</head>
<body>
	<div id="page-inner"> 
		
		<form name = "upload_examples" >
			<table class="table table-striped table-bordered table-hover">
			
			<caption align="top">请填写下面的表格完成上传</caption>
			 
				<tr class="odd gradeX">
					<td>姓名:</td>
					<td><input type = "text" name = "name"/></td>
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
					<td>爸爸电话:</td>
					<td><input type = "text" name = "phone1"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>妈妈电话:</td>
					<td><input type = "text" name = "phone2"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>添加人:</td>
					<td><input type = "text" name = "address"/></td>
				</tr>
				<tr class="odd gradeX">
					<td>状态:</td>
					<td>
						<s:select list="#{'0':'未上门','1':'已上门'}" class="selectbox" name = "zhuangtai"  style="width:153px" theme="simple"/>
					</td>
				</tr>
				<tr class="odd gradeX">
					<td>可能性:</td>
					<td>
						<s:select list="probability_list" listKey="PId" listValue="name" class="selectbox" name = "probability"  style="width:153px" theme="simple"/>
					</td>
				</tr>
				<tr class="odd gradeX">
					<td>来源:</td>
					<td>
						<s:select list="channel_list" listKey="PId" listValue="name" class="selectbox" name = "channel"  style="width:153px" theme="simple"/>
					</td>
				</tr>
				<tr class="odd gradeX">
					<td>销售:</td>
					<td>
						<s:select list="stuff_list" listKey="PId" listValue="name" class="selectbox" name = "stuff"  style="width:153px" theme="simple"/>
					</td>
				</tr>
				<tr class="odd gradeX">
					<td colspan="2" align="center">
						<a name = "reset_upload_examples" href="javascript:void(0);" class="btn btn-warning">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重置&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
						<a name = "submit_upload_examples" href="javascript:void(0);" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</td>
				</tr>
			</table>
		</form> 
		

	</div>
</body>
</html>