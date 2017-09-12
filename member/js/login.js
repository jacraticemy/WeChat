$(document).ready(function () {
    var $form = $('form');
    var $user = $form.find('.username');
    var $passwd = $form.find('.password');
    var $submit = $form.find('.login-submit');
    // enter键登录   
    $(document).keyup(function(event){ 
        if(event.keyCode ==13){ 
          $submit.trigger("click"); 
        } 
    });
    
    $submit.on('click', function (event) {
    	regName = /^[\u4e00-\u9fa5]{2,4}$/;//姓名
        event.preventDefault();
        if (!$user.val() || !$passwd.val()) {
        	$('input').css('border','1px solid red');
      		$('input').addClass('error');
      		$.notice('登录提示：', '信息未填写完整！', undefined, 300, 150);
      		return;	
        }
        else if(!regName.test($user.val())){
        	$('input:first').css('border','1px solid red');
        	$.notice('登录提示：', '账号填写错误！', undefined, 300, 150);
        }
        else if($passwd.val().length < 6){
        	$('input:last').css('border','1px solid red');
        	$('input').addClass('error');
        	$.notice('登录提示：', '密码长度不能小于6位！', undefined, 300, 150);
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
    
     $('#close').on('click', function (event) {
    	$('input').css('border','1px solid #bfbfbf');
    })
});