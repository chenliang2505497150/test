<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta content="" name="description" />
    <meta content="webthemez" name="author" />
    
   
    <link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    <link href="assets/css/select2.min.css" rel="stylesheet" >
    <link href="assets/css/checkbox3.min.css" rel="stylesheet" >
    <link rel="stylesheet" type="text/css" href="Menu/css/styles.css">
 <!-- DATA TABLE SCRIPTS -->
<script src="assets/js/select2.full.min.js"></script>
<!-- 日期选择插件 -->
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js" ></script>


<script type="text/javascript">
$(document).ready(function() {
	//--------------------------- 右键菜单
	var menu = document.querySelector('.menu');
	function showMenu(x, y){
	    menu.style.left = x + 'px';
	    menu.style.top = y + 'px';
	    menu.classList.add('show-menu');
	}
	function hideMenu(){
	    menu.classList.remove('show-menu');
	}
	function onContextMenu(e){
	    e.preventDefault();
	    showMenu(e.pageX-260, e.pageY-65);
	    document.addEventListener('mousedown', onMouseDown, false);
	}
	function onMouseDown(e){
	    hideMenu();
	    document.removeEventListener('mousedown', onMouseDown);
	}

	document.addEventListener('contextmenu', onContextMenu, false);
	
	//---------------------------
	
	//---------------------------全选的选择框
	$("#checkbox-fa-light-0").click(function() {
		$("input[name=check_content]").each(function(){
			$(this).prop("checked",$("#checkbox-fa-light-0").prop("checked"));
		});
	});

	//---------------------------
	//用来绑定右键菜单的点击事件
	$("#confirm").bind('mousedown',function(){
		//这里上传选中的课程编号，进行处理
		var message = "您确定要执行<确认上课>操作吗?";
		var r=confirm(message);
		if (r==true) {
			doConfirmClass();
		}
	});

	function doConfirmClass() {
		//获取所有选中的checkbox，如果没有就弹一个框
		var classtable_list = new Array();
		$("input[name=check_content]").each(function(){
			
			if($(this).prop("checked")){
				classtable_list.push($(this).val());
			}
		});
		//判断选中是不是空
		if(classtable_list.length == 0){
			alert("不能空选");
		} else {
			var json = {};
			for(var i=0;i<classtable_list.length;i++)
			{
			    json[i]=classtable_list[i];
			}

			//发送ajax请求
			$.ajax({
                type: "post",
                url: "sd_confirm_classtable_list",
                async: false,
                data: { 
                	classtable_list_json: JSON.stringify(json),
                },
                success: function (data, status) {
                	
                		var result_obj = $.parseJSON(data)[0];
                		alert(result_obj.message);
                		//需要重新加载数据
                		$("a[name=submit]").click();
                },
                error: function () { alert("获取信息失败") }
                
            });
            //ajax请求发送结束
		}
	}

	Bind();
	
	function Bind(){
		$("select[name=operate]").each(function(){
			$(this).change(function(){
				
				if($(this).val()!="no"){
					//跳转
					var ex_Id= $(this).prop("id");
					var message = "您确定要执行<"+$(this).find("option:selected").text()+">操作吗?";
					var r=confirm(message);
					if (r==true)
				    {
				    	//发送Ajax请求
						$.ajax({
			                type: "post",
			                url: $(this).val(),
			                async: false,
			                data: {
			                	id: ex_Id,
			                },
			                success: function (data, status) {
			                	var result_obj = $.parseJSON(data)[0];
			                	//刷新数据  
			                	alert(result_obj.message);
			                	$("a[name=submit]").click();
			                },
			                error: function () { alert("获取信息失败") }
			                
			            });
				    }
				}
				
		    })
			
		});
	}
	
	
	
	
	
	
	function LoadData(result_obj){
		//清空表格数据
		 $("#ContentList").empty();
		//判断学生列表是否为空
		if(result_obj.Contennt_list != null){
			var firstItem = parseInt(result_obj.firstItem);
			for(var i=0;i<result_obj.Contennt_list.length;i++){
				var currentItem = firstItem+i;
				
				
				//更新表格数据
				var tr_start = "<tr class='odd gradeX'>";
				var td0 = "<td><div class='checkbox3 checkbox-success checkbox-inline checkbox-check checkbox-round  checkbox-light'><input type='checkbox' name = 'check_content' value ='"+result_obj.Contennt_list[i].P_ID+"' id='checkbox-fa-light-"+result_obj.Contennt_list[i].P_ID+"'><label for='checkbox-fa-light-"+result_obj.Contennt_list[i].P_ID+"'></label></div></td>";
				var td1 = "<td class = 'center'>"+currentItem+"</td>";
				var td2 = "<td class = 'center'>"+result_obj.Contennt_list[i].student+"</td>";
				var td3 = "<td class = 'center'>"+result_obj.Contennt_list[i].subject+"</td>";
				var td4 = "<td class = 'center'>"+result_obj.Contennt_list[i].stuff+"</td>";
				var td5 = "<td class = 'center'>"+result_obj.Contennt_list[i].day_time+"</td>";
				var td6 = "<td class = 'center'>"+result_obj.Contennt_list[i].class_time+"</td>";
				var td7 = "<td class = 'center'>"+result_obj.Contennt_list[i].status+"</td>";
				var td8 = "<td class = 'center'>"+result_obj.Contennt_list[i].course_type+"</td>";
				var td9 = "<td class = 'center'>"+result_obj.Contennt_list[i].level+"</td>";
				var tdmore_start = "<td class = 'center'>";
				var tdmore_end = "</td>";
				var div_start = "<div>";
				var div_end = "</div>";
				var select_start = "<select class='selectbox' name='operate' id ='"+result_obj.Contennt_list[i].P_ID+"'  style='width:100px' >";
				var option = "<option value='no'>－－－－</option><option value='cr_confirm_classtable'>确认已上</option><option value='admin_delete_classtable'>删除课程</option>";
				var select_end = "</select>";
				var tdmore = tdmore_start + div_start + select_start + option + select_end + div_end + tdmore_end;
				var tr_end = "</tr>";
	            
				var html = tr_start +td0 + td1 + td2 + td3 + td4 + td5 + td6 +td7+ td8 + td9 + tdmore+ tr_end;
	                
				
				$("#ContentList").append(html);
				
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
                url: "sd_search_classtable",
                async: false,
                data: {
                	cr: $("select[name=cr]").val(),
                	student: $("input[name=student]").val(),
                	teacher: $("input[name=teacher]").val(),
                	time1: $("input[name=time1]").val(),
                	time2: $("input[name=time2]").val(),
                	level: $("select[name=level]").val(),
                	subject: $("select[name=subject]").val(),
                	class_time: $("select[name=class_time]").val(),
                	course_type: $("select[name=course_type]").val(),
                	status: $("select[name=status]").val(),
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
	
	
	//表单的重置按钮
	$("a[name=reset]").click(function(){
		$("form[name=search_content]")[0].reset();
		$(".selectbox").select2();//刷新select样式
	});
	
	//给提交按钮写JS事件
	$("a[name=submit]").click(function(){
		//获取筛选框中变量，并适时将页面置为1
		// 发送Ajax请求
        $.ajax({
                type: "post",
                url: "sd_search_classtable",
                async: false,
                data: {
                	cr: $("select[name=cr]").val(),
                	student: $("input[name=student]").val(),
                	teacher: $("input[name=teacher]").val(),
                	time1: $("input[name=time1]").val(),
                	time2: $("input[name=time2]").val(),
                	level: $("select[name=level]").val(),
                	subject: $("select[name=subject]").val(),
                	class_time: $("select[name=class_time]").val(),
                	course_type: $("select[name=course_type]").val(),
                	status: $("select[name=status]").val(),
                	start_look: 1,
                },
                success: function (data, status) {
                	
                		var result_obj = $.parseJSON(data)[0];
                		LoadData(result_obj);
                		LoadCurrentPageInfo(result_obj);//加载当前显示的页数以及栏目的序号
                		LoadPagation(result_obj);
                		$(".selectbox").select2();//重新加载select的样式      
                },
                error: function () { alert("获取信息失败") }
                
            });
		return false;
	});
	
	
	
	
	
});
</script>
</head>
<body>

		    <menu class="menu">
			    <li class="menu-item">
			        <button type="button" class="menu-btn trigger" id = "confirm">
			            <i class="fa fa-folder-open"></i>
			            <span class="menu-text">确认已上</span>
			        </button>
			    </li>
			</menu>
	
            <div id="page-inner"> 
               
	            <div class="row">
	                <div class="col-md-12">
	                    <!-- Advanced Tables -->
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                             课表查看
	                             <nav class="navbar navbar-default">
									<div class="container-fluid">
									    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
									      <form class="navbar-form navbar-left" role="search" name = "search_content">
									        <div class="form-group">
									        	学员姓名:<input class="form-control" name = "student" type="text">
									        	老师姓名:<input class="form-control" name = "teacher" type="text"> 
									        	科目:<s:select list="subject_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "subject" style="width:100px" theme="simple"/>
									        	<p>
									        	上课时间:<input class="form-control" name = "time1" type="text" onClick="WdatePicker()"> 
								            	&nbsp;&nbsp;至&nbsp;&nbsp;<input class="form-control" name = "time2" type="text" onClick="WdatePicker()">
								            	时间档：<s:select list="class_time_list" listKey="PId" listValue="classtime" headerKey="no" headerValue="－－－－－" class="selectbox" name = "class_time"  style="width:120px" theme="simple"/>
								            	<p>	
								            	年级：<s:select list="level_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "level"  style="width:100px" theme="simple"/>
								            	课程类型：<s:select list="course_type_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "course_type" style="width:100px" theme="simple"/>
								            	是否已上:<s:select list="#{'no':'－－－－','0':'未上','1':'已上'}"   class="selectbox" name = "status"  style="width:100px" theme="simple"/>
								            	班主任:<s:select list="stuff_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "cr" style="width:100px" theme="simple"/>
								            	<p>
								            	<a name = "reset" href="javascript:void(0);" class="btn btn-warning">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重置&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
												<a name = "submit" href="javascript:void(0);" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
								            	    
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
	                                        	<th>
		                                        	<div class="checkbox3 checkbox-success checkbox-inline checkbox-check checkbox-round  checkbox-light">
			                                            <input type="checkbox" id="checkbox-fa-light-0" >
			                                            <label for="checkbox-fa-light-0">全选</label>
			                                        </div>
	                                        	</th>
	                                            <th>序号</th>
	                                            <th>学生</th>
	                                            <th>科目</th>
	                                            <th>老师</th>
	                                            <th>上课时间</th>
	                                            <th>时间档</th>
	                                            <th>是否结课</th>
	                                            <th>课程类型</th>
	                                            <th>年级</th>
	                          					<th>更多操作</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody id = "ContentList">
	                                    <s:iterator value="ContentList" id = "content" status = "sta">
	                                    	<s:set name="id" value="#content.P_ID"/>  <!-- 定义存储ID的变量 -->
	                                    	<s:set name="num" value="item.firstItem"/>
	                                    	<tr class="odd gradeX">
	                                    		<td>
	                                    			<div class="checkbox3 checkbox-success checkbox-inline checkbox-check checkbox-round  checkbox-light">
			                                            <input type="checkbox" name = "check_content" value ="${id}" id="checkbox-fa-light-${id}">
			                                            <label for="checkbox-fa-light-${id}"></label>
			                                        </div>
	                                    		</td>
	                                            <td class="center"><s:property value="#num+#sta.count-1"/></td>
	                                            <td class="center"><s:property value="#content.student"/></td>
	                                            <td class="center"><s:property value="#content.subject"/></td>
	                                            <td class="center"><s:property value="#content.stuff"/></td>
	                                            <td class="center"><s:property value="#content.day_time"/></td>
	                                            <td class="center"><s:property value="#content.class_time"/></td>
	                                            <td class="center"><s:property value="#content.status"/></td>
	                                            <td class="center"><s:property value="#content.course_type"/></td>
	                                            <td class="center"><s:property value="#content.level"/></td>
	                                            <td class="center">
		                                            <div>
		                                            		<select class="selectbox" name="operate" id ="${id}"  style="width:100px" >  
					                                        		<option value="no">－－－－</option>
					                                        		<option value="cr_confirm_classtable">确认已上</option>
					                                                <option value="admin_delete_classtable">删除课程</option>
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
