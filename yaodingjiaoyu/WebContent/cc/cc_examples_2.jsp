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
    
 <!-- DATA TABLE SCRIPTS -->
<script src="assets/js/select2.full.min.js"></script>
<!-- 日期选择插件 -->
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript">
$(document).ready(function() {
	$("select[name=campus]").change(function(){
		if($(this).children('option:selected').val()=="no"){
			//清空cc列表数据
			$("select[name=cc]").empty();//先清空cc列表
			var option = "<option value='no'>－－－－</option>";
			$("select[name=cc]").append(option);
		}
		else{//发送Ajax请求获得列表，并加载
			//清空cc列表数据
			$("select[name=cc]").empty();//先清空cc列表
			var option = "<option value='no'>－－－－</option>";
			$("select[name=cc]").append(option);
			
			$.ajax({
                type: "post",
                url: "admin_get_salerList",
                async: false,
                data: {
                	campus: $("select[name=campus]").val(),
                },
                success: function (data, status) {//再此判断数据是否非空
                	
                		var result_obj = $.parseJSON(data)[0];
                		if(result_obj.status==200){
                			for(var i=0;i<result_obj.salerList.length;i++){
                				var option = "<option value="+result_obj.salerList[i].P_ID+">"+result_obj.salerList[i].name+"</option>";
                				$("select[name=cc]").append(option);
                			}
                		}
                		else{
                			alert("获取销售人员信息失败");
                		}
                },
                error: function () { 
	                	alert("获取销售人员信息失败");
                	}
                
            });
		}
		$(".selectbox").select2();
	});
	
	
	
	
	
	
	function LoadData(result_obj){
		//清空表格数据
		 $("#ContentList").empty();
		//判断学生列表是否为空
		if(result_obj.ContentList != null){
			var firstItem = parseInt(result_obj.firstItem);
			for(var i=0;i<result_obj.ContentList.length;i++){
				var currentItem = firstItem+i;
				
				
				//更新表格数据
				var tr_start = "<tr class='odd gradeX'>";
				var td1 = "<td>"+currentItem+"</td>";
				var td2 = "<td>"+result_obj.ContentList[i].name+"</td>";
				var td3 = "<td>"+result_obj.ContentList[i].insertTime+"</td>";
				var td4 = "<td class = 'center'>"+result_obj.ContentList[i].contents+"</td>";
				var td6 = "<td class = 'center'>"+result_obj.ContentList[i].stuff+"</td>";
				var tr_end = "</tr>";
	            
				var html = tr_start + td1 + td2 + td3 + td4 + td6 + tr_end;
	                
				
				$("#ContentList").append(html);
				
			}
			$(".selectbox").select2();//重新加载select的样式
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
                url: "cc_search_cc_content",
                async: false,
                data: {
                	name: $("input[name=name]").val(),
                	time1: $("input[name=time1]").val(),
                	time2: $("input[name=time2]").val(),
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
	$("form[name=search_cc_content]").submit(function(){
		//获取筛选框中变量，并适时将页面置为1
		// 发送Ajax请求
        $.ajax({
                type: "post",
                url: "cc_search_cc_content",
                async: false,
                data: {
                	name: $("input[name=name]").val(),
                	time1: $("input[name=time1]").val(),
                	time2: $("input[name=time2]").val(),
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
	                             跟踪记录
	                             <nav class="navbar navbar-default">
									<div class="container-fluid">
									    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
									      <form class="navbar-form navbar-left" role="search" name = "search_cc_content">
									        <div class="form-group">
								            	起始时间:<input class="form-control" name = "time1" type="text" onClick="WdatePicker()"> 
								            	结束时间:<input class="form-control" name = "time2" type="text" onClick="WdatePicker()">
								            	<p>
								            	姓名：<input type="text" class="form-control" placeholder="Search" name = "name"> 
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
	                                            <th>时间</th>
	                                            <th>内容</th>
	                                            <th>跟踪人</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody id = "ContentList">
	                                    <s:iterator value="ccContentList" id = "cc_content" status = "sta">
	                                    	<s:set name="id" value="#cc_content.P_ID"/>  <!-- 定义存储ID的变量 -->
	                                    	<s:set name="num" value="item.firstItem"/>
	                                    	<tr class="odd gradeX">
	                                            <td><s:property value="#num+#sta.count-1"/></td>
	                                            <td><s:property value="#cc_content.name"/></td>
	                                            <td><s:property value="#cc_content.insertTime"/></td>
	                                            <td class="center"><s:property value="#cc_content.contents"/></td>
	                                            <td class="center"><s:property value="#cc_content.stuff"/></td>
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
