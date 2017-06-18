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
	
	
	
	$("#checkbox-fa-light-0").click(function(){
		$("input[name=check_content]").each(function(){
			$(this).prop("checked",$("#checkbox-fa-light-0").prop("checked"));
		});
	});
	
	
	
	//---------------------------
	//给所有没有完结课程添加点击事件
	function BindTableClick(){
		
		var somedialog = document.getElementById("somedialog");
		var dlg = new DialogFx(somedialog);
		var fenpei = document.getElementById("fenpei_menu");
		fenpei.addEventListener( 'mousedown', dlg.toggle.bind(dlg));
		
	}
	//调用表格的绑定点击事件
	BindTableClick();
	//--------------------------
	
Bind();
	
	function Bind(){
		$("select[name=operate]").each(function(){
			$(this).change(function(){
				
				if($(this).val()!="no"){
					var ex_Id= $(this).prop("id");
					//跳转
					window.open($(this).val()+"?id="+ex_Id);
				}
				
		    })
			
		});
	}
	
$("a[name=submit_choose]").click(function(){
		
		//获取所有选中的checkbox，如果没有就弹一个框
		var examples_list = new Array();
		$("input[name=check_content]").each(function(){
			
			if($(this).prop("checked")){
				examples_list.push($(this).val());
			}
		});
		//判断选中是不是空
		if(examples_list.length == 0){
			alert("不能空选");
			$("#close_devide").click();
		}else{
			
			if($('select[name=cc] option:selected').val() == "no"){
				alert("未选择销售");
			}else{
				
				var json = {};
				for(var i=0;i<examples_list.length;i++)
				{
				    json[i]=examples_list[i];
				}
				
				
				
				//发送AJax
				$.ajax({
	                type: "post",
	                url: "sd_distribution_examples",
	                async: false,
	                data: { 
	                	examples_list_json: JSON.stringify(json),
	                	cc: $("select[name=cc]").val(),
	                },
	                success: function (data, status) {
	                	
	                		var result_obj = $.parseJSON(data)[0];
	                		alert(result_obj.message);
	                		
	                },
	                error: function () { alert("获取信息失败") }
	                
	            });
				
				$("#close_devide").click();
			}
			
		}
		
	});
	
	
	
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
				var td0 = "<td><div class='checkbox3 checkbox-success checkbox-inline checkbox-check checkbox-round  checkbox-light'><input type='checkbox' name = 'check_content' value ='"+result_obj.examplesList[i].P_ID+"' id='checkbox-fa-light-"+result_obj.examplesList[i].P_ID+"'><label for='checkbox-fa-light-"+result_obj.examplesList[i].P_ID+"'></label></div></td>";
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
				var option = "<option value='no'>－－－－</option><option value='examples_detail'>详情</option><option value='cc_change'>修改</option>";
				var select_end = "</select>";
				var td8 = td8_start + div_start + select_start + option + select_end + div_end + td8_end;
				var tr_end = "</tr>";
	            
				var html = tr_start+ td0 + td1 + td2 + td3 + td4 + td5 + td6  +td8+ tr_end;
	                
				
				$("#examplesList").append(html);
				
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
                url: "sd_search_examples",
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
                	stuff: $("select[name=stuff]").val(),
                	channel: $("select[name=channel]").val(),
                	cc_total: $("select[name=cc_total]").val(),
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
	//将筛选框中的内容全部清空
	$("button[name=reset]").click(function(){
		$("input[name=name]").prop("value","");
		$("input[name=school]").prop("value","");
		$("input[name=address]").prop("value","");
		$("input[name=now_class]").prop("value","");
		$("input[name=telephone]").prop("value","");
		$("select[name=stuff] option:first").prop("selected","selected");
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
                url: "sd_search_examples",
                async: false,
                data: { 
                	name: $("input[name=name]").val(),
                	school: $("input[name=school]").val(),
                	address: $("input[name=address]").val(),
                	now_class: $("input[name=now_class]").val(),
                	telephone: $("input[name=telephone]").val(),
                	youxiao: $("select[name=youxiao]").val(),
                	zhuangtai: $("select[name=zhuangtai]").val(),
                	stuff: $("select[name=stuff]").val(),
                	probability: $("select[name=probability]").val(),
                	level: $("select[name=level]").val(),
                	channel: $("select[name=channel]").val(),
                	cc_total: $("select[name=cc_total]").val(),
                	status: $("select[name=status]").val(),
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


<!-- 弹框的内容 -->
	       		 <div id="somedialog" class="dialog" style="z-index:6">
					<div class="dialog__overlay"></div>
					<div class="dialog__content">
						<strong>分配销售</strong>
						<div>
							<table class="table table-striped table-bordered table-hover" id = "savedata" stuff="" pram="">
								<tr class="odd gradeX">
									<td>销售:</td>
									<td><s:select list="cc_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "cc" style="width:200px" theme="simple"/></td>
								</tr>
								<tr class="odd gradeX">
									<td colspan="2" align="center">
										<a name = "close_devide" href="javascript:void(0);" class="btn btn-warning action" data-dialog-close>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="close_devide">关闭</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
										<a name = "submit_choose" href="javascript:void(0);" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			<!-- ／弹框的内容 -->
    
    <menu class="menu">
	    <li class="menu-item">
	        <button type="button" class="menu-btn trigger" id = "fenpei_menu" data-dialog="somedialog">
	            <i class="fa fa-folder-open"></i>
	            <span class="menu-text" >分配</span>
	        </button>
	    </li>
	    <li class="menu-item">
	        <button type="button" class="menu-btn">
	        	<i class="fa fa-folder-open"></i>
	            <span class="menu-text">刷新</span>
	        </button>
	    </li>
	</menu>
    

		
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
									            销售：<s:select list="cc_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "stuff" style="width:100px" theme="simple"/>
									            <p>
									            来源：<s:select list="channel_list" listKey="PId" listValue="name" headerKey="no" headerValue="－－－－" class="selectbox" name = "channel" style="width:100px" theme="simple"/>
								            	跟踪次数：<s:select list="#{'0':'未跟踪','5':'5次以内','10':'10次以内','11':'大于10次'}"  headerKey="no" headerValue="－－－－" class="selectbox" name = "cc_total" style="width:100px" theme="simple"/>
								            	是否分配：<s:select list="#{'0':'未分配','1':'已分配'}"  headerKey="no" headerValue="－－－－" class="selectbox" name = "status" style="width:100px" theme="simple"/>
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
	                                        	<th>
		                                        	<div class="checkbox3 checkbox-success checkbox-inline checkbox-check checkbox-round  checkbox-light">
			                                            <input type="checkbox" id="checkbox-fa-light-0" >
			                                            <label for="checkbox-fa-light-0">全选</label>
			                                        </div>
	                                        	</th>
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
	                                    		<td>
	                                    			<div class="checkbox3 checkbox-success checkbox-inline checkbox-check checkbox-round  checkbox-light">
			                                            <input type="checkbox" name = "check_content" value ="${id}" id="checkbox-fa-light-${id}">
			                                            <label for="checkbox-fa-light-${id}"></label>
			                                        </div>
	                                    		</td>
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
					                                                <option value="examples_detail">详情</option>
					                                                <option value="cc_change">修改</option>
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
