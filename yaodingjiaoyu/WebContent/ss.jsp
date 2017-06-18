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
    <title>欢迎您，学生家长:<s:property value = "stuff.name" /></title>
	<!-- Bootstrap Styles-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FontAwesome Styles-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- Custom Styles-->
    <link href="assets/css/custom-styles.css" rel="stylesheet" />

   
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="javascript:void(0);"><strong><i class="icon fa fa-book"></i> 耀鼎教育UPC系统</strong></a>
				<div id="sideNav" href="">
					<i class="fa fa-bars icon"></i> 
				</div>
            </div>

            <ul class="nav navbar-top-links navbar-right">
               
               
                
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> 用户简介</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> 设置</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> 退出登录</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
        </nav>
        <!--/. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">
            	<li>
                    <a href="javascript:void(0);"><i class="fa fa-sitemap"></i>线索客户管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a id = "look_examples">线索客户查询</a>
                        </li>
                        
                        <li>
                            <a id = "look_cc_content">跟踪记录查看</a>
                        </li>
                        <li>
                            <a id = "add_examples">添加线索客户</a>
                        </li>

                    </ul>
                </li>
            
                <li>
                    <a href="javascript:void(0);"><i class="fa fa-sitemap"></i>学员管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a id = "look_stu">学员信息查看</a>
                        </li>

                        <li>
                            <a id = "look_class_content">回访查看</a>
                        </li>

                    </ul>
                </li>

                <li>
                    <a href="javascript:void(0);"><i class="fa fa-sitemap"></i> 合同管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a id = "lookHetong" href="javascript:void(0);">查看合同</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="javascript:void(0);"><i class="fa fa-sitemap"></i> 课表管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a id="lookClasstable"  href="javascript:void(0);">查看课表</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0);"><i class="fa fa-sitemap"></i> 职工管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="javascript:void(0);">员工调动</a>
                        </li>
                        <li>
                            <a href="javascript:void(0);">信息查看</a>
                        </li>
                        <li>
                            <a href="javascript:void(0);">账号创建</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0);"><i class="fa fa-sitemap"></i> 基本管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="javascript:void(0);">添加校区</a>
                        </li>
                        <li>
                            <a href="javascript:void(0);">添加岗位</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="javascript:void(0);"><i class="fa fa-edit"></i> 业绩报表 </a>
                </li>

     

            </ul>

        </div>

    </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper">
		        
        </div>
    </div><!-- div wrapper end -->
    
    <script src="assets/js/jquery-3.0.0.min.js"></script>
      <!-- Bootstrap Js -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- Metis Menu Js -->
    <script src="assets/js/jquery.metisMenu.js"></script>
      <!-- Custom Js -->
    <script src="assets/js/custom-scripts.js"></script>
    <script type="text/javascript">
	    $(document).ready(function(){ 
	    	//隐藏左侧导航Js事件
	    	$("#sideNav").click(function(){
				if($(this).hasClass('closed')){
					$('.navbar-side').animate({left: '0px'});
					$(this).removeClass('closed');
					$('#page-wrapper').animate({'margin-left' : '260px'});
					
				}
				else{
				    $(this).addClass('closed');
					$('.navbar-side').animate({left: '-260px'});
					$('#page-wrapper').animate({'margin-left' : '0px'}); 
				}
			});
	    	
	    	
	        
	        $("#look_stu").click(function()
            {
                //do something
                htmlobj=$.ajax({url:"admin_stuinfo?id=1",async:false});
                $("#page-wrapper").html(htmlobj.responseText);
                return false;
            });

	        $("#look_class_content").click(function()
            {
                //do something
                htmlobj=$.ajax({url:"admin_stuinfo?id=2",async:false});
                $("#page-wrapper").html(htmlobj.responseText);
                return false;
            });
	        
	        $("#look_examples").click(function()
            {
                //do something
                htmlobj=$.ajax({url:"admin_examples?id=1",async:false});
                $("#page-wrapper").html(htmlobj.responseText);
                return false;
            });
	       
	        $("#look_cc_content").click(function()
            {
                //do something
                htmlobj=$.ajax({url:"admin_examples?id=2",async:false});
                $("#page-wrapper").html(htmlobj.responseText);
                return false;
            });
	        $("#add_examples").click(function()
            {
                //do something
                htmlobj=$.ajax({url:"admin_examples?id=3",async:false});
                $("#page-wrapper").html(htmlobj.responseText);
                return false;
            });
            $("#lookHetong").click(function()
                    {
                        //do something
                        htmlobj=$.ajax({url:"admin_hetong?id=1",async:false});
                        $("#page-wrapper").html(htmlobj.responseText);
                        return false;
			});
			$("#lookClasstable").click(function()
                    {
                        //do something
                        htmlobj=$.ajax({url:"admin_classtable_1?id=1",async:false});
                        $("#page-wrapper").html(htmlobj.responseText);
                        return false;
			});
	        
	        
	        
	        
	        
	        
	        
	        //默认加载例子库
	        htmlobj=$.ajax({url:"admin_examples?id=1",async:false});
            $("#page-wrapper").html(htmlobj.responseText);
	        
	    }); 
        
    </script>
    
    
   
</body>
</html>