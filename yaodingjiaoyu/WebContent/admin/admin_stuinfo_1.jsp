<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta content="" name="description" />
    <meta content="webthemez" name="author" />
    <title></title>
    <link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    <link href="assets/css/select2.min.css" rel="stylesheet" >
    
 <!-- DATA TABLE SCRIPTS -->
<script src="assets/js/select2.full.min.js"></script>


<script type="text/javascript">
$(document).ready(function() {
	
Bind();
	
	function Bind(){
		$("select[name=operate]").each(function(){
			$(this).change(function(){
				
				if($(this).val()!="no"){
					//跳转
					var ex_Id= $(this).prop("id");
					window.open($(this).val()+"?id="+ex_Id);
				}
				
		    })
			
		});
	}
	
	
	function LoadData(result_obj){
		//清空表格数据
		 $("#studentList").empty();
		//判断学生列表是否为空
		if(result_obj.studentList != null){
			var firstItem = parseInt(result_obj.firstItem);
			for(var i=0;i<result_obj.studentList.length;i++){
				var currentItem = firstItem+i;
				
				
				//更新表格数据
				var tr_start = "<tr class='odd gradeX'>";
				var td1 = "<td>"+currentItem+"</td>";
				var td2 = "<td>"+result_obj.studentList[i].name+"</td>";
				var td3 = "<td>"+result_obj.studentList[i].school+"</td>";
				var td4 = "<td class = 'center'>"+result_obj.studentList[i].level+"</td>";
				var td5 = "<td class = 'center'>"+result_obj.studentList[i].keshi+"</td>";
				var td6 = "<td class = 'center'>"+result_obj.studentList[i].phone1+"</td>";
				var td7 = "<td class = 'center'>"+result_obj.studentList[i].phone2+"</td>";
				var tdcampus = "<td class = 'center'>"+result_obj.studentList[i].campus+"</td>";
				var td8_start = "<td class = 'center'>";
				var td8_end = "</td>";
				var div_start = "<div>";
				var div_end = "</div>";
				var select_start = "<select class='selectbox' name='operate' id ='"+result_obj.studentList[i].P_ID+"'  style='width:100px' >";
				var option = "<option value='no'>－－－－</option><option value='admin_change_student'>修改</option><option value='student_detail'>详情</option>";
				var select_end = "</select>";
				var td8 = td8_start + div_start + select_start + option + select_end + div_end + td8_end;
				var tr_end = "</tr>";
	            
				var html = tr_start + td1 + td2 + td3 + td4 + td5 + td6 + td7+tdcampus +td8+ tr_end;
	                
				
				$("#studentList").append(html);
				
			}
			$(".selectbox").select2();//重新加载select的样式
			Bind();
		}
		
		
	}
	
	//加载当前第几页，以及第一个元素序号，最后一个元素序号
	function LoadCurrentPageInfo(result){
		var content = "当前  "+result.firstItem+"   到   "+result.lastItem+"   共   "+result.allItem+"   条记录";
		$('#Item').html(content);
		$('#all_page').html(result.all_page);
	}
	
	//重新加载分页框
	function LoadPagation(result){
		$("select[name=start_look]").empty();//先清空
		for(var i = 1;i<=result.all_page;i++){
			//添加select内元素
			var option = "<option value="+i+">"+i+"</option>";
			$("select[name=start_look]").append(option);
		}
		//$(".selectbox").select2();
	}
	
	$(".selectbox").select2();
	
	//上一页按钮点击事件
	$("#prePage").click(function(){
		if($('select[name=start_look] option:selected').val()==$('select[name=start_look] option:first').val()){
			alert("已经是第一页了!");
		}else{
			//将选中的页码调整到上一页
			$('select[name=start_look] option:selected').prev().prop("selected", 'selected');
			$(".selectbox").select2();
			
			// 发送Ajax请求
			getPageAjax();
		}
	});
	
	//下一页按钮点击事件
	$("#nextPage").click(function(){
		var t = $("select[name=start_look]").find("option:selected").text();
		if($('select[name=start_look] option:selected').val()==$('select[name=start_look] option:last').val()){
			alert("已经是最后一页了!");
		}else{
			//将选中的页码调整到下一页
			$('select[name=start_look] option:selected').next().prop("selected", 'selected');
			$(".selectbox").select2();
			
			// 发送Ajax请求
	       getPageAjax();
		}
	});
	//改变页面的Ajax封装
	function getPageAjax(){
		// 发送Ajax请求
        $.ajax({
                type: "post",
                url: "admin_search_student",
                async: false,
                data: { 
                	name: $("input[name=name]").val(),
                	school: $("input[name=school]").val(),
                	level: $("select[name=level]").val(),
                	campus: $("select[name=campus]").val(),
                	stu_class: $("input[name=stu_class]").val(),
                	telephone: $("input[name=telephone]").val(),
                	stu_status: $("select[name=stu_status]").val(),
                	start_look: $("select[name=start_look]").find("option:selected").val(),
                	max_look: 15,
                },
                success: function (data, status) {
                	
                		var result_obj = $.parseJSON(data)[0];
                		LoadData(result_obj);
                		LoadCurrentPageInfo(result_obj);   
                },
                error: function () { alert("获取信息失败") }
                
            });
	}
	
	//select选择发生改变事件
	$("select[name=start_look]").change(function(){
		//发送Ajax请求
		getPageAjax();
	});
	
	//给提交按钮写JS事件
	$("form[name=seachstu]").submit(function(){
		//获取筛选框中变量，并适时将页面置为1
		// 发送Ajax请求
        $.ajax({
                type: "post",
                url: "admin_search_student",
                async: false,
                data: { 
                	name: $("input[name=name]").val(),
                	school: $("input[name=school]").val(),
                	level: $("select[name=level]").val(),
                	campus: $("select[name=campus]").val(),
                	stu_class: $("input[name=stu_class]").val(),
                	telephone: $("input[name=telephone]").val(),
                	stu_status: $("select[name=stu_status]").val(),
                	start_look: 1,
                	max_look: 15,
                },
                success: function (data, status) {
                	
                		var result_obj = $.parseJSON(data)[0];
                		LoadData(result_obj);
                		LoadCurrentPageInfo(result_obj);//加载当前显示的页数以及栏目的序号
                		LoadPagation(result_obj);
                		      
                },
                error: function () { alert("获取信息失败") }
                
            });
		return false;
	});
});
</script>
</head>
<body>
    

		
            <div id="page-inner"> 
               
	            <div class="row">
	                <div class="col-md-12">
	                    <!-- Advanced Tables -->
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                             学员信息
	                             <nav class="navbar navbar-default">
									<div class="container-fluid">
									    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
									      <form class="navbar-form navbar-left" role="search" name = "seachstu">
									        <div class="form-group">
									        	姓名:<input type="text" class="form-control" placeholder="Search" name = "name">
									        	学校:<input type="text" class="form-control" placeholder="Search" name = "school">
								            	年级:<s:select list="level_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "level" id = "nianji" style="width:100px" theme="simple"/>
								            	<p>       
									            电话:<input type="text" class="form-control" placeholder="Search" name = "telephone">
									            班级:<input type="text" class="form-control" placeholder="Search" name = "stu_class">
									            学员状态:<s:select list="#{'no':'－－－－','0':'已结课','1':'未结课'}" class="selectbox" name = "stu_status" id = "stu_status" style="width:100px"/>
								            	<p>
								            	校区:<s:select list="campus_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "campus" id = "campus" style="width:100px" theme="simple"/>
								            	<button type="submit" class="btn btn-default">提交</button>    
									        </div>
									        
									      </form>
									    </div><!-- /.navbar-collapse -->
									</div>
								</nav>
	                        </div>
	                        <div class="panel-body">
	                            <div class="table-responsive">
	                            
		                            <div class="col-sm-6">
										
									</div>
	                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
	                                    <thead>
	                                        <tr>
	                                            <th>序号</th>
	                                            <th>姓名</th>
	                                            <th>学校</th>
	                                            <th>年级</th>
	                                            <th>(总/剩余)课时</th>
	                                            <th>父亲电话</th>
	                                            <th>母亲电话</th>
	                                            <th>校区</th>
	                          					<th>更多</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody id = "studentList">
	                                    <s:iterator value="studentList" id = "student" status = "sta">
	                                    	<s:set name="id" value="#student.PId"/>  <!-- 定义存储ID的变量 -->
	                                    	<s:set name="num" value="item.firstItem"/>
	                                    	<tr class="odd gradeX">
	                                            <td><s:property value="#num+#sta.count-1"/></td>
	                                            <td><s:property value="#student.name"/></td>
	                                            <td><s:property value="#student.school"/></td>
	                                            <td class="center"><s:property value="#student.level.name"/></td>
	                                            <td class="center"><s:property value="#student.allHour"/>(<s:property value="#student.lastHour"/>)</td>
	                                            <td class="center"><s:property value="#student.phone1"/></td>
	                                            <td class="center"><s:property value="#student.phone2"/></td>
	                                            <td class="center"><s:property value="#student.campus.name"/></td>
	                                            <td class="center">
		                                            <div>
		                                            		<select class="selectbox" name="operate" id ="${id}"  style="width:100px" >  
					                                        		<option value="no">－－－－</option>
					                                                <option value="admin_change_student">修改</option>
					                                                <option value="student_detail">详情</option>
					                                        </select>
		                                            </div>
	                                    		</td>
	                                        </tr>
	                                    </s:iterator> 
	                                    </tbody>
	                                </table>
	                                <div class="row">
										<div class="col-sm-6">
											<div class="dataTables_info" id="dataTables-example_info" role="alert" aria-live="polite" aria-relevant="all">
												<span id = "Item">当前 <s:property value = "item.firstItem"/> 到 <s:property value = "item.lastItem"/> 共 <s:property value = "item.allItem"/> 条记录</span>
												<span class="label label-default">总页数:<span class="label label-default" id = "all_page"><s:property value = "item.all_page" /></span></span>
											</div>
										</div>
											<div class="col-sm-6">
												<!-- 在此处添加分页插件 -->
													<button  class="btn btn-default" id = "prePage">上一页</button> 
													<select class="selectbox" name="start_look"  style="width:100px" >
														<s:iterator  status="st" begin="1" end="item.all_page" step="1"> 
															<s:set name="id" value="#st.count"/>
															<option value="${id}"><s:property value = "#st.count" /></option>
														</s:iterator>  
		                                       		</select>
													<button  class="btn btn-default" id = "nextPage">下一页</button>
											
												 
											</div>
									</div>
	                                
	                            </div>
	                            
	                        </div>
	                    </div>
	                    <!--End Advanced Tables -->
	                </div>
	            </div>     
       		 </div>
                
</body>
</html>
