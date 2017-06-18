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
    <link rel="stylesheet" type="text/css" href="Menu/css/styles.css">
	
	<!-- DATA TABLE SCRIPTS -->
	<script src="assets/js/select2.full.min.js"></script>
	
	<!-- alert -->
		<!-- common styles -->
		<link rel="stylesheet" type="text/css" href="alert/css/dialog.css" />
		<!-- individual effect -->
		<link rel="stylesheet" type="text/css" href="alert/css/dialog-annie.css" />
		<script src="alert/js/modernizr.custom.js"></script>
		<script src="alert/js/classie.js"></script>
		<script src="alert/js/dialogFx.js"></script>
	<!-- /alert -->
    

<!-- 日期选择插件 -->
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript">
$(document).ready(function() {
	$(".selectbox").select2();//重新加载select的样式
	
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
	    $("#classtable_data").attr("pram",$(this).attr("classtable_id"));
	}
	function onMouseDown(e){
	    hideMenu();
	    document.removeEventListener('mousedown', onMouseDown);
	}

	//--------------------------- 右键菜单
	//用来绑定右键菜单的添加备注
	$("#add_classRemark").bind('mousedown',function(){
		//这里上传选中的课程编号，进行处理
		var message = "请输入备注:";
		var remark=prompt(message);
		if(remark != null) {
			if ((remark=="")) {
					alert("备注不能为空!");
				} else {
					doAddClassRemark(remark);
				}
		}
		
	});

	function doAddClassRemark(remark_text) {
		//右键的时候需要保存选中课程的id，但是又要注意当没有id不能提交的问题
		if($("#classtable_data").attr("pram") !="") {
			//发送Ajax请求
			$.ajax({
                type: "post",
                url: 'cr_add_classtable_remark',
                async: false,
                data: {
                	id: $("#classtable_data").attr("pram"),
                	remark: remark_text,
                },
                success: function (data, status) {
                	var result_obj = $.parseJSON(data)[0];
                	//刷新数据  
                	alert(result_obj.message);
                	$("form[name=search_classtable]").submit();
                },
                error: function () { alert("获取信息失败") }
                
            });
		} else {
			alert("未选择需要操作的课程!");
		}
		$("#classtable_data").attr("pram","");
	}

	//用来绑定右键菜单的确认上课
	$("#confirm_classtable").bind('mousedown',function(){
		//这里上传选中的课程编号，进行处理
		var message = "您确定要执行<确认课程>操作吗?";
		var r=confirm(message);
		if (r==true) {
			doConfirmClass();
		}
	});

	function doConfirmClass() {
		//右键的时候需要保存选中课程的id，但是又要注意当没有id不能提交的问题
		if($("#classtable_data").attr("pram") !="") {
			//发送ajax请求
			//发送Ajax请求
			$.ajax({
                type: "post",
                url: 'cr_confirm_classtable',
                async: false,
                data: {
                	id: $("#classtable_data").attr("pram"),
                },
                success: function (data, status) {
                	var result_obj = $.parseJSON(data)[0];
                	//刷新数据  
                	alert(result_obj.message);
                	$("form[name=search_classtable]").submit();
                },
                error: function () { alert("获取信息失败") }
                
            });
		} else {
			alert("未选择需要操作的课程!");
		}
		$("#classtable_data").attr("pram","");
	}

	//用来绑定右键菜单的删除课程
	$("#delete_classtable").bind('mousedown',function(){
		//这里上传选中的课程编号，进行处理
		var message = "您确定要执行<删除课程>操作吗?";
		var r=confirm(message);
		if (r==true) {
			doDeleteClass();

		}
	});

	function doDeleteClass() {
		//右键的时候需要保存选中课程的id，但是又要注意当没有id不能提交的问题
		if($("#classtable_data").attr("pram") !="") {
			//发送Ajax请求
			$.ajax({
                type: "post",
                url: 'cr_delete_classtable',
                async: false,
                data: {
                	id: $("#classtable_data").attr("pram"),
                },
                success: function (data, status) {
                	var result_obj = $.parseJSON(data)[0];
                	//刷新数据  
                	alert(result_obj.message);
                	$("form[name=search_classtable]").submit();
                },
                error: function () { alert("获取信息失败") }
                
            });
		} else {
			alert("未选择需要操作的课程!");
		}
		$("#classtable_data").attr("pram","");
	}
	
	//给所有没有完结课程添加点击事件
	function BindTableClick(){
		
		var somedialog = document.getElementById("somedialog");
		var dlg = new DialogFx(somedialog);
		
		$(".trigger").each(function(){
			
			if($(this).attr("status") != 1){
				this.addEventListener( 'click', dlg.toggle.bind(dlg));
				
				
				//这里首先需要取消上次绑定
				$(this).unbind('click').click(function(){
					//在这里记录数据存储到隐藏域
					$("#savedata").attr("stuff",$(this).parent().parent().parent().attr("name"));
					$("#savedata").attr("pram",$(this).attr("name"));
				});

				//绑定右键点击事件
				this.addEventListener('contextmenu', onContextMenu, false);
				
			}
			
		});
		
		
	}
	//调用表格的绑定点击事件
	BindTableClick();

	function LoadData(result_obj){
		//清空表格数据
		 $("#content").empty();
		
		var tr_list = result_obj.tr_list;
		var table = result_obj.table;
		var day = result_obj.day;
		var class_time = result_obj.class_time;
		//判断学生列表是否为空
		if(tr_list != null){
			for(var i=0;i<tr_list.length;i++){
				var current_table;
				if(table != null){
					current_table = table[tr_list[i].P_ID];
				}else{
					current_table = null;
				}
				
				if(current_table != null){//该老师课表不为空
					
					//输出课表
					var result = "<table name='"+tr_list[i].P_ID+"' class='table table-striped table-bordered table-hover' id='dataTables-example'>";
					result += "<thead>";
					result += "<tr>";
					result += "<th style='text-align:center;'>老师</th>";
					result += "<th style='text-align:center;'>时间挡</th>";
					result += "<th style='text-align:center;' name = 'time' title = '"+day.one+"'>周一("+getDay(day.one)+")</th>";
					result += "<th style='text-align:center;' title = '"+day.two+"'>周二("+getDay(day.two)+")</th>";
					result += "<th style='text-align:center;' title = '"+day.three+"'>周三("+getDay(day.three)+")</th>";
					result += "<th style='text-align:center;' title = '"+day.four+"'>周四("+getDay(day.four)+")</th>";
					result += "<th style='text-align:center;' title = '"+day.five+"'>周五("+getDay(day.five)+")</th>";
					result += "<th style='text-align:center;' title = '"+day.six+"'>周六("+getDay(day.six)+")</th>";
					result += "<th style='text-align:center;' title = '"+day.seven+"'>周日("+getDay(day.seven)+")</th>";
					result += "</tr>";
					result += "</thead>";
					result += "<tbody>";
					
					for(var k=0;k < class_time.length;k++){
						
						result += "<tr class='odd gradeX'>";
						if(0 == k){
							result += "<td class='center' rowspan='7' style='width:1em;vertical-align:middle;' title = '"+tr_list[i].phone+"'>"+tr_list[i].name+"</td>";
						}
						
						result += "<td class='center' style='text-align:center;'>"+class_time[k].classtime+"</td>";
						
						for(var j=1; j<=7; j++){
							var key = j+'_'+(k+1);
							if(current_table[key]!=null){
								result += "<td data-dialog='somedialog' classtable_id = '"+current_table[key].classtable_id+"' status = '"+current_table[key].status+"' name='"+key+"' class='trigger' align='center'  title ='"+current_table[key].title+"'  style='background-color:"+current_table[key].color+";'>"+current_table[key].student+"</td>";
							}else{
								result += "<td data-dialog='somedialog' classtable_id = '' status = '0' name='"+key+"' class='trigger' align='center'></td>";
							}
							
							
						}
						result += "</tr>";

					}
					result += "</tbody>";
					result += "</table>";
				}
				else{
					//老师在该天没有课程，显示空课表
					var result = "<table name='"+tr_list[i].P_ID+"' class='table table-striped table-bordered table-hover' id='dataTables-example'>";
					result += "<thead>";
					result += "<tr>";
					result += "<th style='text-align:center;'>老师</th>";
					result += "<th style='text-align:center;'>时间挡</th>";
					result += "<th style='text-align:center;' name = 'time' title = '"+day.one+"'>周一("+getDay(day.one)+")</th>";
					result += "<th style='text-align:center;' title = '"+day.two+"'>周二("+getDay(day.two)+")</th>";
					result += "<th style='text-align:center;' title = '"+day.three+"'>周三("+getDay(day.three)+")</th>";
					result += "<th style='text-align:center;' title = '"+day.four+"'>周四("+getDay(day.four)+")</th>";
					result += "<th style='text-align:center;' title = '"+day.five+"'>周五("+getDay(day.five)+")</th>";
					result += "<th style='text-align:center;' title = '"+day.six+"'>周六("+getDay(day.six)+")</th>";
					result += "<th style='text-align:center;' title = '"+day.seven+"'>周日("+getDay(day.seven)+")</th>";
					result += "</tr>";
					result += "</thead>";
					result += "<tbody>";
					
					for(var k=0;k < class_time.length;k++){
						
						result += "<tr class='odd gradeX'>";
						if(0 == k){
							result += "<td class='center' rowspan='7' style='width:1em;vertical-align:middle;' title = '"+tr_list[i].phone+"'>"+tr_list[i].name+"</td>";
						}
						
						result += "<td class='center' style='text-align:center;'>"+class_time[k].classtime+"</td>";
						
						for(var j=1; j<=7; j++){
							var key = j+'_'+(k+1);
							result += "<td data-dialog='somedialog' classtable_id = '' status = '0' name='"+key+"' class='trigger' align='center'></td>";	
						}
						result += "</tr>";

					}
					result += "</tbody>";
					result += "</table>";
				}
				$("#content").append(result);//将一张表加入显示列表中
				//调用表格的绑定点击事件
				BindTableClick();
			}
			
		}
		
		
	}
	
	
	function getDay(date){
	    var date = date || new Date(),
	        timestamp, newDate;
	    if(!(date instanceof Date)){
	        date = new Date(date.replace(/-/g, '/'));
	    }
	    timestamp = date.getTime();
	    newDate = new Date(timestamp);
	    return [newDate.getMonth() + 1, newDate.getDate()].join('-');
	}
	
	function getDaysBefore(date,n){
	    var date = date || new Date(),
	        timestamp, newDate;
	    if(!(date instanceof Date)){
	        date = new Date(date.replace(/-/g, '/'));
	    }
	    timestamp = date.getTime();
	    newDate = new Date(timestamp - n * 24 * 3600 * 1000);
	    return [newDate.getFullYear(), newDate.getMonth() + 1, newDate.getDate()].join('-');
	}


	
	function getDaysLast(date,n){
	    var date = date || new Date(),
	        timestamp, newDate;
	    if(!(date instanceof Date)){
	        date = new Date(date.replace(/-/g, '/'));
	    }
	    timestamp = date.getTime();
	    newDate = new Date(timestamp + n * 24 * 3600 * 1000);
	    return [newDate.getFullYear(), newDate.getMonth() + 1, newDate.getDate()].join('-');
	}
	
	
	$("a[name=last_week]").click(function(){
		var tar_time =  getDaysLast($("th[name=time]").eq(0).prop("title"),7);
		getPageAjax(tar_time);
	});
	

	$("a[name=before_week]").click(function(){
		//获得上周一的时间
		var tar_time =  getDaysBefore($("th[name=time]").eq(0).prop("title"),7);
		getPageAjax(tar_time);
	});
	//改变页面的Ajax封装
	function getPageAjax(tar_time){
		// 发送Ajax请求
        $.ajax({
                type: "post",
                url: "cr_search_classtable",
                async: false,
                data: {
                	time: tar_time,
                	name: $("input[name=name]").val(),
                },
                success: function (data, status) {
                	
                		var result_obj = $.parseJSON(data)[0];
                		LoadData(result_obj);
                },
                error: function () { alert("获取信息失败") }
                
            });
       
	}

	//给提交按钮写JS事件
	$("form[name=search_classtable]").submit(function(){
		//获取筛选框中变量，并适时将页面置为1
		// 发送Ajax请求
        $.ajax({
                type: "post",
                url: "cr_search_classtable",
                async: false,
                data: {
                	time: $("input[name=time]").val(),
                	name: $("input[name=name]").val(),
                },
                success: function (data, status) {
                	
                		var result_obj = $.parseJSON(data)[0];
                		LoadData(result_obj);
                		      
                },
                error: function () { alert("获取信息失败") }
                
            });
		return false;
	});
	
	//弹框的提交按钮
	$("a[name=submit_add]").click(function(){
		//本周的周一时间
		var one = $("th[name=time]").eq(0).prop("title");
		var stuff = $("#savedata").attr("stuff");
		 
		//获得课表的档
		var day_last = $("#savedata").attr("pram").split("_")[0];
		var class_time = $("#savedata").attr("pram").split("_")[1];
		var day_time = getDaysLast(one,parseInt(day_last)-1);
		
		if($('select[name=student] option:selected').val()==$('select[name=student] option:first').val()){
			//未选择学生不上传
			alert("未选择学生!");
		}
		else{
			//提交学员编号，老师编号，上课时间，以及时间档,课程类型
			$.ajax({
	            type: "post",
	            url: "cr_add_classtable",
	            async: false,
	            data: {
	            	student: $("select[name=student]").val(),
	            	course_type: $("select[name=course_type]").val(),
	            	day_time: day_time,
	            	stuff: stuff,
	            	class_time: class_time,
	            	delay_week: $("select[name=delay_week]").val(),
	            },
	            success: function (data, status) {//再此判断数据是否非空
	            	
	            		var result_obj = $.parseJSON(data)[0];
	            		
	           			//关闭弹框
	           			$("#close_paike").click();
	           			//请求新的数据
	          				var tar_time =  getDaysBefore($("th[name=time]").eq(0).prop("title"),0);//获得上周一的时间
	          				getPageAjax(tar_time);
	           			//更新单价
	           			 alert(result_obj.message);
	            },
	            error: function () { 
	                	alert("获取课时信息失败");
	            	}
	            
	        });
		}
		
		
	});
	
	$("select[name=student]").change(function(){
		if($("select[name=student]").children('option:selected').val()!="no"){
			//发送Ajax
			GetKeshi();
		}
	});
	
	$("select[name=course_type]").change(function(){
		if($("select[name=student]").children('option:selected').val()!="no"){
			//发送Ajax
			GetKeshi();
		}
	});
	
	function GetKeshi(){
		$.ajax({
            type: "post",
            url: "cr_get_classhour",
            async: false,
            data: {
            	student: $("select[name=student]").val(),
            	course_type: $("select[name=course_type]").val(),
            },
            success: function (data, status) {//再此判断数据是否非空
            	
            		var result_obj = $.parseJSON(data)[0];
            		if(result_obj.status==200){
            			//更新单价
            			 $("input[name=keshi]").prop("value",result_obj.last_hour);
            		}
            		else{
            			alert("获取课时信息失败");
            		}
            },
            error: function () { 
                	alert("获取课时信息失败");
            	}
            
        });
	}
	
	
	
	
	
});
</script>
</head>
<body>
			<!-- 右键菜单 -->
			<menu class="menu" id = "classtable_data" pram="">
			    <li class="menu-item">
			        <button type="button" class="menu-btn trigger" id = "confirm_classtable">
			            <i class="fa fa-folder-open"></i>
			            <span class="menu-text">确认已上</span>
			        </button>
			    </li>
			    
			    <li class="menu-item">
			        <button type="button" class="menu-btn trigger" id = "delete_classtable">
			            <i class="fa fa-folder-open"></i>
			            <span class="menu-text">删除课程</span>
			        </button>
			    </li>
			    
			    <li class="menu-item">
			        <button type="button" class="menu-btn trigger" id = "add_classRemark">
			            <i class="fa fa-folder-open"></i>
			            <span class="menu-text">添加备注</span>
			        </button>
			    </li>
			</menu>
			
			<!-- 弹框的内容 -->
	       		 <div id="somedialog" class="dialog" style="z-index:6">
					<div class="dialog__overlay"></div>
					<div class="dialog__content">
						<strong>排课</strong>
						<div>
							<table class="table table-striped table-bordered table-hover" id = "savedata" stuff="" pram="">
								<tr class="odd gradeX">
									<td>姓名:</td>
									<td>
										<s:if test="(stu_list != null)">
												<s:select list="stu_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "student" style="width:200px" theme="simple"/>			          
										</s:if>
										<s:else>
												<s:select list="#{'no':'－－－－'}"  class="selectbox" name = "student" style="width:200px" theme="simple"/>
										</s:else>
									</td>
								</tr>
								<tr class="odd gradeX">
									<td>课程类型:</td>
									<td><s:select list="course_type_list" listKey="PId" listValue="name"  class="selectbox" name = "course_type" style="width:200px" theme="simple"/></td>
								</tr>
								<tr class="odd gradeX">
									<td>剩余课时:</td>
									<td align="center"><input type="text" style="width:200px;text-align:center;color:#298bf7;" disabled="disabled" class="form-control" placeholder="Search" name = "keshi"></td>
								</tr>
								<tr class="odd gradeX">
									<td>顺延周数:</td>
									<td><s:select list="#{'0':'0','1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}"   style="width:200px" class="selectbox" name = "delay_week"  theme="simple"/></td>
								</tr>
								<tr class="odd gradeX">
									<td colspan="2" align="center">
										<a href="javascript:void(0);" class="btn btn-warning action" data-dialog-close>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="close_paike">关闭</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
										<a name = "submit_add" href="javascript:void(0);" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			<!-- ／弹框的内容 -->
		
            <div id="page-inner"> 
            
            
               
	            <div class="row">
	                <div class="col-md-12">
	                    <!-- Advanced Tables -->
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                             名师课程时间安排
	                             <nav class="navbar navbar-default">
									<div class="container-fluid">
									    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
									      <form class="navbar-form navbar-left" role="search" name = "search_classtable">
									        <div class="form-group">
									        	老师:<input type="text" class="form-control" placeholder="Search" name = "name">
						  						时间:<input class="form-control" name = "time" type="text" onClick="WdatePicker()">
								            	<button type="submit" class="btn btn-default">提交</button>  
								            	<p>
								            	<a name = "before_week" href="javascript:void(0);" class="btn btn-warning">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上一周&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
									        	<a name = "last_week" href="javascript:void(0);" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下一周&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
									        </div>
									      </form>
									    </div><!-- /.navbar-collapse -->
									</div>
								</nav>
	                        </div>
	                        
	                        <div class="panel-body">
	                            <div class="table-responsive">
	                            
		                            <div id = "content">
										 <s:iterator value="tr_list" id="tr">
											<s:if test="table.containsKey(#tr.PId+'')">
												<!-- 输出课程表 -->
												<table name="${tr.PId}" class="table table-striped table-bordered table-hover" id="dataTables-example">
				                                    <thead>
				                                        <tr>
				                                        	<th style="text-align:center;">老师</th>
				                                            <th  style="text-align:center;">时间挡</th>
				                                            <th style="text-align:center;font-size: 12px;" name = "time" title = "${day.one}">周一(${day.one})</th>
				                                            <th style="text-align:center;font-size: 12px;" title = "${day.two}">周二(${day.two})</th>
				                                            <th style="text-align:center;font-size: 12px;" title = "${day.three}">周三(${day.three})</th>
				                                            <th style="text-align:center;font-size: 12px;" title = "${day.four}">周四(${day.four})</th>
				                                            <th style="text-align:center;font-size: 12px;" title = "${day.five}">周五(${day.five})</th>
				                                            <th style="text-align:center;font-size: 12px;" title = "${day.six}">周六(${day.six})</th>
				                                            <th style="text-align:center;font-size: 12px;" title = "${day.seven}">周日(${day.seven})</th>
				                                        </tr>
				                                    </thead>
				                                    <tbody>
				                                    	 <s:iterator value="class_time" status="i" id = "time">
				                                    	 	<s:if test="(1 == #i.count)">
															          <td class="center" rowspan="7" style="width:1em;vertical-align:middle;" title = "${tr.phone}">${tr.name}</td>
															</s:if>
															<tr class="odd gradeX">
				                                    	 		<td class="center" style="text-align:center;"><s:property value="#time.classtime"/></td>
														     	<s:iterator value="new int[7]" status="j">
														     		<s:set name="map_key" value="#j.count+'_'+#i.count"/>
							                                    	<td data-dialog="somedialog" classtable_id ="${table.get(tr.PId.toString()).get(map_key).classtable_id}"  status ="${table.get(tr.PId.toString()).get(map_key).status}" class="trigger" name="${map_key}" align="center"  title ="${table.get(tr.PId.toString()).get(map_key).title}"  style="background-color:${table.get(tr.PId.toString()).get(map_key).color};">${table.get(tr.PId.toString()).get(map_key).student}</td>
														     	</s:iterator>
													     	</tr>
													     </s:iterator>
				                                    </tbody>
			                                	</table> 
											</s:if>
											<s:else> 
												<!-- 输出空课程表 -->
												<table name="${tr.PId}" class="table table-striped table-bordered table-hover" id="dataTables-example">
				                                    <thead>
				                                        <tr>
				                                        	<th style="text-align:center;">老师</th>
				                                            <th  style="text-align:center;">时间挡</th>
				                                            <th style="text-align:center;font-size: 12px;" name = "time" title = "${day.one}">周一(${day.one})</th>
				                                            <th style="text-align:center;font-size: 12px;" title = "${day.two}">周二(${day.two})</th>
				                                            <th style="text-align:center;font-size: 12px;" title = "${day.three}">周三(${day.three})</th>
				                                            <th style="text-align:center;font-size: 12px;" title = "${day.four}">周四(${day.four})</th>
				                                            <th style="text-align:center;font-size: 12px;" title = "${day.five}">周五(${day.five})</th>
				                                            <th style="text-align:center;font-size: 12px;" title = "${day.six}">周六(${day.six})</th>
				                                            <th style="text-align:center;font-size: 12px;" title = "${day.seven}">周日(${day.seven})</th>
				                                        </tr>
				                                    </thead>
				                                    <tbody>
				                                    	 <s:iterator value="class_time" status="i" id = "time">
				                                    	 	<s:if test="(1 == #i.count)">
															          <td class="center" rowspan="7" style="width:1em;vertical-align:middle;" title = "${tr.phone}">${tr.name}</td>
															</s:if>
															<tr class="odd gradeX">
				                                    	 		<td class="center" style="text-align:center;"><s:property value="#time.classtime"/></td>
														     	<s:iterator value="new int[7]" status="j">
														     		<s:set name="map_key" value="#j.count+'_'+#i.count"/>
							                                    	<td data-dialog="somedialog" classtable_id = '' status="0" name="${map_key}" class="trigger" align="center"></td>
														     	</s:iterator>
													     	</tr>
													     </s:iterator>
				                                    </tbody>
			                                	</table> 
											</s:else>
											
										</s:iterator>
										<!-- 如果table为空就进行相应的处理 -->
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
