<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta content="" name="description" />
<meta content="webthemez" name="author" />
<title>欢迎您，班主任 <s:property value="stuff.name" />
</title>
<!-- Bootstrap Styles-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- Custom Styles-->
<link href="assets/css/custom-styles.css" rel="stylesheet" />

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



</head>
<body>
	<!-- 弹框的内容 -->
	<div id="changedialog" class="dialog" style="z-index: 6">
		<div class="dialog__overlay"></div>
		<div class="dialog__content">
			<strong>修改密码</strong>
			<div>
				<table class="table table-striped table-bordered table-hover"
					id="savedata" stuff="" pram="">
					<tr class="odd gradeX">
						<td>密码:</td>
						<td><input class="form-control" name="old_pass"
							type="password" maxlength="15"></td>
					</tr>
					<tr class="odd gradeX">
						<td>新的密码:</td>
						<td><input class="form-control" name="new_pass"
							type="password" maxlength="15"></td>
					</tr>
					<tr class="odd gradeX">
						<td colspan="2" align="center"><a name="close"
							href="javascript:void(0);" class="btn btn-warning action"
							data-dialog-close>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
								id="close">关闭</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</a> <a name="submit_change" href="javascript:void(0);"
							class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提交&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<!-- ／弹框的内容 -->

	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="javascript:void(0);"><strong><i
					class="icon fa fa-book"></i> 耀鼎教育UPC系统</strong></a>
			<div id="sideNav" href="">
				<i class="fa fa-bars icon"></i>
			</div>
		</div>

		<ul class="nav navbar-top-links navbar-right">



			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#" aria-expanded="false"> <i
					class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="javascript:void(0);" id="userInfo"><i
							class="fa fa-user fa-fw"></i> 个人信息</a></li>
					<li><a href="javascript:void(0);" id="change_pwd"><i
							class="fa fa-gear fa-fw"></i> 修改密码</a></li>
					<li class="divider"></li>
					<li><a href="javascript:void(0);" id="logout"><i
							class="fa fa-sign-out fa-fw"></i> 退出登录</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		</nav>
		<!--/. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">
				<li><a href="javascript:void(0);"><i class="fa fa-sitemap"></i>线索客户管理<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a id="add_examples">添加线索客户</a></li>
					</ul>
				</li>
			
				<li><a href="#"><i class="fa fa-sitemap"></i>学员管理<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="＃" id="lookstu">学员信息查看</a></li>

						<li><a href="＃" id="lookContent">回访查看</a></li>

					</ul>
				</li>

				<li><a href="#"><i class="fa fa-sitemap"></i> 合同管理<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="＃" id="lookHetong">查看合同</a></li>
					</ul></li>

				<li><a href="#"><i class="fa fa-sitemap"></i> 课表管理<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="＃" id="looktable">查看课表</a></li>
						<li><a href="＃" id="unsign">统计</a></li>
					</ul></li>

				<li><a href="javascript:void(0);"><i class="fa fa-edit"></i>
						业绩报表<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a id= "cr_achieve" href="javascript:void(0);">班主任业绩</a></li>
					</ul>	
				</li>
				
				<li>
					<a href="javascript:void(0);">
						<i class="fa fa-edit"></i>
						系统公告
						<span class="fa arrow">
							<s:if test="(#application.OPEN_REMIND)">
								<div style=" width:16px; height:16px; background-color:#F00; border-radius:8px;float:left;">
         							<span style="height:50px; line-height:16px; display:block; color:#FFF; text-align:center">${application.REMIND_NUM}</span>
 								</div>
							</s:if>
						</span>
					</a>
					<ul class="nav nav-second-level">
						<li><a id= "version" href="javascript:void(0);">系统消息</a></li>
					</ul>	
				</li>

			</ul>

		</div>

		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper"></div>
	</div>
	<!-- div wrapper end -->

	<script src="assets/js/jquery-3.0.0.min.js"></script>
	<!-- Bootstrap Js -->
	<script src="assets/js/bootstrap.min.js"></script>
	<!-- Metis Menu Js -->
	<script src="assets/js/jquery.metisMenu.js"></script>
	<!-- Custom Js -->
	<script src="assets/js/custom-scripts.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			$("#sideNav").click(function() {
				if ($(this).hasClass('closed')) {
					$('.navbar-side').animate({
						left : '0px'
					});
					$(this).removeClass('closed');
					$('#page-wrapper').animate({
						'margin-left' : '260px'
					});

				} else {
					$(this).addClass('closed');
					$('.navbar-side').animate({
						left : '-260px'
					});
					$('#page-wrapper').animate({
						'margin-left' : '0px'
					});
				}
			});


			//弹框的提交按钮
			$("a[name=submit_change]").click(function(){
				
				if(($('input[name=old_pass]').val()=="")||($('input[name=new_pass]').val()=="")){
					//密码为空不能提交
					alert("密码不能为空!");
				}
				else{
					$.ajax({
			            type: "post",
			            url: "change_password",
			            async: false,
			            data: {
			            	old_pass: $("input[name=old_pass]").val(),
			            	new_pass: $("input[name=new_pass]").val(),
			            },
			            success: function (data, status) {//再此判断数据是否非空
			            	
			            		var result_obj = $.parseJSON(data)[0];
			            		
			           			//关闭弹框
			           			$("#close").click();
			           			 alert(result_obj.message);
			            },
			            error: function () { 
			                	alert("修改密码失败");
			            	}
			            
			        });
				}
				
			});

			//给所有没有完结课程添加点击事件
			function BindChangeBtnClick() {

				var changedialog = document.getElementById("changedialog");
				var dlg = new DialogFx(changedialog);

				var $change_pwd = $("#change_pwd"); //jQuery 对象
				var change_pwd = $change_pwd[0]; //DOM 对象
				change_pwd.removeEventListener('click', dlg.toggle.bind(dlg));
				change_pwd.addEventListener('click', dlg.toggle.bind(dlg));
			}

			BindChangeBtnClick();

			$("#add_examples").click(function() {
				//do something
				htmlobj = $.ajax({
					url : "cc_examples_3",
					async : false
				});
				$("#page-wrapper").html(htmlobj.responseText);
				return false;
			});

			$("#version").click(function() {
				//do something
				htmlobj = $.ajax({
					url : "version",
					async : false
				});
				$("#page-wrapper").html(htmlobj.responseText);
				return false;
			});

			$("#cr_achieve").click(function() {
				//do something
				htmlobj = $.ajax({
					url : "achieve_manager?id=2",
					async : false
				});
				$("#page-wrapper").html(htmlobj.responseText);
				return false;
			});


			$("#userInfo").click(function() {
				//do something
				htmlobj = $.ajax({
					url : "user_info",
					async : false
				});
				$("#page-wrapper").html(htmlobj.responseText);
				return false;
			});

			$("#logout").click(function() {
				//do something
				htmlobj = $.ajax({
					url : "Logout",
					async : false
				});
				window.location = "login.jsp";
			});

			//默认加载学员信息
			htmlobj = $.ajax({
				url : "cr_stuinfo?id=1",
				async : false
			});
			$("#page-wrapper").html(htmlobj.responseText);

			$("#lookstu").click(function() {
				//do something
				htmlobj = $.ajax({
					url : "cr_stuinfo?id=1",
					async : false
				});
				$("#page-wrapper").html(htmlobj.responseText);
				return false;
			});
			$("#lookContent").click(function() {
				//do something
				htmlobj = $.ajax({
					url : "cr_stuinfo?id=2",
					async : false
				});
				$("#page-wrapper").html(htmlobj.responseText);
				return false;
			});

			$("#lookHetong").click(function() {
				//do something
				htmlobj = $.ajax({
					url : "cr_hetong?id=1",
					async : false
				});
				$("#page-wrapper").html(htmlobj.responseText);
				return false;
			});

			$("#looktable").click(function() {
				//do something
				htmlobj = $.ajax({
					url : "cr_classtable?id=1",
					async : false
				});
				$("#page-wrapper").html(htmlobj.responseText);
				return false;
			});
			$("#unsign").click(function() {
				//do something
				htmlobj = $.ajax({
					url : "cr_classtable?id=2",
					async : false
				});
				$("#page-wrapper").html(htmlobj.responseText);
				return false;
			});

		});
	</script>



</body>
</html>