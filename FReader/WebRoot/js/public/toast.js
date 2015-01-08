/**
 * 仿android的toast，同时增加loading功能
 */
var Toast = (function(){
	var totalHtml = document.createElement("div");
	totalHtml.className="toast-wrap toast-wrap-next";
	totalHtml.innerHTML = "<div><div class='toast'></div></div>";
	
	var toastWrap, toast;
	if(toastWrap==null){
		document.body.appendChild(totalHtml);
		toastWrap = document.querySelector(".toast-wrap");
		toast = document.querySelector(".toast");
	}
	function show(text, callback){
		
		if(typeof text == "string"){
			toast.addEventListener("webkitAnimationEnd", function(){
				toastWrap.className = "toast-wrap toast-wrap-next";
				toast.className = 'toast';
				callback && callback();
			},false);
			toast.addEventListener("animationend", function(){
				toastWrap.className = "toast-wrap toast-wrap-next";
				toast.className = 'toast';
				callback && callback();
			},false);
			toastWrap.className = "toast-wrap toast-wrap-active";
			toast.className = "toast toast-animation";
			toast.innerHTML = text;
		}
	}
	/**
	 * 
	 */
	function load(){
		var loadImg = '<div class="load-img-wrap"><div class="circle"></div><div class="circle1"></div></div>';
		toastWrap.className = "toast-wrap toast-wrap-active";
		toast.className = "toast toast-in-transition";
		toast.innerHTML = loadImg;
	}
	
	function hide(callback){
		var toastClass = toast.classList,
			wrapToastClass = toastWrap.classList;
		if(!wrapToastClass.contains("toast-wrap-active")) return;
		if(!hide.hasTransitionEvent){
			toast.addEventListener("webkitTransitionEnd", function(){
				callback && callback();
				if(toastClass.contains("toast-in-transition")){
					return;
				}
				toastWrap.className = "toast-wrap toast-wrap-next";
				toast.className = 'toast';
			},false);
			hide.hasTransitionEvent = true;
		}
		toast.className = "toast toast-out-transition";
	}
	
	return {
		show:show,
		load:load,
		hide:hide
	}
})();
