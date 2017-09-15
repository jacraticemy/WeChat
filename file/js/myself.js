$(document).ready(function () {
	$('#self-logoff').on('click',function(){
		$.notice('提示：', '确认退出账号吗？', undefined, 200, 70);
	})
	
	$('#logoff-sure').on('click',function(){
		window.location.href = '../member/login.html';
	})
	
	$('#logoff-close').on('click',function(){
		$('.jq-notice-container').addClass('hidden');
	})
})