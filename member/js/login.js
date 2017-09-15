$(document).ready(function () {
	var URLHead = '';

    var $form1 = $('form:first');
    var $user1 = $form1.find('.username');
    var $passwd1 = $form1.find('.password');
    var $signIn1 = $form1.find('#btnSignIn');
    var $signUp1 = $form1.find('#btnSignUp');//第一个注册按钮
  //  var $forgetPwd = $form1.find('.forgetPwd');
    
    var $form2 = $('form:last');
    var $user2 = $form2.find('.inUserName');
    var $passwd2_1 = $form2.find('.inPwd1');
    var $passwd2_2 = $form2.find('.inPwd2');
    var $signUp2 = $form2.find('#btnSignUp2');//第二个注册按钮
    var $signRe = $form2.find('#btnReset');
    
    
    var $pwdFind = $('#btnFind');
    var $pwdUser = $('#pwdUser');
    
    // enter键登录   
    $(document).keyup(function(event){ 
        if(event.keyCode ==13){ 
          $(':submit').trigger("click"); 
        } 
    });
    
    /*登录事件*/
    $signIn1.on('click', function (event) {
    	regName = /^[\u4e00-\u9fa5]{2,4}$/;//姓名
        event.preventDefault();
        if (!$user1.val() || !$passwd1.val()) {
        	$('input').css('border','1px solid red');
      		$('input').addClass('error');
      		$.notice('登录提示：', '信息未填写完整！', undefined, 200, 70);
      		return;	
        }
        else if(!regName.test($user1.val())){
        	$('input:first').css('border','1px solid red');
        	$('input:first').addClass('error');
        	$.notice('登录提示：', '账号填写错误！', undefined, 200, 70);
        }
        else if($passwd1.val().length < 6){
        	$('input:last').css('border','1px solid red');
        	$('input:last').addClass('error');
        	$.notice('登录提示：', '密码长度不能小于6位！', undefined, 200, 70);
        }
        
        setTimeout(function () {
			window.location.href = '../file/Sign.html';
		}, 1000)
        
        var ajaxArgs = {
        	realname : $user1.val(),
        	password : $passwd1.val()
        }
        //接口对接
        $.ajax({
            type: "POST",
            url: URLHead + "api/member/login",
            beforeSend: $.notice('提示！', '正在登录...', function () {
            }),
            
            data: ajaxArgs,
            success: function(data){
                if(typeof data === 'string') {
                    data = JSON.parse(data);
                }
                if(data.code == 200) {
                    
                    $.notice("提示！", "登录成功，正在跳转...");
                    setTimeout(function () {
			            window.location.href = '../file/Sign.html';
			        }, 1000)
        
                } else {
                    $.notice("提示！", "登录失败，用户或密码错误，请重新登录...");
                }
            }
        })
    });
    
    /*注册事件*/
    $signUp2.on('click', function (event) {
    	regName = /^[\u4e00-\u9fa5]{2,4}$/;//姓名
        event.preventDefault();
        if (!$passwd2_1.val() || !$passwd2_1.val() || !$user2.val()) {
        	$('input').css('border','1px solid red');
      		$('input').addClass('error');
      		$.notice('登录提示：', '信息未填写完整！', undefined, 200, 70);
      		return;	
        }
        else if(!regName.test($user2.val())){
        	$('input:eq(2)').css('border','1px solid red');
        	$('input:eq(2)').addClass('error');
        	$.notice('登录提示：', '账号格式错误，请输入中文名！', undefined, 200, 70);
        }
        else if($passwd2_1.val().length < 6){
        	$('input:eq(3)').css('border','1px solid red');
        	$('input:eq(3)').addClass('error');
        	$.notice('登录提示：', '密码长度不能小于6位！', undefined, 200, 70);
        }
        else if($passwd2_2.val().length < 6){
        	$('input:eq(4)').css('border','1px solid red');
        	$('input:eq(4)').addClass('error');
        	$.notice('登录提示：', '密码长度不能小于6位！', undefined, 200, 70);
        }
        else if($passwd2_2.val() != $passwd2_1.val()){
        	$('input:gt(2)').css('border','1px solid red');
        	$('input:gt(2)').addClass('error');
        	$.notice('登录提示：', '两次密码输入不一致', undefined, 200, 70);
        }
//      else{
//      	
//      }
        //接口对接
//      $.ajax({
//          type: "POST",
//          url: utils.URLHead + "/users/login",
//          beforeSend: $.notice('提示！', '正在登录...', function () {
//              utils.loading($('.jq-notice-context'));
//          }),
//          data: ajaxArgs,
//          success: function(data){
//              if(typeof data === 'string') {
//                  data = JSON.parse(data);
//              }
//              if(data.code == 200) {
//                  var admin = data.body;
//                  // 设置登录状态
//                  utils.setLoginState(admin);
//                  // 登录用户信息保存到cookie中
//                  
//                  $.notice("提示！", "登录成功，正在跳转...");
//                  utils.jumpUrl('../../user/setting/page.html', 2000);
//      
//              } else {
//                  $.notice("提示！", "登录失败，用户或密码错误，请重新登录...");
//              }
//          }
//      })
    });
    
    /*找回密码事件*/
    $pwdFind.on('click', function (event) {
    	regName = /^[\u4e00-\u9fa5]{2,4}$/;//姓名
        event.preventDefault();
        if (!$pwdUser.val()) {
        	$('input').css('border','1px solid red');
      		$('input').addClass('error');
      		$.notice('登录提示：', '信息未填写完整！', undefined, 200, 70);
      		return;	
        }
        else if(!regName.test($pwdUser.val())){
        	$('input').css('border','1px solid red');
        	$('input').addClass('error');
        	$.notice('登录提示：', '账号格式错误，请输入中文名！', undefined, 200, 70);
        }
        
        var ajaxArgs = {
            realname: $pwdUser.val(),
        };
        //  接口对接
        $.ajax({
            type: "POST",
            url: URLHead + "/api/member/findPassword",
            beforeSend: $.notice('提示！', '请稍等......', function () {
            }),
            data: ajaxArgs,
            success: function(data){
                if(typeof data === 'string') {
                    data = JSON.parse(data);
                }
                if(code == 200) {      
                    $.notice("提示！", "找回密码的邮件已发送，请进入邮件修改密码...");
        
                } else {
                    $.notice("警告！", "出现错误，请重试...");
                }
            }
        })
    });
    $signUp1.on('click',function(){
    	$('.signOut').animate({
            left: "-85%"
        },100);
        $('.signIn').animate({
            right: "10%"
        },100);
    })
    
    $signRe.on('click',function(){
    	$('.signOut').animate({
            left: "10%"
        },100);
        $('.signIn').animate({
            right: "-85%"
        },100);
    })
    
    
    $('#close').on('click', function (event) {
    	$('input').css('border','1px solid #bfbfbf');
    	$('input').removeClass('error');
    });
    
})