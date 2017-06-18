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
		 $("#examplesList").empty();
		//判断学生列表是否为空
		if(result_obj.examplesList != null){
			var firstItem = parseInt(result_obj.firstItem);
			for(var i=0;i<result_obj.examplesList.length;i++){
				var currentItem = firstItem+i;
				
				if(result_obj.examplesList[i].cc_total ==null){
					result_obj.examplesList[i].cc_total = 0;
				}
				if(result_obj.examplesList[i].last_time ==null){
					result_obj.examplesList[i].last_time = "";
				}
				//更新表格数据
				var tr_start = "<tr class='odd gradeX'>";
				var td1 = "<td>"+currentItem+"</td>";
				var td2 = "<td>"+result_obj.examplesList[i].name+"</td>";
				var td3 = "<td>"+result_obj.examplesList[i].school+"</td>";
				var td4 = "<td class = 'center'>"+result_obj.examplesList[i].level+"</td>";
				var td5 = "<td class = 'center'>"+result_obj.examplesList[i].cc_total+"</td>";
				var td6 = "<td class = 'center'>"+result_obj.examplesList[i].last_time+"</td>";

				var td8_start = "<td class = 'center'>";
				var td8_end = "</td>";
				var div_start = "<div>";
				var div_end = "</div>";
				var select_start = "<select class='selectbox' name='operate' id ='"+result_obj.examplesList[i].P_ID+"'  style='width:100px' >";
				var option = "<option value='no'>－－－－</option><option value='examples_detail'>查看详情</option><option value='cc_add_content'>添加回访</option><option value='cc_change'>修改例子</option>";
				var select_end = "</select>";
				var td8 = td8_start + div_start + select_start + option + select_end + div_end + td8_end;
				var tr_end = "</tr>";
	            
				var html = tr_start + td1 + td2 + td3 + td4 + td5 + td6  +td8+ tr_end;
	                
				
				$("#examplesList").append(html);
				
			}
			$(".selectbox").select2();//重新加载select的样式
			Bind();//绑定事件
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
                url: "cc_search_examples",
                async: false,
                data: { 
                	name: $("input[name=name]").val(),
                	school: $("input[name=school]").val(),
                	address: $("input[name=address]").val(),
                	now_class: $("input[name=now_class]").val(),
                	telephone: $("input[name=telephone]").val(),
                	youxiao: $("select[name=youxiao]").val(),
                	zhuangtai: $("select[name=zhuangtai]").val(),
                	probability: $("select[name=probability]").val(),
                	level: $("select[name=level]").val(),
                	channel: $("select[name=channel]").val(),
                	cc_total: $("select[name=cc_total]").val(),
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
	//将筛选框中的内容全部清空
	$("button[name=reset]").click(function(){
		$("input[name=name]").prop("value","");
		$("input[name=school]").prop("value","");
		$("input[name=address]").prop("value","");
		$("input[name=now_class]").prop("value","");
		$("input[name=telephone]").prop("value","");
		$("select[name=youxiao] option:first").prop("selected","selected");
		$("select[name=zhuangtai] option:first").prop("selected","selected");
		$("select[name=probability] option:first").prop("selected","selected");
		$("select[name=level] option:first").prop("selected","selected");
		$("select[name=channel] option:first").prop("selected","selected");
		$("select[name=cc_total] option:first").prop("selected","selected");
		$(".selectbox").select2();
		return false;
		
	});
	//给提交按钮写JS事件
	$("form[name=searchexam]").submit(function(){
		//获取筛选框中变量，并适时将页面置为1
		// 发送Ajax请求
        $.ajax({
                type: "post",
                url: "cc_search_examples",
                async: false,
                data: { 
                	name: $("input[name=name]").val(),
                	school: $("input[name=school]").val(),
                	address: $("input[name=address]").val(),
                	now_class: $("input[name=now_class]").val(),
                	telephone: $("input[name=telephone]").val(),
                	youxiao: $("select[name=youxiao]").val(),
                	zhuangtai: $("select[name=zhuangtai]").val(),
                	probability: $("select[name=probability]").val(),
                	level: $("select[name=level]").val(),
                	channel: $("select[name=channel]").val(),
                	cc_total: $("select[name=cc_total]").val(),
                	start_look: 1,
                	max_look: 15,
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
    

		
            <div id="page-inner"> 
               
	            <div class="row">
	                <div class="col-md-12">
	                    <!-- Advanced Tables -->
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                             线索客户
	                             <nav class="navbar navbar-default">
									<div class="container-fluid">
									    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
									      <form class="navbar-form navbar-left" role="search" name = "searchexam">
									        <div class="form-group">
									        	姓名：<input type="text" class="form-control" placeholder="Search" name = "name">
									        	学校：<input type="text" class="form-control" placeholder="Search" name = "school">
									        	添加人：<input type="text" class="form-control" placeholder="Search" name = "address">
								            	<p>       
									            电话：<input type="text" class="form-control" placeholder="Search" name = "telephone">
									            班级：<input type="text" class="form-control" placeholder="Search" name = "now_class">
									            年级：<s:select list="level_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "level"  style="width:100px" theme="simple"/>
									            <p>有效：<s:select list="#{'0':'无效','1':'有效'}"  headerKey="no" headerValue="－－－－" class="selectbox" name = "youxiao"  style="width:100px" theme="simple"/>
									            状态：<s:select list="#{'0':'未上门','1':'已上门'}"  headerKey="no" headerValue="－－－－" class="selectbox" name = "zhuangtai" style="width:100px" theme="simple"/>
									            可能性：<s:select list="probability_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "probability" style="width:100px" theme="simple"/>
									            <p>
									            来源：<s:select list="channel_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "channel" style="width:100px" theme="simple"/>
								            	跟踪次数：<s:select list="#{'0':'未跟踪','5':'5次以内','10':'10次以内','11':'大于10次'}"  headerKey="no" headerValue="－－－－" class="selectbox" name = "cc_total" style="width:100px" theme="simple"/>
								            	<button type="submit" class="btn btn-default">提交</button>  
								            	<button  name = "reset" class="btn btn-default">重置</button>    
									        </div>
									        
									      </form>
									    </div><!-- /.navbar-collapse -->
									</div>
								</nav>
	                        </div>
	                    <!--START Advanced Tables -->
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
	                                            <th>跟踪次数</th>
	                                            <th>最后跟踪时间</th>
	                          					<th>更多</th>
	                                        </tr>
	                                    </thead>
	                                   
	                                    <tbody id = "examplesList">
	                                    <s:iterator value="examples_list" id = "examples" status = "sta">
	                                    	<s:set name="id" value="#examples.P_ID"/>  
	                                    	<s:set name="num" value="item.firstItem"/>
	                                    	<tr class="odd gradeX">
	                                            <td><s:property value="#num+#sta.count-1"/></td>
	                                            <td><s:property value="#examples.name"/></td>
	                                            <td><s:property value="#examples.school"/></td>
	                                            <td class="center"><s:property value="#examples.level"/></td>
	                                            <td class="center"><s:property value="#examples.cc_total"/></td>
	                                            <td class="center"><s:property value="#examples.last_time"/></td>
	                                            <td class="center">
		                                            <div>
		                                            		<select class="selectbox" name="operate" id ="${id}"  style="width:100px" >  
					                                        		<option value="no">－－－－</option>
					                                                <option value="examples_detail">查看详情</option>
					                                                <option value="cc_add_content">添加回访</option>
					                                                <option value="cc_change">修改例子</option>
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
