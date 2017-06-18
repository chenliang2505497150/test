<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
 <title>查看员工信息</title>
<!-- Bootstrap Styles-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <link href="assets/css/custom-styles.css" rel="stylesheet" />
    <link href="assets/css/select2.min.css" rel="stylesheet" >
    <link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    
    <script src="assets/js/jquery-3.0.0.min.js"></script>
    <script src="assets/js/select2.full.min.js"></script>   
    
    <script type="text/javascript">
	    $(document).ready(function() {
			$(".selectbox").select2();

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
			
			//表单的提交
			$("a[name=sd_change_stuffInfo]").click(function(){
				submitChange();//修改信息
			});
			
			//提交表单的Ajax封装
			function submitChange(){
				// 发送Ajax请求
		        $.ajax({
		                type: "post",
		                url: "sd_change_stuff",
		                async: false,
		                data: { 
		                	id: $("input[name=id]").val(),
		                	name: $("input[name=name]").val(),
		                	sex: $("input[name=sex]").val(),
		                	id_Card: $("input[name=id_card]").val(),
		                	phone: $("input[name=phone]").val(),
		                	address: $("textarea[name=address]").val(),
		                	jingji_phone: $("input[name=jingji_phone]").val(),
		                	job: $("select[name=job]").val(),
		                	username: $("input[name=username]").val(),
		                	status: $("select[name=status]").val(),
		                	part_time: $("select[name=part_time]").val(),
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
			                             员工详细信息
						</div>
						<div class="panel-body">
		                   <div class="table-responsive">
		                   		<table class="table table-striped table-bordered table-hover">
			 	
								 	<tr class="odd gradeX">
										<td>编号:</td> 
										<td><input type = "text" class="form-control" placeholder="Search" style="width:200px" name = "id" disabled="disabled" value="${stuff.PId}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>姓名:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "name" style="width:200px" value="${stuff.name}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>性别:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "sex" style="width:200px" value="${stuff.sex}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>身份证号:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "id_card" style="width:200px" value="${stuff.idCard}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>电话:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "phone" style="width:200px" value="${stuff.phone}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>紧急电话:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "jingji_phone" style="width:200px" value="${stuff.jingjiPhone}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>岗位:</td>
										<td><s:select list="job_list" listKey="PId" listValue="name" value = "stuff.job.PId" class="selectbox" name = "job"  style="width:200px" theme="simple"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>用户名:</td>
										<td><input type = "text" class="form-control" placeholder="Search" name = "username" style="width:200px" value="${stuff.username}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>账户状态:</td>
										<td><s:select list="#{'0':'正常','1':'冻结'}" value = "stuff.status" class="selectbox" name = "status"  style="width:200px" theme="simple"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>是否兼职:</td>
										<td><s:select list="#{'0':'常规','1':'兼职'}" value = "stuff.partTime" class="selectbox" name = "part_time" style="width:200px" theme="simple"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>家庭住址:</td>
										<td><textarea class="form-control" rows="3" name="address">${stuff.address}</textarea></td>
									</tr>
																
									<tr class="odd gradeX">
										<td colspan="2" align="center">
											<a name = "sd_change_stuffInfo" href="javascript:void(0);" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
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