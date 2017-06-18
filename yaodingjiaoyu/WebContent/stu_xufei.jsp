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
			
			
			
			$("select[name=course_type]").change(function(){
				//发送Ajax
				GetPrice();
			});
			
			
			function GetPrice(){
				$.ajax({
	                type: "post",
	                url: "cr_get_price",
	                async: false,
	                data: {
	                	id: $("input[name=id]").val(),
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
			
			//表单的提交
			$("a[name=submit_cr_add_hetong]").click(function(){
				AddContent();//添加例子
			});
			
			//提交表单的Ajax封装
			function AddContent(){
				// 发送Ajax请求
		        $.ajax({
		                type: "post",
		                url: "cr_add_hetong_ajax",
		                async: false,
		                data: { 
		                	id: $("input[name=id]").val(),
		                	hetong_num: $("input[name=hetong_num]").val(),
		                	subject: $("select[name=subject]").val(),
		                	course_type: $("select[name=course_type]").val(),
		                	normal_hour: $("input[name=normal_hour]").val(),
		                	ht_property: $("select[name=ht_property]").val(),
		                	pos: $("input[name=pos]").val(),
		                	cash: $("input[name=cash]").val(),
		                	pos_num: $("input[name=pos_num]").val(),
		                	receipt_num: $("input[name=receipt_num]").val(),
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

	<div id="page-inner" style="background:#349Be4;"> 
	
		<div class="row">
                <div class="col-md-12">
                	<!-- Advanced Tables -->
			        <div class="panel panel-default">
			        
			        	<div class="panel-heading">
			                             添加续费合同
						</div>
						<div class="panel-body">
		                   <div class="table-responsive">
		                   		<table class="table table-striped table-bordered table-hover">
			 	
								 	<tr class="odd gradeX">
										<td>学生编号:</td> 
										<td><input type="text" class="form-control" placeholder="Text input" name = "id" disabled="disabled" value="${id}" style="width:200px;text-align:center;color:#298bf7;"></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>合同编号:</td>
										<td><input type="text" style="width:200px;text-align:center;color:#298bf7;" class="form-control" placeholder="Search" name = "hetong_num"></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>科目:</td>
										<td><s:select list="subject_list" listKey="PId" listValue="name"  class="selectbox" name = "subject" style="width:200px;text-align:center;" theme="simple"/></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>课程类型:</td>
										<td><s:select list="course_type_list" listKey="PId" listValue="name" class="selectbox" name = "course_type" style="width:200px;text-align:center;" theme="simple"/></td>
									</tr>
										
									<tr class="odd gradeX">
										<td>常规课时:</td>
										<td><input type="text" style="width:200px;text-align:center;" class="form-control" placeholder="Search" name = "normal_hour"></td>
									</tr>	
									
									<tr class="odd gradeX">
										<td>合同属性:</td>
										<td><s:select list="ht_property_list" listKey="PId" listValue="name" class="selectbox" name = "ht_property" style="width:200px;text-align:center;" theme="simple"/></td>
									</tr>	
									
									<tr class="odd gradeX">
										<td>课时单价:</td>
										<td><input type="text" style="width:200px;text-align:center;color:#298bf7;" class="form-control" disabled="disabled" placeholder="Search" name = "unit_price" value = "${unit_price}"></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>POS:</td>
										<td><input type="text" style="width:200px;text-align:center;" class="form-control" placeholder="Search" name = "pos"></td>
									</tr>
										
									<tr class="odd gradeX">
										<td>现金:</td>
										<td><input type="text" style="width:200px;text-align:center;" class="form-control" placeholder="Search" name = "cash"></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>POS编号:</td>
										<td><input type="text" style="width:200px;text-align:center;" class="form-control" placeholder="Search" name = "pos_num"></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>收据单号:</td>
										<td><input type="text" style="width:200px;text-align:center;" class="form-control" placeholder="Search" name = "receipt_num"></td>
									</tr>
									
									<tr class="odd gradeX">
										<td>备注:</td>
										<td><textarea class="form-control" rows="3" name="remarks"></textarea></td>
									</tr>
									
									<tr class="odd gradeX">
										<td colspan="2" align="center">
											<a name = "submit_cr_add_hetong" href="javascript:void(0);" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
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
			
	</div>
</body>
</html>