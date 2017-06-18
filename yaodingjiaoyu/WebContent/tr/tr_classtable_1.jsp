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
	$(".selectbox").select2();//重新加载select的样式
	
	
	
	


	function LoadData(result_obj){
		//清空表格数据
		 $("#content").empty();
		
		var table = result_obj.table;
		var day = result_obj.day;
		var class_time = result_obj.class_time;
		
		//判断学生列表是否为空
			if(table != null){
				
				//输出课表
				var result = "<table  class='table table-striped table-bordered table-hover' id='dataTables-example'>";
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
						result += "<td class='center' rowspan='7' style='width:1em;vertical-align:middle;' title = '"+result_obj.teacher_phone+"'>"+result_obj.teacher_name+"</td>";
					}
					
					result += "<td class='center' style='text-align:center;'>"+class_time[k].classtime+"</td>";
					
					for(var j=1; j<=7; j++){
						var key = j+'_'+(k+1);
						if(table[key]!=null){
							result += "<td align='center'  title ='"+table[key].title+"'  style='background-color:"+table[key].color+";'>"+table[key].student+"</td>";
						}else{
							result += "<td align='center'></td>";
						}
						
						
					}
					result += "</tr>";

				}
				result += "</tbody>";
				result += "</table>";
				
			}else{
				
				//老师在该天没有课程，显示空课表
				var result = "<table class='table table-striped table-bordered table-hover' id='dataTables-example'>";
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
						result += "<td class='center' rowspan='7' style='width:1em;vertical-align:middle;' title = '"+result_obj.teacher_phone+"'>"+result_obj.teacher_name+"</td>";
					}
					
					result += "<td class='center' style='text-align:center;'>"+class_time[k].classtime+"</td>";
					
					for(var j=1; j<=7; j++){
						var key = j+'_'+(k+1);
						result += "<td class='trigger' align='center'></td>";	
					}
					result += "</tr>";

				}
				result += "</tbody>";
				result += "</table>";
				
			}	
			
		
		
		$("#content").append(result);//将一张表加入显示列表中
				
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
                url: "tr_search_classtable",
                async: false,
                data: {
                	time: tar_time,
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
                url: "tr_search_classtable",
                async: false,
                data: {
                	time: $("input[name=time]").val(),
                },
                success: function (data, status) {
                	
                		var result_obj = $.parseJSON(data)[0];
                		LoadData(result_obj);
                		      
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
	                             名师课程时间安排
	                             <nav class="navbar navbar-default">
									<div class="container-fluid">
									    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
									      <form class="navbar-form navbar-left" role="search" name = "search_classtable">
									        <div class="form-group">
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
										<!-- 输出课程表 -->
										<table  class="table table-striped table-bordered table-hover" id="dataTables-example">
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
													          <td class="center" rowspan="7" style="width:1em;vertical-align:middle;" title = "${teacher.phone}">${teacher.name}</td>
													</s:if>
													<tr class="odd gradeX">
		                                    	 		<td class="center" style="text-align:center;"><s:property value="#time.classtime"/></td>
												     	<s:iterator value="new int[7]" status="j">
												     		<s:set name="map_key" value="#j.count+'_'+#i.count"/>
					                                    	<td data-dialog="somedialog" status ="${table.get(map_key).status}" class="trigger"  align="center"  title ="${table.get(map_key).title}"  style="background-color:${table.get(map_key).color};">${table.get(map_key).student}</td>
												     	</s:iterator>
											     	</tr>
											     </s:iterator>
		                                    </tbody>
	                                	</table> 
								
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
