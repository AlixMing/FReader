document.addEventListener("DOMContentLoaded", function() {
	window.mySwipe = new Swipe(document.getElementById('slider'), {
		speed: 400,
		auto: 2000,
		continuous: true,
		disableScroll: false,
		stopPropagation: false,
		callback: function(index, elem) {},
		transitionEnd: function(index, elem) {}
	});
	
	
	document.querySelector("#menu-wrap").addEventListener('touchstart',function(){
		
	},false);
	
//	$('.confirm-dishes-btn').tap(function(){
//		Toast.load();
//		setTimeout(function(){
//			Toast.hide();
//		},2000);
//	})
	
//	$.ajax({
//		url: '/menu!getAllMenuType',
//		dataType: 'json',
//		success:function(data, status, jqXHR) {
//			console.log(JSON.stringify(data, null, 4));
//		}
//	})
})
