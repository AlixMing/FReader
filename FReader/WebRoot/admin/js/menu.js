//增加菜式类型
$('.add-menu-type a').click(function(){
	var typeName = $('#menu-type').val();
	if(typeName==''){
		alert('输入不能为空');
		return;
	}
	$.ajax({
		type:"get",
		url:"/DinResSys2/admin/type!addMenuType",
		async:true,
		dataType: 'json',
		data:{
			'type.typeName': typeName
		},
		success:function(data){
			if(parseInt(data.status)==1){
				alert('添加成功');
				location.reload();
			}else{
				alert('添加失败');
			}
		}
	});
})

//点击“新增”，弹出菜式填写模态框
$('.add a').click(function(){
	$('#item').val('');
	$('#price').val('');
	$('#describe').val('');
	$('#menuId').val('');
	$('#menuModal  form').attr('action','/DinResSys2/admin/menu!addMenu');
	$('#menuModal').modal('show');
})
//点击'修改', 弹出菜式填写模态框
$('.change a').click(function(){
	$td  = $(this).parent().parent().parent().children();
	var id = $td.eq(0).text(),
		item = $td.eq(1).text(),
		describe = $td.eq(2).text(),
		price = $td.eq(5).find('.price').text().substr(1);
		
	$('#item').val(item);
	$('#price').val(price);
	$('#describe').val(describe);
	$('#menuId').val(id);
	$('#menuModal  form').attr('action','/DinResSys2/admin/menu!changeMenu');
	
	$('#menuModal').modal('show');
})

$('.commit-menu').click(function(){
	var item = $('#item').val().trim(),
		price = $('#price').val().trim();
	if(item=='' || price==''){
		alert('活动名和价格不能为空')
		return;
	}
	$('#menuModal  form').submit();	
})




