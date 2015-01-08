/**
 * 依赖Toast,zepto
 * @param {Object} fromPage		父页面
 * @param {Object} loding	loading中的回调
 * @param {Object} after
 * @param {Object} speed, loading动画时间，为负数时表示不使用loading动画
 */


$(function(){
	var Module = (function(){
		
		var modules = {};
		
		function  init(){
			
		}
		
		function notify(){
			if(arguments.length==1){
				arguments[0].update();
				return;
			}
			for(var i=0; i<arguments.length-1; i++){
				arguments[i].update(arguments[arguments.length-1]);
			}
		}
		function notifyAll(){
			
		}
		function update(args){
			
		}
		/**
		 * 
		 * @param {Object} toPage	pageid
		 * @param {Object} loadingCallback loading动画开始后立即执行回调
		 * @param {Object} afterCallback	页面切换完后执行回调
		 */
		function pageNext(toPage, loadingCallback, afterCallback){
			Toast.load();
			var  that = this;
			setTimeout(function(){
				loadingCallback && loadingCallback();
				that.modules[toPage] && (that.modules[toPage].from = that.moduleName);
				var from = $('#'+that.moduleName), to = $('#'+toPage);
				from.removeClass('page-active').addClass("page-prev page-out");
				to.removeClass('page-prev page-active').addClass("page-next");
				//debugger;
				$('.page-wrap').one("transitionend", '.page-out', function(){
					$(this).removeClass("page-out");
					afterCallback && afterCallback();
					Toast.hide();
				})
				$('.page-wrap').one("transitionend", '.page-in',function(){
					$(this).removeClass("page-in");
					afterCallback && afterCallback();
					Toast.hide();
				})
				to.height();		//强制回流，使下面的语句顺利执行
				setTimeout(function(){
					to.removeClass("page-next").addClass("page-active page-in");
				},0);
			},500)
		}
		function pagePrev(toPage, loadingCallback, afterCallback){
			Toast.load();
			var  that = this;
			setTimeout(function(){
				loadingCallback && loadingCallback();
				var from = $('#'+that.moduleName), to = $('#'+toPage);
				from.removeClass('page-active').addClass("page-next page-out");
				to.removeClass('page-next page-active').addClass("page-prev");
				$('.page-wrap').off().on("transitionend", '.page-out', function(){
					$(this).removeClass("page-out");
					afterCallback && afterCallback();
					Toast.hide();
				})
				$('.page-wrap').off().on("transitionend", '.page-in',function(){
					$(this).removeClass("page-in");
					afterCallback && afterCallback();
					Toast.hide();
				})
				setTimeout(function(){
					to.removeClass("page-prev").addClass("page-active page-in");
				},0);
			},500)
		}
		Object.extend = function(parent, obj){
			function TmpObj(){
				for(var i in obj){
					if(obj.hasOwnProperty(i)){
						this[i] = obj[i];
					}
				}	
			}
			TmpObj.prototype = parent;
			var o = new TmpObj();
			return o;
		}
		return {
			moduleName:"",		//必须赋值，且moduleName与页面id要一致
			from:"",		//从哪个页面跳转过来
			modules: modules,		//所有模块在init时就应该保存到了modules中了
			init:init,
			notify:notify,
			notifyAll:notifyAll,
			update:update,		//各个模块根据数据更新页面
			pageNext:pageNext,
			pagePrev:pagePrev
		}
	})();
	
	/**
	 * 首页模块
	 */
	var indexModule = Object.extend(Module, {
		moduleName: 'index',
		init:function(){
			var that = this;
			that.modules[that.moduleName] = that;
			
			$('.order-wrap').off().tap(function(){
				that.pageNext("dishes");
			})
			$('.my-order-wrap').off().tap(function(){
				$.ajax({
					type:'get',
					url: '/DinResSys2/user!getLoginStatus',
					dataType:'json',
					success: function(data, status, jqXHR){
						if(parseInt(data.status)==1){
							that.pageNext('my-center');			
						}else{
							that.pageNext('login');
						}
					}
				})
			})
			
			
			$.ajax({
				type:"get",
				url: '/DinResSys2/menu!getAllMenuType',
				dataType: 'json',
				success:function(data, status, jqXHR) {
					var n = data.length;
					var uls = "", lis = "";
					for(var i = 0, k = Math.ceil(n/4); i<k; i++){
						uls+="<ul>";
						lis="";
						for(var j = i*4; j<i*4+4 && j<n ; j++){
							//uls+='<li>'+data[j]+'</li>';
							lis = lis + "<li class='"+(Math.random()>0.5?"type-lighthigh":"type-yellowhigh")+"'>"+data[j]+"</li>";
						}
						uls+=lis+"</ul>";
					}
					uls+='<span class="triangle-border"><span class="triangle"></span></span>';
					$('#index .order-type').html(uls);
				}
			})
			$.ajax({
				type:"get",
				url:"/DinResSys2/menu!getActivityMenuImg",
				async:true,
				dataType:'json',
				success:function(data, status, jqXHR){
					var $img = $('.swipe-wrap img');
					
					data.forEach(function(ele, index){
						$img[index].src = ele;
					})
				}
			});
			
			
		},
		update:function(){
			
		}
	})
	indexModule.init();
	indexModule.init()
	
	/**
	 * 菜单选择模块
	 */
	var dishesModule = Object.extend(Module, {
		moduleName:"dishes",
		init:function(){
			var that = this;
			that.modules[that.moduleName] = that;
			
			that.allMenuObj = {};	//独有属性，allMenu，格式如{1(id):{id:1,item:'adsf',price:11, ....},id:{....}}
			that.allMenuArray = []; //独有属性，allMenu，格式如[{id:1,item:'adsf',price:11, ....},{....}]
			that.menuType = [];	//独有属性，menuType，格式如[{type:'优惠',num:3(菜式数量)},{...}]
			that.orders = {};	//独有属性，orders为当前所点菜单对象，格式如{id:buyNum,id:buyNum...}
			that.totalPrice = 0; 	//dishesModule独有属性totalPrice
			Object.observe(that, function(changes){		//对其进行监控
				changes.forEach(function(change) {
			        console.log(change.type, change.name, change.oldValue);
					if(change.name=="totalPrice"){
						$('#dishes .total-price, #shop-cart .total-price, #order-info .total-price').html("￥"+that.totalPrice);
					}
			    });
			})
			
			initSideType();
			menuScroll = new  IScroll("#menu-wrap",{
				bounce: true,
				probeType: 2,
				mouseWheel: true
			})
			sideScroll = new IScroll('#side-type-wrap', {
				bounce: true,
				mouseWheel: true
			});
			
			menuScroll.on('scrollEnd',function(){
				var y = 0;
				for(var i=0; i<that.menuType.length-1; i++){
					y = Math.abs(this.y);
					if(y>=(that.menuType[i].start-1)*81 && y<(that.menuType[i+1].start-1)*81){
						$('#side-type-wrap li').eq(i).addClass('type-active').siblings().removeClass('type-active');
						return ;
					}
				}
			})
			
			function initSideType(){
				//初始化侧边栏
				$.ajax({
					type:"get",
					url: '/DinResSys2/menu!getAllMenuType',
					dataType: 'json',
					success:function(data, status, jqXHR) {
						var n = data.length,
							lis = '';
						data.forEach(function(ele, index){
							that.menuType.push({type:ele, num: 0, start: 0});
							if(index == 0){
								lis += '<li class="type-active">'+ele+'</li>';					
							}else{
								lis += '<li>'+ele+'</li>';					
							}
						})
						initMenu();
						$("#dishes .side-type ul").html(lis);
						setTimeout(function(){
							sideScroll.refresh();
						}, 0);
					}
				})
			}
			
			
			
			function initMenu(){
				//初始化菜式
				$.ajax({
					type:"get",
					url: '/DinResSys2/menu!getAllMenu',
					dataType: 'json',
					success:function(data, status, jqXHR) {
						var menus = data.menus,
							lis = "";
						that.allMenuArray = menus = sortMenu(menus);
						menus.forEach(function(ele, index){
							that.allMenuObj[ele.id] = ele;
							console.log(ele.hasPromotion)
							if(ele.hasPromotion){
								lis += '<li><span class="menu-name">'+ele.item+'<span class="promotion-tip"></span></span><span class="appraise-btn">评论></span><span class="sale-num">月售'+ele.saleNum+'</span><span class="price"><span class="price-btn">￥'+ele.price+'</span></span></li>';
							}else{
								lis += '<li><span class="menu-name">'+ele.item+'</span><span class="appraise-btn">评论></span><span class="sale-num">月售'+ele.saleNum+'</span><span class="price"><span class="price-btn">￥'+ele.price+'</span></span></li>';
							}
						})
						$('#menu-wrap ul').html(lis);
						setTimeout(function () {
					        menuScroll.refresh();
					    }, 0);
					}
				})
			}
			
			//点击侧边栏菜式类型
			$('#dishes .side-type').off().on('tap', 'li', function(){
				var index = $(this).index();
				console.log(that.menuType[index].start);
				menuScroll.scrollToElement(document.querySelector('#menu-wrap li:nth-child('+that.menuType[index].start+')'));
				
				$(this).addClass("type-active").siblings().removeClass('type-active');
				
			})
			
			//点击评论||价格||-（减号）			//未完成状态，没有把当前状态保存到that.orders中
			$('#dishes #menu-wrap').off().on('tap', '.appraise-btn, .price-btn, .delete-num', function(){
				var classes = this.classList;
				var index;
				if(classes.contains('appraise-btn')){
					index = $(this).parent().index();
					that.pageNext('appraise',function(){
						that.notify(that.modules['appraise'],{index:index, id:that.allMenuArray[index].id, item:that.allMenuArray[index].item})
					});
				}else if(classes.contains('price-btn')){
					that.totalPrice += parseInt($(this).html().substring(1));
					var $next = $(this).next();
					if($next.is('.price-num')){
						$next.html(parseInt($next.html())+1);
					}else{
						var html = '<span class="price-num">1</span><span class="delete-num">一</span>';
						$(this).parent().append($(html));
					}
					
					index = $(this).parent().parent().index();
					var	id = that.allMenuArray[index].id;
					that.orders[id]=!!that.orders[id]?++that.orders[id]:1;
					console.log(that.orders);
				}else if(classes.contains('delete-num')){
					var $prev = $(this).prev();
					var currentNum = parseInt($prev.html())-1;
					that.totalPrice -= parseInt($(this).parent().find('.price-btn').html().substring(1));				
					index = $(this).parent().parent().index();
					if(currentNum>0){
						$prev.html(currentNum);	
					}else{
						$prev.remove();
						$(this).remove();
					}
					var	id = that.allMenuArray[index].id;
					--that.orders[id]==0? (delete that.orders[id]):'';
					console.log(that.orders);
				}
			})
			
			//返回 
			$('#dishes .nav-back').off().tap(function(){
				that.pagePrev('index')
			})
			
			//去结算
			$('#dishes .confirm-dishes-btn').off().tap(function(){
				$.ajax({
					type:'get',
					url: '/DinResSys2/user!getLoginStatus',
					dataType:'json',
					success: function(data, status, jqXHR){
						if(parseInt(data.status)==1){
							if(that.totalPrice == 0){
								Toast.show('请先点餐')
								return;
							}
							that.pageNext('shop-cart',function(){
								that.notify(that.modules['shop-cart'],{orders:that.orders, menus:that.allMenuObj})
							});
						}else{
							that.pageNext('login');
						}
					}
				})
				
			})
			
			function sortMenu(menus){
				var tmp = [];
				that.menuType.forEach(function(typeObj, i){
					menus.forEach(function(menu, j){
						if(typeObj.type==menu.type){
							tmp.push(menu);
							that.menuType[i].num++;
						}
					})
				})
				
				that.menuType.forEach(function(typeObj, i){
					var k;
					
					if(i==0){
						that.menuType[0].start = 1;
					}else{
						k = 0;
						for(var j=0; j<i; j++){
							k += that.menuType[j].num;
						}
						that.menuType[i].start = k+1;
					}
				})
				console.log(that.menuType)
				
				return tmp;
			}
			
		},
		update: function(args){
			
		}
	})
	dishesModule.init();
	
	
	/**
	 * 	购物车模块 
	 */
	var shopCartModule = Object.extend(Module, {
		moduleName:"shop-cart",
		init:function(){
			var that = this;
			that.modules[that.moduleName] = that;
			
			//返回 
			$('#shop-cart .nav-back').off().tap(function(){
				that.pagePrev(that.from);
			})
			
			//确认美食
			$("#shop-cart .confirm-btn").off().tap(function(){
				that.pageNext('order-info');
			})
		},
		update:function(args){
			var orders = args.orders,
				menusObj = args.menus;
			var totalPrice = 0, lis='', price = 0, num = 0, totalNum = 0;
			for(var id in orders){
				if(orders.hasOwnProperty(id)){
					price = menusObj[id].price,
					num = orders[id];
					totalNum += num;
					lis += '<li><span class="menu-name">'+menusObj[id].item+'</span><span class="buy-num">x'+num+'</span><span class="price">￥'+price+'</span></li>';
					totalPrice += price*num;
				}
			}
			this.modules['dishes'].totalPrice = totalPrice;
			$('#shop-cart .total-num').html(totalNum);
			$('#shop-cart .main ul').html(lis);
		}
	})
	shopCartModule.init();
	
	
	/**
	 * 评论模块
	 */
	var appraiseModule = Object.extend(Module, {
		moduleName:"appraise",
		init:function(){
			var that = this;
			that.modules[that.moduleName] = that;
			
			//返回 
			$('#appraise .nav-back').off().tap(function(){
				that.pagePrev(that.from);
			})
		},
		update:function(args){	//必须传入item和id
			$appraise = $('#appraise');
			$appraise.find('.menu-name').html(args.item);
			console.log(args);
			$.ajax({
				type:"get",
				url: '/DinResSys2/appraise!getAppraiseByMenuID',
				data:{
					'menu.id': args.id
				},
				dataType: 'json',
				success:function(data, status, jqXHR) {
					var lis = '',appraises = data.appraises;
					appraises.forEach(function(appraise, i){
						lis += '<li><span class="guest-name">'+appraise.name+'</span><span class="guest-date">&nbsp;&nbsp;&nbsp;&nbsp;'+appraise.date+'</span><p class="guest-msg">'+appraise.msg+'</p></li>';
					})
									
					$appraise.find('.scope').html(data.scope);
					$appraise.find('.appraise-num').html(data.num+'人评价');
					$appraise.find('.percent').each(function(i, ele){
						ele.innerHTML = '&nbsp;&nbsp;' + data.praiseLevels[i];
					})
					$appraise.find('.message ul').html(lis);
					
				}
			})
		}
	})
	appraiseModule.init();
	
	
	/**
	 * 送餐信息模块
	 */
	var orderInfoModule = Object.extend(Module, {
		moduleName:"order-info",
		init:function(){
			var that = this;
			that.modules[that.moduleName] = that;
			
			that.addresses;
			
			$.ajax({
				type:"get",
				url: '/DinResSys2/user!getUserInfo',
				dataType: 'json',
				success:function(data, status, jqXHR) {
					var phone = data.phone;
					that.addresses = data.addresses;
					
					$('#order-info #address').val(that.addresses[0].address);
					$('#order-info #tel').val(phone);
				}
			});
			
			//返回
			$('#order-info .nav-back').off().tap(function(){
				that.pagePrev(that.from);
			})
			
			//向右箭头
			$('#order-info .change-address').off().tap(function(){
				that.pageNext('address-select', function(){
					that.notify(that.modules['address-select'], {addresses:that.addresses});
				});
			})
			
			//点击提交订单
			$('#order-info .commit-order-btn').off().tap(function(){
				var phone = $('#order-info #tel').val().trim(),
					addressID = that.addresses[addressSelectModule.currentAddress].id,
					remark = $('#order-info #remark').val().trim();
				var orders = that.modules['dishes'].orders;
				
				if(phone==''){
					Toast.show('手机号码不能为空');
					return ;
				}
				$.ajax({
					type:"get",
					url: '/DinResSys2/order!commitOrderResult?'+$.param({menus:orders}),
					data:{
						phone: phone,
						addressID: addressID,
						remark: remark
					},
					dataType: 'json',
					success:function(data, status, jqXHR) {
						if(parseInt(data.status)==1){
							Toast.show('订单提交成功',function(){
								that.pageNext('my-center');
								that.notify(that.modules['my-center'], {isRefresh: true});
							})
						}else{
							Toast.show('订单提交失败');
						}
					}
				})
			})
			
			
		},
		update:function(args){
			if(!!args.isRefresh){
				this.init();
				return;
			}
			this.addresses = args.addresses;
			$('#order-info #address').val(args.addresses[args.currentAddress].address);
		}
	})
	orderInfoModule.init();
	
	
	
	/**
	 * 	地址选择模块
	 */
	var addressSelectModule = Object.extend(Module, {
		moduleName:"address-select",
		init:function(){
			var that = this;
			that.modules[that.moduleName] = that;
			
			that.addresses;
			
			that.currentAddress = 0;
			
			//返回
			$('#address-select .nav-back').off().tap(function(){
				that.pagePrev(that.from);
			})
			
			//设置默认和选择当前使用地址
			$('#address-select .address-list').off().on('tap', '.set-default-address, li', function(){
				if($(this).is('.set-default-address')){
					var index = $(this).parent().index();
					
					console.log(that.addresses[index].id)
					$.ajax({
						type:"get",
						url: '/DinResSys2/address!setDefaultAddress',
						dataType: 'json',
						data:{
							'address.id': that.addresses[index].id
						},
						success:function(data, status, jqXHR) {
//							var tmpArr = that.addresses.splice(index, 1);
//							that.addresses.unshift(tmpArr[0]);
							
							if(parseInt(data.status)==1){
								var lis = '';
								that.addresses.forEach(function(ele, i){
									if(index==i){
										that.addresses[i].isDefault = true;
									}else{
										that.addresses[i].isDefault = false;
									}
									
									if(that.currentAddress == i){
										lis += '<li class="current-address">'+ele.address;
										if(that.addresses[i].isDefault){
											lis += '<div class="default-address-tip">默认地址</div></li>';
										}else{
											lis += '<div class="set-default-address">设为默认地址</div></li>';
										}
									}else{
										lis += '<li>'+ele.address;
										if(that.addresses[i].isDefault){
											lis += '<div class="default-address-tip">默认地址</div></li>';
										}else{
											lis += '<div class="set-default-address">设为默认地址</div></li>';
										}
									}
								})
								$('#address-select .address-list').html(lis);
								//that.notify(that.modules['order-info'], {address:that.addresses[index]});
							}else{
								Toast.show('设置异常');
							}
						}
					})
					return ;
				}else if($(this).is('li')){
					var index = $(this).index();
					that.currentAddress = index;
					$(this).addClass('current-address').siblings().removeClass('current-address');
					that.pagePrev('order-info',function(){
						that.notify(that.modules['order-info'], {addresses: that.addresses, currentAddress: that.currentAddress});
					})
					return;
				}
			})
			
			$('.add-address .add-address-btn').off().tap(function(){
				var ad = $('#address-select .new-address').val().trim();
				if(ad==''){
					Toast.show('新增地址不能为空');
					return;
				}else{
					$.ajax({
						type:"get",
						url: '/DinResSys2/address!addAddress',
						dataType: 'json',
						data:{
							'address.ad': ad
						},
						success:function(data, status, jqXHR) {
							if(parseInt(data.status)==1){
								that.addresses.push(data.address);
								that.notify(that, that.addresses);
							}
						}
					})
				}
			})
		},
		update: function(args){		//必须要传入addresses
			if(!!args.isRefresh){
				this.init();
				return;
			}
			if(!args.addresses){
				return;
			}
			var lis = '';
			this.addresses = args.addresses;
			var that = this;
			args.addresses.forEach(function(ele, i){
				if(that.currentAddress == i){
					lis += '<li class="current-address">'+ele.address;
					if(ele.isDefault){
						lis += '<div class="default-address-tip">默认地址</div></li>';
					}else{
						lis += '<div class="set-default-address">设为默认地址</div></li>';
					}
				}else{
					lis += '<li>'+ele.address;
					if(ele.isDefault){
						lis += '<div class="default-address-tip">默认地址</div></li>';
					}else{
						lis += '<div class="set-default-address">设为默认地址</div></li>';
					}
				}
				$('#address-select .address-list').html(lis);
			})
		}
	})
	addressSelectModule.init();
	
	
	
	
	/**
	 * 	个人中心模块
	 */
	var myCenterModule = Object.extend(Module, {
		moduleName:"my-center",
		init:function(){
			var that = this;
			that.modules[that.moduleName] = that;
			that.addresses = {};
			that.orders = {};
			//首页
			$('#my-center .nav-back').off().tap(function(){
				that.pagePrev('index');
			})
			
			//初始更新地址管理栏
			$.ajax({
				type:"get",
				url: '/DinResSys2/user!getUserInfo',
				dataType: 'json',
				success:function(data, status, jqXHR) {
					var phone = data.phone;
					that.addresses = data.addresses;
					that.update({addresses:that.addresses});
				}
			});
			
			//初始更新订单查看栏
			$.ajax({
				type:"get",
				url: '/DinResSys2/order!getAllOrder',
				dataType: 'json',
				success:function(data, status, jqXHR){
					that.orders = data.orders;
					var uls = '';
					that.orders.forEach(function(order, i){
						uls += '<ul><li class="order-date">'+order.date+'<span>状态：'+order.status+'</span></li>';
						var menus = order.menus, totalPrice  = 0;
						menus.forEach(function(menu, j){
							uls += '<li>'+menu.item+'<span class="appraise-btn">&gt;</span><span class="price">￥'+menu.price+'</span><span class="num">x'+menu.num+'</span></li>'
							totalPrice += menu.price*menu.num;
						})
						uls += '<li class="clearfix"><span class="price total-price">￥'+totalPrice+'</span></li>';
						
						uls += '</ul>';
					})
					$('.order-list').html(uls);
				}
			})
			
			
			//设置默认和新增地址
			$('#my-center .address-list').off().on('tap', '.set-default-address, .add-address-btn', function(){
				if($(this).is('.set-default-address')){
					var index = $(this).parent().index();
					$.ajax({
						type:"get",
						url: '/DinResSys2/address!setDefaultAddress',
						dataType: 'json',
						data:{
							'address.id': that.addresses[index].id
						},
						success:function(data, status, jqXHR) {
							if(parseInt(data.status)==1){
								that.addresses.forEach(function(ele, i){
									if(index==i){
										that.addresses[i].isDefault = true; 
									}else{
										that.addresses[i].isDefault = false;
									}
								})
							}
							that.update({addresses: that.addresses});
						}
					})
				}else if($(this).is('.add-address-btn')){
					var ad = $('#my-center .new-address').val().trim();
					if(ad==''){
						Toast.show('新增地址不能为空');
						return;
					}else{
						var $parent = $(this).parent();
						$.ajax({
							type:"get",
							url: '/DinResSys2/address!addAddress',
							dataType: 'json',
							data:{
								'address.ad': ad
							},
							success:function(data, status, jqXHR) {
								if(parseInt(data.status)==1){
									that.addresses.push(data.address);
									that.update({addresses:that.addresses})
									$parent.before('<li>'+data.address.address+'<div class="set-default-address">设为默认地址</div></li>');
									that.notify(that.modules['address-select'], that.modules['order-info'], {isRefresh:true});
								}else{
									Toast.show('操作失败')
								}
							}
						})
					}			
				}
			})
			
			//点击菜式评论右箭头
			$('.order-list').off().on('tap', '.appraise-btn', function(){
				var $parent = $(this).parent(),
					index = $parent.index()-1;
				var $parentUL = $parent.parent(),
					orderIndex = $parentUL.index();
					console.log(index);
				that.pageNext('my-center-menu-appraise', function(){
					that.notify(that.modules['my-center-menu-appraise'], {menuID: that.orders[orderIndex].menus[index].id});
				})
			})
		},
		update:function(args){
			if(!!args.isRefresh){
				this.init();
				return;
			}
			if(!!args.addresses){
				this.addresses = args.addresses;
				var lis = '';
				this.addresses.forEach(function(ele, i){
					lis += '<li>'+ele.address;
					if(ele.isDefault){
						lis += '<div class="default-address-tip">默认地址</div></li>';
					}else{
						lis += '<div class="set-default-address">设为默认地址</div></li>';
					}				
				})
				lis += '<li class="add-address"><input class="new-address" type="text" placeholder="请填写新的地址"/><div class="add-address-btn">新增</div></li>'
				$('#my-center .address-list').html(lis);
			}
			if(!!args.orders){
				
			}
		}
	})
	myCenterModule.init();
	
	
	var myCenterMenuAppraise = Object.extend(Module, {
		moduleName:"my-center-menu-appraise",
		init:function(){
			var that = this;
			that.modules[that.moduleName] = that;
			
			//返回
			$('#my-center-menu-appraise .nav-back').off().tap(function(){
				that.pagePrev(that.from);
			})
			
			$('#my-center-menu-appraise .star').off().tap(function(){
				var index = $(this).index();
				console.log(index);
				$('#my-center-menu-appraise .star').each(function(i, ele){
					if(i<=index){
						ele.className = 'star red-star';
					}else{
						ele.className = 'star';
					}
				})
			})
		},
		update: function(args){
			var that = this;
			var id = args.menuID;
			$('#my-center-menu-appraise .commit-appraise-btn').off().tap(function(){
				var msg = $('#my-center-menu-appraise .appaise-msg').val().trim(),
					level = $('#my-center-menu-appraise .red-star').length;
				$.ajax({
					type:"get",
					url: '/DinResSys2/appraise!addAppraise',
					dataType: 'json',
					data:{
						'menuId':id,
						'appraise.praiseLevel': level,
						'appraise.detail': msg
					},
					success:function(data, status, jqXHR) {
						if(parseInt(data.status)==1){
							Toast.show('添加成功');
							that.pagePrev('my-center');
						}else{
							Toast.show('添加不成功');
						}
					}
				})
			})
		}
	})
	myCenterMenuAppraise.init();
		
	
	/**
	 * 	登录模块
	 */
	var loginModule = Object.extend(Module, {
		moduleName:"login",
		init:function(){
			var that = this;
			that.modules[that.moduleName] = that;
			
			//首页
			$('#login .nav-back').off().tap(function(){
				that.pagePrev('index');
			})
			//注册
			$('#login .nav-right').off().tap(function(){
				that.pageNext('register');
			})
			//登录			
			$('#login .login-btn').off().tap(function(){
				var username = $('#login #loginName').val(),
					pwd = $('#login #loginPwd').val();
				if(username.trim() == '' || pwd.trim() == ''){
					Toast.show('用户名或密码不能为空');
					return;
				}
				$.ajax({
					type:"get",
					url: '/DinResSys2/user!login',
					data:{
						'user.name': username,
						'user.password': pwd
					},
					dataType: 'json',
					success: function(data, status, jqXHR){
						if(data.status==1){
							Toast.show('登录成功',function(){
								that.pageNext('dishes');
								that.notify(that.modules['my-center'], that.modules['address-select'], that.modules['order-info'], {isRefresh:true});
							})
						}else{
							Toast.show('登录异常');
						}
					}
				})
			})
			
		},
		update:function(){
			
		}
	})
	loginModule.init();
	
	
	/**
	 * 	注册模块
	 */
	var registerModule = Object.extend(Module, {
		moduleName:"register",
		init:function(){
			var that = this;
			that.modules[that.moduleName] = that;
			//首页
			$('#register .nav-back').off().tap(function(){
				that.pagePrev('index');
			})
			
			$('#register .register-btn').off().tap(function(){
				var name = $('#register #registerName').val().trim(),
					pwd = $('#register #registerPwd').val().trim(),
					pwdAgain = $('#register #pwd-again').val().trim(),
					phone = $('#register #phone').val().trim(),
					address = $('#register #address').val().trim();
				if(name=='' || pwd=='' || pwdAgain=='' || phone=='' || address==''){
					debugger
					Toast.show('所有输入不能为空');
				}else{
					if(pwd!=pwdAgain){
						Toast.show('两次密码输入不一致');
					}else if(!(/^\d{11}$/.test(phone))){
						Toast.show('手机号码格式不正确')
					}else{
						$.ajax({
							type:"get",
							url: '/DinResSys2/user!register',
							data:{
								'user.name': name,
								'user.password': pwd,
								'user.tel': phone,
								'address.ad': address
							},
							dataType: 'json',
							success: function(data, status, jqXHR){
								if(data.status==1){
									Toast.show('注册成功',function(){
										that.pageNext('dishes', function(){
											that.notify(that.modules['my-center'], that.modules['address-select'], that.modules['order-info'], {isRefresh:true});
										});
									})
								}else{
									Toast.show('注册失败');
								}
							}
						})
					}
				}
				
			})
		},
		update: function(){
			
		}
	})
	registerModule.init();
	
	
	
})
