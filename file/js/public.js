$(document).ready(function () {
	
	$('.Sign').on('click',function(){
		$('#footer-sign').attr('src','img/Sign1.png')
		$('#footer-meeting').attr('src','img/meeting.png')
		$('#footer-youself').attr('src','img/me.png')
		$('.img-text:eq(0)').css('color','#1296db')
		$('.img-text:eq(1)').css('color','rgba(0,0,0,.4)')
		$('.img-text:eq(2)').css('color','rgba(0,0,0,.4)')
	})
	$('.Meeting').on('click',function(){
		$('#footer-sign').attr('src','img/Sign.png')
		$('#footer-meeting').attr('src','img/meeting1.png')
		$('#footer-youself').attr('src','img/me.png')
		$('.img-text:eq(0)').css('color','rgba(0,0,0,.4)')
		$('.img-text:eq(1)').css('color','#1296db')
		$('.img-text:eq(2)').css('color','rgba(0,0,0,.4)')
	})
	$('.yourSelf').on('click',function(){
		$('#footer-sign').attr('src','img/Sign.png')
		$('#footer-meeting').attr('src','img/meeting.png')
		$('#footer-youself').attr('src','img/me1.png')
		$('.img-text:eq(0)').css('color','rgba(0,0,0,.4)')
		$('.img-text:eq(1)').css('color','rgba(0,0,0,.4)')
		$('.img-text:eq(2)').css('color','#1296db')
	})
})