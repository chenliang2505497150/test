
jQuery(document).ready(function() {

    $('.page-container form').submit(function(){
        var username = $(this).find('.username').val();
        var password = $(this).find('.password').val();
        if(username == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '27px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.username').focus();
            });
            return false;
        }
		
        if(password == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '96px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.password').focus();
            });
            return false;
        }

        // 发送Ajax请求
        $.ajax({
                type: "post",
                url: "Login",
                async: false,
                data: { 
                    username: $("input[name='username']").val(),
                    password: $("input[name='password']").val(),
                },
                success: function (data, status) {
                    var obj = $.parseJSON(data)[0];
                    if (obj.status!=200) {
                    	alert(obj.message);
                    }
                },
                error: function () { alert("用户名或密码错误") }
                
            });
        return false;
    });

    $('.page-container form .username, .page-container form .password').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });

});
