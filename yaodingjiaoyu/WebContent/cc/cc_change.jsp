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
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <link href="assets/css/custom-styles.css" rel="stylesheet" />
    <link href="assets/css/select2.min.css" rel="stylesheet" >
    <link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    
    <script src="assets/js/jquery-3.0.0.min.js"></script>
    <script src="assets/js/select2.full.min.js"></script>   
    

    <script type="text/javascript">
	    $(document).ready(function() {
			$(".selectbox").select2();
			
			
			//表单的提交
			$("a[name=submit_cc_change]").click(function(){
				submitExample();//修改例子
			});
			
			//提交表单的Ajax封装
			function submitExample(){
				// 发送Ajax请求
		        $.ajax({
		                type: "post",
		                url: "cc_change_example",
		                async: false,
		                data: { 
		                	id: $("input[name=id]").val(),
		                	name: $("input[name=name]").val(),
		                	school: $("input[name=school]").val(),
		                	level: $("select[name=level]").val(),
		                	now_class: $("input[name=now_class]").val(),
		                	address: $("input[name=address]").val(),
		                	youxiao: $("select[name=youxiao]").val(),
		                	zhuangtai: $("select[name=zhuangtai]").val(),
		                	probability: $("select[name=probability]").val(),
		                	channel: $("select[name=channel]").val(),
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
			                             修改例子信息
						</div>
						<div class="panel-body">
		                   <div class="table-responsive">
		                   		<table class="table table-striped table-bordered table-hover">
			 	
								 	<tr class="odd gradeX">
										<td>编号:</td> 
										<td><input type = "text" name = "id" disabled="disabled" value="${examples.PId}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>姓名:</td>
										<td><input type = "text" name = "name" value="${examples.name}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>学校:</td>
										<td><input type = "text" name = "school" value="${examples.school}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>年级:</td>
										<td><s:select list="level_list" listKey="PId" listValue="name" value = "examples.level.PId" class="selectbox" name = "level"  style="width:153px" theme="simple"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>班级:</td>
										<td><input type = "text" name = "now_class" value="${examples.nowClass}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>父亲电话:</td>
										<td><input type = "text" name = "phone1" disabled="disabled" value="${examples.phone1}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>母亲电话:</td>
										<td><input type = "text" name = "phone2" disabled="disabled" value="${examples.phone2}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>添加人:</td>
										<td><input type = "text" name = "address" value="${examples.address}"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>是否有效:</td>
										<td><s:select list="#{'0':'无效','1':'有效'}" value = "examples.youxiao" class="selectbox" name = "youxiao"  style="width:153px" theme="simple"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>状态:</td>
										<td><s:select list="#{'0':'未上门','1':'已上门'}" value = "examples.zhuangtai" class="selectbox" name = "zhuangtai"  style="width:153px" theme="simple"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>可能性:</td>
										<td><s:select list="probability_list" listKey="PId" listValue="name" value = "examples.probability.PId" class="selectbox" name = "probability"  style="width:153px" theme="simple"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>来源:</td>
										<td><s:select list="channel_list" listKey="PId" listValue="name" value = "examples.channel.PId" class="selectbox" name = "channel"  style="width:153px" theme="simple"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td colspan="2" align="center">
											<a name = "submit_cc_change" href="javascript:void(0);" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
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