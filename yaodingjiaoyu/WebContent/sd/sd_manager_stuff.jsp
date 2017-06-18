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
    <link href="assets/css/checkbox3.min.css" rel="stylesheet" >
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
		<script>
			
		</script>
<!-- /alert -->


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
	//获取选中的员工列表
	function getStuffList() {
		var stuff_list = new Array();
		$("input[name=check_content]").each(function(){
			
			if($(this).prop("checked")){
				stuff_list.push($(this).val());
			}
		});
		return stuff_list;
	}

	//用来绑定右键菜单(冻结账号)的点击事件
	$("#sd_freeze_stuff_list").bind('mousedown',function(){
		//这里上传选中的课程编号，进行处理
		var message = "您确定要执行<冻结账号>操作吗?";
		var r=confirm(message);
		if (r==true) {
			doFreezeOption("sd_freeze_stuff_list");
		}
	});

	//用来绑定右键菜单(解除冻结)的点击事件
	$("#sd_unfreeze_stuff_list").bind('mousedown',function(){
		//这里上传选中的课程编号，进行处理
		var message = "您确定要执行<解除冻结>操作吗?";
		var r=confirm(message);
		if (r==true) {
			doFreezeOption("sd_unfreeze_stuff_list");
		}
	});

	function doFreezeOption(url) {
		//获取所有选中的checkbox，如果没有就弹一个框
		var stuff_list = getStuffList();
		//判断选中是不是空
		if(stuff_list.length == 0){
			alert("不能空选");
		} else {
			var json = {};
			for(var i=0;i<stuff_list.length;i++)
			{
			    json[i]=stuff_list[i];
			}

			//发送ajax请求
			$.ajax({
                type: "post",
                url:  url,
                async: false,
                data: { 
                	stuff_list_json: JSON.stringify(json),
                },
                success: function (data, status) {
                	
                		var result_obj = $.parseJSON(data)[0];
                		alert(result_obj.message);
                		//需要重新加载数据
                		$("a[name=submit]").click();
                },
                error: function () { alert("操作失败!") }
                
            });
            //ajax请求发送结束
		}
	}


	
	$(".selectbox").select2();
	Bind();
	//绑定更多选项的点击事件
	function Bind(){
		$("select[name=operate]").each(function(){
			$(this).change(function(){
				
				if($(this).val()!="no"){
					var ex_Id= $(this).prop("id");
					
					if($(this).val() == "sd_lookstuff_detail") {
						window.open($(this).val()+"?id="+ex_Id);//跳转
					} else {
						//发送ajax请求
						$.ajax({
			                type: "post",
			                url: $(this).val(),
			                async: false,
			                data: {
			                	id: ex_Id,
			                },
			                success: function (data, status) {
		                		var result_obj = $.parseJSON(data)[0];
		                		alert(result_obj.message);
		                		//刷新
		                		$("a[name=submit]").click();
			                },
			                error: function () { alert("获取信息失败") }
			                
			            });
			            //ajax请求发送结束
					}
					
				}
				
		    })
			
		});
	}

	function LoadData(result_obj){
		//清空表格数据
		 $("#stuffList").empty();
		//判断学生列表是否为空
		if(result_obj.stuffList != null){
			var firstItem = parseInt(result_obj.firstItem);
			for(var i=0;i<result_obj.stuffList.length;i++){
				var currentItem = firstItem+i;
				
				
				//更新表格数据
				var tr_start = "<tr class='odd gradeX'>";
				var td0 = "<td><div class='checkbox3 checkbox-success checkbox-inline checkbox-check checkbox-round  checkbox-light'><input type='checkbox' name = 'check_content' value ='"+result_obj.stuffList[i].P_ID+"' id='checkbox-fa-light-"+result_obj.stuffList[i].P_ID+"'><label for='checkbox-fa-light-"+result_obj.stuffList[i].P_ID+"'></label></div></td>";
				var td1 = "<td>"+currentItem+"</td>";
				var td2 = "<td>"+result_obj.stuffList[i].name+"</td>";
				var td3 = "<td>"+result_obj.stuffList[i].sex+"</td>";
				var td4 = "<td class = 'center'>"+result_obj.stuffList[i].phone+"</td>";
				var td5 = "<td class = 'center'>"+result_obj.stuffList[i].username+"</td>";
				var td6 = "<td class = 'center'>"+result_obj.stuffList[i].job+"</td>";
				var td7 = "<td class = 'center'>"+result_obj.stuffList[i].status+"</td>";
				var td7_next = "<td class = 'center'>"+result_obj.stuffList[i].part_time+"</td>";
				var td8_start = "<td class = 'center'>";
				var td8_end = "</td>";
				var div_start = "<div>";
				var div_end = "</div>";
				var select_start = "<select class='selectbox' name='operate' id ='"+result_obj.stuffList[i].P_ID+"'  style='width:100px' >";
				
				var option;
				if(result_obj.stuffList[i].status == "正常") {
					option = "<option value='no'>－－－－</option><option value='sd_lookstuff_detail'>查看详情</option><option value='sd_freeze_stuff'>冻结账户</option><option value='sd_resetstuff_password'>重置密码</option>";
				} else {
					option = "<option value='no'>－－－－</option><option value='sd_lookstuff_detail'>查看详情</option><option value='sd_unfreeze_stuff'>解除冻结</option><option value='sd_resetstuff_password'>重置密码</option>";
				}
				
				var select_end = "</select>";
				var td8 = td8_start + div_start + select_start + option + select_end + div_end + td8_end;
				var tr_end = "</tr>";
	            
				var html = tr_start + td0 + td1 + td2 + td3 + td4 + td5 + td6 + td7 + td7_next + td8+ tr_end;
	                
				
				$("#stuffList").append(html);
				
			}
			$(".selectbox").select2();//重新加载select的样式
			Bind();
		}	
	}
	//表单的重置按钮
	$("a[name=reset]").click(function(){
		$("form[name=searchStuff]")[0].reset();
		$(".selectbox").select2();//刷新select样式
	});
	
	//给提交按钮写JS事件
	$("a[name=submit]").click(function(){
		//获取筛选框中变量，并适时将页面置为1
		// 发送Ajax请求
        $.ajax({
                type: "post",
                url: "sd_search_stuff",
                async: false,
                data: {
                	name: $("input[name=name]").val(),
                	job: $("select[name=job]").val(),
                	part_time: $("select[name=part_time]").val(),
                	status: $("select[name=status]").val(),
                	start_look: 1,
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
                url: "sd_search_stuff",
                async: false,
                data: { 
                	name: $("input[name=name]").val(),
                	job: $("select[name=job]").val(),
                	part_time: $("select[name=part_time]").val(),
                	status: $("select[name=status]").val(),
                	start_look: $("select[name=start_look]").find("option:selected").val(),
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

	$("#checkbox-fa-light-0").click(function(){
		$("input[name=check_content]").each(function(){
			$(this).prop("checked",$("#checkbox-fa-light-0").prop("checked"));
		});
	});

	
	
});
</script>
</head>
<body>
		    <menu class="menu">
			    <li class="menu-item">
			        <button type="button" class="menu-btn" id = "sd_freeze_stuff_list">
			            <i class="fa fa-folder-open"></i>
			            <span class="menu-text" >冻结账号</span>
			        </button>
			    </li>
			    <li class="menu-item">
			        <button type="button" class="menu-btn" id = "sd_unfreeze_stuff_list">
			        	<i class="fa fa-folder-open"></i>
			            <span class="menu-text">解除冻结</span>
			        </button>
			    </li>
			</menu>

            <div id="page-inner"> 
               
	            <div class="row">
	                <div class="col-md-12">
	                    <!-- Advanced Tables -->
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                             员工信息
	                             <nav class="navbar navbar-default">
									<div class="container-fluid">
									    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
									      <form class="navbar-form navbar-left" role="search" name = "searchStuff">
									        <div class="form-group">
									        	姓名:<input type="text" class="form-control" placeholder="姓名" name = "name">
									        	岗位:<s:select list="job_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "job" style="width:100px" theme="simple"/>
								            	状态:<s:select list="#{'no':'－－－－','0':'正常','1':'冻结'}" class="selectbox" name = "status"  style="width:100px"/>
									            是否兼职:<s:select list="#{'no':'－－－－','0':'常规','1':'兼职'}" class="selectbox" name = "part_time" style="width:100px"/>
								            	<p><a name = "reset" href="javascript:void(0);" class="btn btn-warning">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重置&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
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
	                                            <th>姓名</th>
	                                            <th>性别</th>
	                                            <th>电话</th>
	                                            <th>用户名</th>
	                                            <th>岗位</th>
	                                            <th>状态</th>
	                          					<th>兼职</th>
	                          					<th>更多</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody id = "stuffList">
	                                    <s:iterator value="stuffList" id = "stuff" status = "sta">
	                                    	<s:set name="id" value="#stuff.P_ID"/>  <!-- 定义存储ID的变量 -->
	                                    	<s:set name="num" value="item.firstItem"/>
	                                    	<tr class="odd gradeX">
	                                    		<td>
	                                    			<div class="checkbox3 checkbox-success checkbox-inline checkbox-check checkbox-round  checkbox-light">
			                                            <input type="checkbox" name = "check_content" value ="${id}" id="checkbox-fa-light-${id}">
			                                            <label for="checkbox-fa-light-${id}"></label>
			                                        </div>
	                                    		</td>
	                                            <td><s:property value="#num+#sta.count-1"/></td>
	                                            <td><s:property value="#stuff.name"/></td>
	                                            <td><s:property value="#stuff.sex"/></td>
	                                            <td class="center"><s:property value="#stuff.phone"/></td>
	                                            <td class="center"><s:property value="#stuff.username"/></td>
	                                            <td class="center"><s:property value="#stuff.job"/></td>
	                                            <td class="center"><s:property value="#stuff.status"/></td>
	                                            <td class="center"><s:property value="#stuff.part_time"/></td>
	                                            <td class="center">
		                                            <div>
		                                            		<s:if test="(#stuff.status == '正常')">
																<select class="selectbox" name="operate" id ="${id}"  style="width:100px" >  
					                                        		<option value="no">－－－－</option>
					                                                <option value="sd_lookstuff_detail">查看详情</option>
					                                                <option value="sd_freeze_stuff">冻结账户</option>
					                                                <option value="sd_resetstuff_password">重置密码</option>
					                                        	</select>
															</s:if>
															<s:else>
																<select class="selectbox" name="operate" id ="${id}"  style="width:100px" >  
					                                        		<option value="no">－－－－</option>
					                                                <option value="sd_lookstuff_detail">查看详情</option>
					                                                <option value="sd_unfreeze_stuff">解除冻结</option>
					                                                <option value="sd_resetstuff_password">重置密码</option>
					                                        	</select>
															</s:else>
		                                            		
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
